package com.coinbase.android.idology;

import rx.functions.Action1;

final /* synthetic */ class IdologyFormPresenter$$Lambda$7 implements Action1 {
    private final IdologyFormPresenter arg$1;

    private IdologyFormPresenter$$Lambda$7(IdologyFormPresenter idologyFormPresenter) {
        this.arg$1 = idologyFormPresenter;
    }

    public static Action1 lambdaFactory$(IdologyFormPresenter idologyFormPresenter) {
        return new IdologyFormPresenter$$Lambda$7(idologyFormPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mScreen.hideDatePickerLayout();
    }
}
