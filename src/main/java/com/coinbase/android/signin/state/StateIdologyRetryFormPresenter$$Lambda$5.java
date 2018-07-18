package com.coinbase.android.signin.state;

import rx.functions.Action1;

final /* synthetic */ class StateIdologyRetryFormPresenter$$Lambda$5 implements Action1 {
    private final StateIdologyRetryFormPresenter arg$1;

    private StateIdologyRetryFormPresenter$$Lambda$5(StateIdologyRetryFormPresenter stateIdologyRetryFormPresenter) {
        this.arg$1 = stateIdologyRetryFormPresenter;
    }

    public static Action1 lambdaFactory$(StateIdologyRetryFormPresenter stateIdologyRetryFormPresenter) {
        return new StateIdologyRetryFormPresenter$$Lambda$5(stateIdologyRetryFormPresenter);
    }

    public void call(Object obj) {
        StateIdologyRetryFormPresenter.lambda$onShow$4(this.arg$1, (Boolean) obj);
    }
}
