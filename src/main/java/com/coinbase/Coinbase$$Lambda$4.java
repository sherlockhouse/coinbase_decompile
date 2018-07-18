package com.coinbase;

import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Response;

final /* synthetic */ class Coinbase$$Lambda$4 implements Interceptor {
    private static final Coinbase$$Lambda$4 instance = new Coinbase$$Lambda$4();

    private Coinbase$$Lambda$4() {
    }

    public static Interceptor lambdaFactory$() {
        return instance;
    }

    public Response intercept(Chain chain) {
        return chain.proceed(chain.request());
    }
}
