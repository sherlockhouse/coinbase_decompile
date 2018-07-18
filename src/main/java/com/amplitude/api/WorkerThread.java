package com.amplitude.api;

import android.os.Handler;
import android.os.HandlerThread;

public class WorkerThread extends HandlerThread {
    private Handler handler;

    public WorkerThread(String name) {
        super(name);
    }

    void post(Runnable r) {
        waitForInitialization();
        this.handler.post(r);
    }

    void postDelayed(Runnable r, long delayMillis) {
        waitForInitialization();
        this.handler.postDelayed(r, delayMillis);
    }

    private synchronized void waitForInitialization() {
        if (this.handler == null) {
            this.handler = new Handler(getLooper());
        }
    }
}
