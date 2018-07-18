package com.coinbase.android;

import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Response;

final /* synthetic */ class StethoWrapper$$Lambda$1 implements Interceptor {
    private static final StethoWrapper$$Lambda$1 instance = new StethoWrapper$$Lambda$1();

    private StethoWrapper$$Lambda$1() {
    }

    public static Interceptor lambdaFactory$() {
        return instance;
    }

    public Response intercept(Chain chain) {
        return chain.proceed(chain.request());
    }
}
