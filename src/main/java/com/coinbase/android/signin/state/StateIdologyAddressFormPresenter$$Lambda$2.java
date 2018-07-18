package com.coinbase.android.signin.state;

import rx.functions.Action1;

final /* synthetic */ class StateIdologyAddressFormPresenter$$Lambda$2 implements Action1 {
    private final StateIdologyAddressFormPresenter arg$1;

    private StateIdologyAddressFormPresenter$$Lambda$2(StateIdologyAddressFormPresenter stateIdologyAddressFormPresenter) {
        this.arg$1 = stateIdologyAddressFormPresenter;
    }

    public static Action1 lambdaFactory$(StateIdologyAddressFormPresenter stateIdologyAddressFormPresenter) {
        return new StateIdologyAddressFormPresenter$$Lambda$2(stateIdologyAddressFormPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mLogger.error("Error calling showContinue button", (Throwable) obj);
    }
}
