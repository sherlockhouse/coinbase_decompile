package com.coinbase.android.task;

import android.util.Pair;
import rx.functions.Func2;

final /* synthetic */ class SyncAccountsTask$$Lambda$1 implements Func2 {
    private static final SyncAccountsTask$$Lambda$1 instance = new SyncAccountsTask$$Lambda$1();

    private SyncAccountsTask$$Lambda$1() {
    }

    public static Func2 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj, Object obj2) {
        return new Pair((Pair) obj, (Pair) obj2);
    }
}
