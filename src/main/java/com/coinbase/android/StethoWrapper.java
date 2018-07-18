package com.coinbase.android;

import android.content.Context;
import okhttp3.Interceptor;

public class StethoWrapper {
    public StethoWrapper(Context context) {
    }

    public Interceptor getNetworkSnifferInterceptor() {
        return StethoWrapper$$Lambda$1.lambdaFactory$();
    }
}
