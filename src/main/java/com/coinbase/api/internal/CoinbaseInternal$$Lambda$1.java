package com.coinbase.api.internal;

import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Response;

final /* synthetic */ class CoinbaseInternal$$Lambda$1 implements Interceptor {
    private static final CoinbaseInternal$$Lambda$1 instance = new CoinbaseInternal$$Lambda$1();

    private CoinbaseInternal$$Lambda$1() {
    }

    public static Interceptor lambdaFactory$() {
        return instance;
    }

    public Response intercept(Chain chain) {
        return chain.proceed(chain.request());
    }
}
