package com.coinbase;

import android.util.Pair;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.functions.Func2;

final /* synthetic */ class Coinbase$$Lambda$28 implements Func2 {
    private static final Coinbase$$Lambda$28 instance = new Coinbase$$Lambda$28();

    private Coinbase$$Lambda$28() {
    }

    public static Func2 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj, Object obj2) {
        return new Pair((Response) obj, (Retrofit) obj2);
    }
}
