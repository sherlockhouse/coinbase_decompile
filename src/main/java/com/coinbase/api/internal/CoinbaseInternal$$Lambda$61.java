package com.coinbase.api.internal;

import android.util.Pair;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.functions.Func2;

final /* synthetic */ class CoinbaseInternal$$Lambda$61 implements Func2 {
    private static final CoinbaseInternal$$Lambda$61 instance = new CoinbaseInternal$$Lambda$61();

    private CoinbaseInternal$$Lambda$61() {
    }

    public static Func2 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj, Object obj2) {
        return new Pair((Response) obj, (Retrofit) obj2);
    }
}
