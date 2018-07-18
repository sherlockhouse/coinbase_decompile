package com.coinbase;

import android.util.Pair;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.functions.Func2;

final /* synthetic */ class Coinbase$$Lambda$19 implements Func2 {
    private static final Coinbase$$Lambda$19 instance = new Coinbase$$Lambda$19();

    private Coinbase$$Lambda$19() {
    }

    public static Func2 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj, Object obj2) {
        return new Pair((Response) obj, (Retrofit) obj2);
    }
}
