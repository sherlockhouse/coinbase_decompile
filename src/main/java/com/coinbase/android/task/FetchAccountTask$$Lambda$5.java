package com.coinbase.android.task;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class FetchAccountTask$$Lambda$5 implements Func1 {
    private final FetchAccountTask arg$1;

    private FetchAccountTask$$Lambda$5(FetchAccountTask fetchAccountTask) {
        this.arg$1 = fetchAccountTask;
    }

    public static Func1 lambdaFactory$(FetchAccountTask fetchAccountTask) {
        return new FetchAccountTask$$Lambda$5(fetchAccountTask);
    }

    public Object call(Object obj) {
        return FetchAccountTask.lambda$fetchCryptoAccount$4(this.arg$1, (Pair) obj);
    }
}
