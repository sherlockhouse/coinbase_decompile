package com.coinbase.android.task;

import rx.functions.Action1;

final /* synthetic */ class FetchAccountTask$$Lambda$7 implements Action1 {
    private final FetchAccountTask arg$1;

    private FetchAccountTask$$Lambda$7(FetchAccountTask fetchAccountTask) {
        this.arg$1 = fetchAccountTask;
    }

    public static Action1 lambdaFactory$(FetchAccountTask fetchAccountTask) {
        return new FetchAccountTask$$Lambda$7(fetchAccountTask);
    }

    public void call(Object obj) {
        this.arg$1.mAccountUpdatedConnector.get().onNext(null);
    }
}
