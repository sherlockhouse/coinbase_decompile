package com.coinbase.android.idology;

import rx.functions.Action1;

final /* synthetic */ class IdologyFormPresenter$$Lambda$14 implements Action1 {
    private final IdologyFormPresenter arg$1;

    private IdologyFormPresenter$$Lambda$14(IdologyFormPresenter idologyFormPresenter) {
        this.arg$1 = idologyFormPresenter;
    }

    public static Action1 lambdaFactory$(IdologyFormPresenter idologyFormPresenter) {
        return new IdologyFormPresenter$$Lambda$14(idologyFormPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mSnackBarWrapper.showFailure((Throwable) obj);
    }
}
