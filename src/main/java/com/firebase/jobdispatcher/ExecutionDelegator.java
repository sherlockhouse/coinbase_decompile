package com.firebase.jobdispatcher;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import java.lang.ref.WeakReference;

class ExecutionDelegator {
    static final SimpleArrayMap<JobInvocation, JobServiceConnection> serviceConnections = new SimpleArrayMap();
    private final Context context;
    private final JobFinishedCallback jobFinishedCallback;
    final ResponseHandler responseHandler = new ResponseHandler(Looper.getMainLooper(), new WeakReference(this));

    interface JobFinishedCallback {
        void onJobFinished(JobInvocation jobInvocation, int i);
    }

    static class ResponseHandler extends Handler {
        private final WeakReference<ExecutionDelegator> executionDelegatorReference;

        ResponseHandler(Looper looper, WeakReference<ExecutionDelegator> executionDelegator) {
            super(looper);
            this.executionDelegatorReference = executionDelegator;
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (msg.obj instanceof JobInvocation) {
                        ExecutionDelegator delegator = (ExecutionDelegator) this.executionDelegatorReference.get();
                        if (delegator == null) {
                            Log.wtf("FJD.ExternalReceiver", "handleMessage: service was unexpectedly GC'd, can't send job result");
                            return;
                        } else {
                            delegator.onJobFinishedMessage((JobInvocation) msg.obj, msg.arg1);
                            return;
                        }
                    }
                    Log.wtf("FJD.ExternalReceiver", "handleMessage: unknown obj returned");
                    return;
                default:
                    Log.wtf("FJD.ExternalReceiver", "handleMessage: unknown message type received: " + msg.what);
                    return;
            }
        }
    }

    ExecutionDelegator(Context context, JobFinishedCallback jobFinishedCallback) {
        this.context = context;
        this.jobFinishedCallback = jobFinishedCallback;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void executeJob(JobInvocation jobInvocation) {
        if (jobInvocation != null) {
            synchronized (serviceConnections) {
                JobServiceConnection oldConnection = (JobServiceConnection) serviceConnections.get(jobInvocation);
                if (oldConnection != null) {
                    if (oldConnection.isConnected() || oldConnection.wasUnbound()) {
                        oldConnection.onStop(false);
                    } else {
                        return;
                    }
                }
                JobServiceConnection conn = new JobServiceConnection(jobInvocation, this.responseHandler.obtainMessage(1), this.context);
                serviceConnections.put(jobInvocation, conn);
                if (!this.context.bindService(createBindIntent(jobInvocation), conn, 1)) {
                    Log.e("FJD.ExternalReceiver", "Unable to bind to " + jobInvocation.getService());
                    conn.unbind();
                }
            }
        }
    }

    private Intent createBindIntent(JobParameters jobParameters) {
        Intent execReq = new Intent("com.firebase.jobdispatcher.ACTION_EXECUTE");
        execReq.setClassName(this.context, jobParameters.getService());
        return execReq;
    }

    static void stopJob(JobInvocation job, boolean needToSendResult) {
        synchronized (serviceConnections) {
            JobServiceConnection jobServiceConnection = (JobServiceConnection) serviceConnections.remove(job);
            if (jobServiceConnection != null) {
                jobServiceConnection.onStop(needToSendResult);
            }
        }
    }

    private void onJobFinishedMessage(JobInvocation jobInvocation, int result) {
        synchronized (serviceConnections) {
            JobServiceConnection connection = (JobServiceConnection) serviceConnections.remove(jobInvocation);
            if (connection != null) {
                connection.unbind();
            }
        }
        this.jobFinishedCallback.onJobFinished(jobInvocation, result);
    }
}
