package com.coinbase.android.task;

import android.util.Pair;
import rx.functions.Func2;

final /* synthetic */ class FetchAccountTask$$Lambda$4 implements Func2 {
    private static final FetchAccountTask$$Lambda$4 instance = new FetchAccountTask$$Lambda$4();

    private FetchAccountTask$$Lambda$4() {
    }

    public static Func2 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj, Object obj2) {
        return new Pair((Pair) obj, (Pair) obj2);
    }
}
