package com.coinbase.android.idology;

import rx.functions.Action1;

final /* synthetic */ class IdologyAddressFormPresenter$$Lambda$4 implements Action1 {
    private final IdologyAddressFormPresenter arg$1;

    private IdologyAddressFormPresenter$$Lambda$4(IdologyAddressFormPresenter idologyAddressFormPresenter) {
        this.arg$1 = idologyAddressFormPresenter;
    }

    public static Action1 lambdaFactory$(IdologyAddressFormPresenter idologyAddressFormPresenter) {
        return new IdologyAddressFormPresenter$$Lambda$4(idologyAddressFormPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mLogger.error("Couldn't handle address form errors", (Throwable) obj);
    }
}
