package com.coinbase.android.signin.state;

import rx.functions.Action1;

final /* synthetic */ class StateIdologySourceOfFundsFormPresenter$$Lambda$2 implements Action1 {
    private final StateIdologySourceOfFundsFormPresenter arg$1;

    private StateIdologySourceOfFundsFormPresenter$$Lambda$2(StateIdologySourceOfFundsFormPresenter stateIdologySourceOfFundsFormPresenter) {
        this.arg$1 = stateIdologySourceOfFundsFormPresenter;
    }

    public static Action1 lambdaFactory$(StateIdologySourceOfFundsFormPresenter stateIdologySourceOfFundsFormPresenter) {
        return new StateIdologySourceOfFundsFormPresenter$$Lambda$2(stateIdologySourceOfFundsFormPresenter);
    }

    public void call(Object obj) {
        this.arg$1.setContinueButtonEnabled(((Boolean) obj).booleanValue());
    }
}
