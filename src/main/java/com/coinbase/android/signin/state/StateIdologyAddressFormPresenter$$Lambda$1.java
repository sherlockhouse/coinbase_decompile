package com.coinbase.android.signin.state;

import rx.functions.Action1;

final /* synthetic */ class StateIdologyAddressFormPresenter$$Lambda$1 implements Action1 {
    private final StateIdologyAddressFormPresenter arg$1;

    private StateIdologyAddressFormPresenter$$Lambda$1(StateIdologyAddressFormPresenter stateIdologyAddressFormPresenter) {
        this.arg$1 = stateIdologyAddressFormPresenter;
    }

    public static Action1 lambdaFactory$(StateIdologyAddressFormPresenter stateIdologyAddressFormPresenter) {
        return new StateIdologyAddressFormPresenter$$Lambda$1(stateIdologyAddressFormPresenter);
    }

    public void call(Object obj) {
        StateIdologyAddressFormPresenter.lambda$onShow$0(this.arg$1, (Boolean) obj);
    }
}
