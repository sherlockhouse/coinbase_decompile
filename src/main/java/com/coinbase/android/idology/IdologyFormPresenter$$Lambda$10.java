package com.coinbase.android.idology;

import rx.functions.Action1;

final /* synthetic */ class IdologyFormPresenter$$Lambda$10 implements Action1 {
    private final IdologyFormPresenter arg$1;

    private IdologyFormPresenter$$Lambda$10(IdologyFormPresenter idologyFormPresenter) {
        this.arg$1 = idologyFormPresenter;
    }

    public static Action1 lambdaFactory$(IdologyFormPresenter idologyFormPresenter) {
        return new IdologyFormPresenter$$Lambda$10(idologyFormPresenter);
    }

    public void call(Object obj) {
        IdologyFormPresenter.lambda$submitForm$9(this.arg$1, (Throwable) obj);
    }
}
