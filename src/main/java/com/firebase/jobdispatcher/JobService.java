package com.firebase.jobdispatcher;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Locale;

public abstract class JobService extends Service {
    static final String ACTION_EXECUTE = "com.firebase.jobdispatcher.ACTION_EXECUTE";
    public static final int RESULT_FAIL_NORETRY = 2;
    public static final int RESULT_FAIL_RETRY = 1;
    public static final int RESULT_SUCCESS = 0;
    static final String TAG = "FJD.JobService";
    private final LocalBinder binder = new LocalBinder();
    private final SimpleArrayMap<String, JobCallback> runningJobs = new SimpleArrayMap(1);

    private static final class JobCallback {
        public final Message message;

        private JobCallback(Message message) {
            this.message = message;
        }

        void sendResult(int result) {
            this.message.arg1 = result;
            this.message.sendToTarget();
        }
    }

    class LocalBinder extends Binder {
        LocalBinder() {
        }

        JobService getService() {
            return JobService.this;
        }
    }

    public abstract boolean onStartJob(JobParameters jobParameters);

    public abstract boolean onStopJob(JobParameters jobParameters);

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void start(JobParameters job, Message msg) {
        synchronized (this.runningJobs) {
            if (this.runningJobs.containsKey(job.getTag())) {
                Log.w(TAG, String.format(Locale.US, "Job with tag = %s was already running.", new Object[]{job.getTag()}));
                return;
            }
            this.runningJobs.put(job.getTag(), new JobCallback(msg));
            if (!onStartJob(job)) {
                JobCallback callback = (JobCallback) this.runningJobs.remove(job.getTag());
                if (callback != null) {
                    callback.sendResult(0);
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void stop(JobInvocation job, boolean needToSendResult) {
        synchronized (this.runningJobs) {
            JobCallback jobCallback = (JobCallback) this.runningJobs.remove(job.getTag());
            if (jobCallback != null) {
                boolean shouldRetry = onStopJob(job);
                if (needToSendResult) {
                    jobCallback.sendResult(shouldRetry ? 1 : 0);
                }
            } else if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Provided job has already been executed.");
            }
        }
    }

    public final void jobFinished(JobParameters job, boolean needsReschedule) {
        if (job == null) {
            Log.e(TAG, "jobFinished called with a null JobParameters");
            return;
        }
        synchronized (this.runningJobs) {
            JobCallback jobCallback = (JobCallback) this.runningJobs.remove(job.getTag());
            if (jobCallback != null) {
                jobCallback.sendResult(needsReschedule ? 1 : 0);
            }
        }
    }

    public final int onStartCommand(Intent intent, int flags, int startId) {
        stopSelf(startId);
        return 2;
    }

    public final IBinder onBind(Intent intent) {
        return this.binder;
    }

    public final boolean onUnbind(Intent intent) {
        synchronized (this.runningJobs) {
            for (int i = this.runningJobs.size() - 1; i >= 0; i--) {
                JobCallback callback = (JobCallback) this.runningJobs.remove(this.runningJobs.keyAt(i));
                if (callback != null) {
                    callback.sendResult(onStopJob((JobParameters) callback.message.obj) ? 1 : 2);
                }
            }
        }
        return super.onUnbind(intent);
    }

    public final void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    public final void onStart(Intent intent, int startId) {
    }

    protected final void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        super.dump(fd, writer, args);
    }

    public final void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public final void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
    }
}
