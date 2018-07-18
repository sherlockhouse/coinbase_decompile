package com.coinbase.android.utils;

import android.app.Application;
import android.content.Context;

public class ConfigUtils {
    private static final String TEST_PACKAGE = "com.coinbase.android.test";
    private final Context mContext;
    private Boolean mIsTesting;

    public ConfigUtils(Application app) {
        this.mContext = app;
    }

    public boolean isTesting() {
        return false;
    }
}
