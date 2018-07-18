package com.firebase.jobdispatcher;

import android.app.Service;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import android.util.Pair;
import com.firebase.jobdispatcher.Job.Builder;
import com.firebase.jobdispatcher.JobTrigger.ContentUriTrigger;

public class GooglePlayReceiver extends Service implements JobFinishedCallback {
    private static final SimpleArrayMap<String, SimpleArrayMap<String, JobCallback>> callbacks = new SimpleArrayMap(1);
    private static final JobCoder prefixedCoder = new JobCoder("com.firebase.jobdispatcher.", true);
    private final GooglePlayCallbackExtractor callbackExtractor = new GooglePlayCallbackExtractor();
    Driver driver;
    private ExecutionDelegator executionDelegator;
    private int latestStartId;
    Messenger serviceMessenger;
    ValidationEnforcer validationEnforcer;

    private static void sendResultSafely(JobCallback callback, int result) {
        try {
            callback.jobFinished(result);
        } catch (Throwable e) {
            Log.e("FJD.GooglePlayReceiver", "Encountered error running callback", e.getCause());
        }
    }

    public final int onStartCommand(Intent intent, int flags, int startId) {
        try {
            super.onStartCommand(intent, flags, startId);
            if (intent == null) {
                Log.w("FJD.GooglePlayReceiver", "Null Intent passed, terminating");
                synchronized (callbacks) {
                    this.latestStartId = startId;
                    if (callbacks.isEmpty()) {
                        stopSelf(this.latestStartId);
                    }
                }
            } else {
                String action = intent.getAction();
                if ("com.google.android.gms.gcm.ACTION_TASK_READY".equals(action)) {
                    getExecutionDelegator().executeJob(prepareJob(intent));
                    synchronized (callbacks) {
                        this.latestStartId = startId;
                        if (callbacks.isEmpty()) {
                            stopSelf(this.latestStartId);
                        }
                    }
                } else if ("com.google.android.gms.gcm.SERVICE_ACTION_INITIALIZE".equals(action)) {
                    synchronized (callbacks) {
                        this.latestStartId = startId;
                        if (callbacks.isEmpty()) {
                            stopSelf(this.latestStartId);
                        }
                    }
                } else {
                    Log.e("FJD.GooglePlayReceiver", "Unknown action received, terminating");
                    synchronized (callbacks) {
                        this.latestStartId = startId;
                        if (callbacks.isEmpty()) {
                            stopSelf(this.latestStartId);
                        }
                    }
                }
            }
            return 2;
        } catch (Throwable th) {
            synchronized (callbacks) {
                this.latestStartId = startId;
                if (callbacks.isEmpty()) {
                    stopSelf(this.latestStartId);
                }
            }
        }
    }

    public IBinder onBind(Intent intent) {
        if (intent == null || VERSION.SDK_INT < 21 || !"com.google.android.gms.gcm.ACTION_TASK_READY".equals(intent.getAction())) {
            return null;
        }
        return getServiceMessenger().getBinder();
    }

    private synchronized Messenger getServiceMessenger() {
        if (this.serviceMessenger == null) {
            this.serviceMessenger = new Messenger(new GooglePlayMessageHandler(Looper.getMainLooper(), this));
        }
        return this.serviceMessenger;
    }

    synchronized ExecutionDelegator getExecutionDelegator() {
        if (this.executionDelegator == null) {
            this.executionDelegator = new ExecutionDelegator(this, this);
        }
        return this.executionDelegator;
    }

    private synchronized Driver getGooglePlayDriver() {
        if (this.driver == null) {
            this.driver = new GooglePlayDriver(getApplicationContext());
        }
        return this.driver;
    }

    private synchronized ValidationEnforcer getValidationEnforcer() {
        if (this.validationEnforcer == null) {
            this.validationEnforcer = new ValidationEnforcer(getGooglePlayDriver().getValidator());
        }
        return this.validationEnforcer;
    }

    JobInvocation prepareJob(Intent intent) {
        Bundle intentExtras = intent.getExtras();
        if (intentExtras == null) {
            Log.e("FJD.GooglePlayReceiver", "No data provided, terminating");
            return null;
        }
        Pair<JobCallback, Bundle> extraction = this.callbackExtractor.extractCallback(intentExtras);
        if (extraction != null) {
            return prepareJob((JobCallback) extraction.first, (Bundle) extraction.second);
        }
        Log.i("FJD.GooglePlayReceiver", "no callback found");
        return null;
    }

    JobInvocation prepareJob(JobCallback callback, Bundle bundle) {
        JobInvocation job = prefixedCoder.decodeIntentBundle(bundle);
        if (job == null) {
            Log.e("FJD.GooglePlayReceiver", "unable to decode job");
            sendResultSafely(callback, 2);
            return null;
        }
        synchronized (callbacks) {
            SimpleArrayMap<String, JobCallback> map = (SimpleArrayMap) callbacks.get(job.getService());
            if (map == null) {
                map = new SimpleArrayMap(1);
                callbacks.put(job.getService(), map);
            }
            map.put(job.getTag(), callback);
        }
        return job;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onJobFinished(JobInvocation js, int result) {
        synchronized (callbacks) {
            try {
                SimpleArrayMap<String, JobCallback> map = (SimpleArrayMap) callbacks.get(js.getService());
                if (map != null) {
                    JobCallback callback = (JobCallback) map.remove(js.getTag());
                    if (callback != null) {
                        if (map.isEmpty()) {
                            callbacks.remove(js.getService());
                        }
                        if (needsToBeRescheduled(js, result)) {
                            reschedule(js);
                        } else {
                            if (Log.isLoggable("FJD.GooglePlayReceiver", 2)) {
                                Log.v("FJD.GooglePlayReceiver", "sending jobFinished for " + js.getTag() + " = " + result);
                            }
                            sendResultSafely(callback, result);
                        }
                        if (callbacks.isEmpty()) {
                            stopSelf(this.latestStartId);
                        }
                    } else if (callbacks.isEmpty()) {
                        stopSelf(this.latestStartId);
                    }
                }
            } finally {
                if (callbacks.isEmpty()) {
                    stopSelf(this.latestStartId);
                }
            }
        }
    }

    private void reschedule(JobInvocation jobInvocation) {
        getGooglePlayDriver().schedule(new Builder(getValidationEnforcer(), jobInvocation).setReplaceCurrent(true).build());
    }

    private static boolean needsToBeRescheduled(JobParameters job, int result) {
        if (job.isRecurring() && (job.getTrigger() instanceof ContentUriTrigger) && result != 1) {
            return true;
        }
        return false;
    }

    static JobCoder getJobCoder() {
        return prefixedCoder;
    }

    static void onSchedule(Job job) {
        synchronized (callbacks) {
            SimpleArrayMap<String, JobCallback> jobs = (SimpleArrayMap) callbacks.get(job.getService());
            if (jobs == null) {
            } else if (((JobCallback) jobs.get(job.getTag())) == null) {
            } else {
                ExecutionDelegator.stopJob(new Builder().setTag(job.getTag()).setService(job.getService()).setTrigger(job.getTrigger()).build(), false);
            }
        }
    }
}
