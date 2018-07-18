package com.coinbase;

import rx.functions.Func2;

final /* synthetic */ class Coinbase$$Lambda$6 implements Func2 {
    private final Coinbase arg$1;

    private Coinbase$$Lambda$6(Coinbase coinbase) {
        this.arg$1 = coinbase;
    }

    public static Func2 lambdaFactory$(Coinbase coinbase) {
        return new Coinbase$$Lambda$6(coinbase);
    }

    public Object call(Object obj, Object obj2) {
        return this.arg$1._accessToken = null;
    }
}
