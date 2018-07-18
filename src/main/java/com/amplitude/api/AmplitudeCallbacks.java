package com.amplitude.api;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

class AmplitudeCallbacks implements ActivityLifecycleCallbacks {
    private static AmplitudeLog logger = AmplitudeLog.getLogger();
    private AmplitudeClient clientInstance = null;

    public AmplitudeCallbacks(AmplitudeClient clientInstance) {
        if (clientInstance == null) {
            logger.e("com.amplitude.api.AmplitudeCallbacks", "Need to initialize AmplitudeCallbacks with AmplitudeClient instance");
            return;
        }
        this.clientInstance = clientInstance;
        clientInstance.useForegroundTracking();
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
        if (this.clientInstance == null) {
            logger.e("com.amplitude.api.AmplitudeCallbacks", "Need to initialize AmplitudeCallbacks with AmplitudeClient instance");
        } else {
            this.clientInstance.onExitForeground(getCurrentTimeMillis());
        }
    }

    public void onActivityResumed(Activity activity) {
        if (this.clientInstance == null) {
            logger.e("com.amplitude.api.AmplitudeCallbacks", "Need to initialize AmplitudeCallbacks with AmplitudeClient instance");
        } else {
            this.clientInstance.onEnterForeground(getCurrentTimeMillis());
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outstate) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    protected long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
}
