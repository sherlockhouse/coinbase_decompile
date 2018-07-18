package com.coinbase.android.idology;

import rx.functions.Action1;

final /* synthetic */ class IdologyFormPresenter$$Lambda$5 implements Action1 {
    private final IdologyFormPresenter arg$1;

    private IdologyFormPresenter$$Lambda$5(IdologyFormPresenter idologyFormPresenter) {
        this.arg$1 = idologyFormPresenter;
    }

    public static Action1 lambdaFactory$(IdologyFormPresenter idologyFormPresenter) {
        return new IdologyFormPresenter$$Lambda$5(idologyFormPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mLogger.error("Couldn't subscribe to DatePickerConnector.OK, shouldn't happen", (Throwable) obj);
    }
}
