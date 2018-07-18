package com.coinbase.cache;

import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Response;

final /* synthetic */ class OkHttpInMemoryLruCache$$Lambda$1 implements Interceptor {
    private final OkHttpInMemoryLruCache arg$1;

    private OkHttpInMemoryLruCache$$Lambda$1(OkHttpInMemoryLruCache okHttpInMemoryLruCache) {
        this.arg$1 = okHttpInMemoryLruCache;
    }

    public static Interceptor lambdaFactory$(OkHttpInMemoryLruCache okHttpInMemoryLruCache) {
        return new OkHttpInMemoryLruCache$$Lambda$1(okHttpInMemoryLruCache);
    }

    public Response intercept(Chain chain) {
        return OkHttpInMemoryLruCache.lambda$createInterceptor$0(this.arg$1, chain);
    }
}
