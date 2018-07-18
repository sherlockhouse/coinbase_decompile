package com.coinbase.android.idology;

import rx.functions.Action1;

final /* synthetic */ class IdologyNameFormPresenter$$Lambda$6 implements Action1 {
    private final IdologyNameFormPresenter arg$1;

    private IdologyNameFormPresenter$$Lambda$6(IdologyNameFormPresenter idologyNameFormPresenter) {
        this.arg$1 = idologyNameFormPresenter;
    }

    public static Action1 lambdaFactory$(IdologyNameFormPresenter idologyNameFormPresenter) {
        return new IdologyNameFormPresenter$$Lambda$6(idologyNameFormPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mLogger.error("Couldn't subscribe to DatePickerConnector.CANCEL, shouldn't happen", (Throwable) obj);
    }
}
