package com.coinbase.android.signin.state;

import rx.functions.Action1;

final /* synthetic */ class StateIdologyRetryFormPresenter$$Lambda$3 implements Action1 {
    private final StateIdologyRetryFormPresenter arg$1;

    private StateIdologyRetryFormPresenter$$Lambda$3(StateIdologyRetryFormPresenter stateIdologyRetryFormPresenter) {
        this.arg$1 = stateIdologyRetryFormPresenter;
    }

    public static Action1 lambdaFactory$(StateIdologyRetryFormPresenter stateIdologyRetryFormPresenter) {
        return new StateIdologyRetryFormPresenter$$Lambda$3(stateIdologyRetryFormPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mLogger.error("Couldn't subscribe to IdologyFormValidConnector, shouldn't happen", (Throwable) obj);
    }
}
