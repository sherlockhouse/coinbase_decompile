package com.coinbase.android.utils;

import android.app.Application;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class RefWatcherWrapper {
    private final Application mAppContext;
    private final ConfigUtils mConfigUtils;
    private RefWatcher mRefWatcher;

    public RefWatcherWrapper(Application applicationContext, ConfigUtils configUtils) {
        this.mAppContext = applicationContext;
        this.mConfigUtils = configUtils;
    }

    public synchronized void install() {
        if (!(LeakCanary.isInAnalyzerProcess(this.mAppContext) || this.mConfigUtils.isTesting())) {
            this.mRefWatcher = LeakCanary.install(this.mAppContext);
        }
    }

    public synchronized RefWatcher get() {
        return this.mRefWatcher;
    }
}
