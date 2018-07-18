package com.coinbase.android.utils;

import android.os.Bundle;

public class BundleWrapper {
    public Bundle passAlongBoolean(Bundle inArgs, Bundle outArgs, String key) {
        if (inArgs.containsKey(key)) {
            outArgs.putBoolean(key, inArgs.getBoolean(key));
        }
        return outArgs;
    }
}
