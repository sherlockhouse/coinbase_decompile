package com.coinbase.android.task;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class FetchAccountTask$$Lambda$2 implements Action1 {
    private final FetchAccountTask arg$1;

    private FetchAccountTask$$Lambda$2(FetchAccountTask fetchAccountTask) {
        this.arg$1 = fetchAccountTask;
    }

    public static Action1 lambdaFactory$(FetchAccountTask fetchAccountTask) {
        return new FetchAccountTask$$Lambda$2(fetchAccountTask);
    }

    public void call(Object obj) {
        FetchAccountTask.lambda$fetchAccount$1(this.arg$1, (Pair) obj);
    }
}
