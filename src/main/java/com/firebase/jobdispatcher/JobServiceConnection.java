package com.firebase.jobdispatcher;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

class JobServiceConnection implements ServiceConnection {
    private LocalBinder binder;
    private final Context context;
    private final Message jobFinishedMessage;
    private final JobInvocation jobInvocation;
    private boolean wasUnbound = false;

    JobServiceConnection(JobInvocation jobInvocation, Message jobFinishedMessage, Context context) {
        this.jobFinishedMessage = jobFinishedMessage;
        this.jobInvocation = jobInvocation;
        this.jobFinishedMessage.obj = this.jobInvocation;
        this.context = context;
    }

    public synchronized void onServiceConnected(ComponentName name, IBinder service) {
        if (!(service instanceof LocalBinder)) {
            Log.w("FJD.ExternalReceiver", "Unknown service connected");
        } else if (this.binder != null || wasUnbound()) {
            Log.w("FJD.ExternalReceiver", "Connection have been used already.");
        } else {
            this.binder = (LocalBinder) service;
            this.binder.getService().start(this.jobInvocation, this.jobFinishedMessage);
        }
    }

    public synchronized void onServiceDisconnected(ComponentName name) {
        unbind();
    }

    synchronized boolean wasUnbound() {
        return this.wasUnbound;
    }

    synchronized boolean isConnected() {
        return this.binder != null;
    }

    synchronized void onStop(boolean needToSendResult) {
        if (wasUnbound()) {
            Log.w("FJD.ExternalReceiver", "Can't send stop request because service was unbound.");
        } else {
            if (this.binder != null) {
                this.binder.getService().stop(this.jobInvocation, needToSendResult);
            }
            unbind();
        }
    }

    synchronized void unbind() {
        if (!wasUnbound()) {
            this.binder = null;
            this.wasUnbound = true;
            try {
                this.context.unbindService(this);
            } catch (IllegalArgumentException e) {
                Log.w("FJD.ExternalReceiver", "Error unbinding service: " + e.getMessage());
            }
        }
    }
}
