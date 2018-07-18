package com.coinbase;

import android.util.Pair;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.functions.Func2;

final /* synthetic */ class Coinbase$$Lambda$29 implements Func2 {
    private static final Coinbase$$Lambda$29 instance = new Coinbase$$Lambda$29();

    private Coinbase$$Lambda$29() {
    }

    public static Func2 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj, Object obj2) {
        return new Pair((Response) obj, (Retrofit) obj2);
    }
}
