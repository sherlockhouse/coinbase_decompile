package com.coinbase.android.task;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class FetchAccountTask$$Lambda$1 implements Func1 {
    private final FetchAccountTask arg$1;

    private FetchAccountTask$$Lambda$1(FetchAccountTask fetchAccountTask) {
        this.arg$1 = fetchAccountTask;
    }

    public static Func1 lambdaFactory$(FetchAccountTask fetchAccountTask) {
        return new FetchAccountTask$$Lambda$1(fetchAccountTask);
    }

    public Object call(Object obj) {
        return FetchAccountTask.lambda$fetchAccount$0(this.arg$1, (Pair) obj);
    }
}
