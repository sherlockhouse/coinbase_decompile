package com.coinbase.android.wbl;

import android.util.Pair;
import rx.functions.Func3;

final /* synthetic */ class AvailableBalanceCalculator$$Lambda$1 implements Func3 {
    private static final AvailableBalanceCalculator$$Lambda$1 instance = new AvailableBalanceCalculator$$Lambda$1();

    private AvailableBalanceCalculator$$Lambda$1() {
    }

    public static Func3 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj, Object obj2, Object obj3) {
        return new Pair((Pair) obj, (Pair) obj3);
    }
}
