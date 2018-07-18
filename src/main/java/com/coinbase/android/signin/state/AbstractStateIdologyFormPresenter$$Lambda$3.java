package com.coinbase.android.signin.state;

import rx.functions.Action1;

final /* synthetic */ class AbstractStateIdologyFormPresenter$$Lambda$3 implements Action1 {
    private final AbstractStateIdologyFormPresenter arg$1;

    private AbstractStateIdologyFormPresenter$$Lambda$3(AbstractStateIdologyFormPresenter abstractStateIdologyFormPresenter) {
        this.arg$1 = abstractStateIdologyFormPresenter;
    }

    public static Action1 lambdaFactory$(AbstractStateIdologyFormPresenter abstractStateIdologyFormPresenter) {
        return new AbstractStateIdologyFormPresenter$$Lambda$3(abstractStateIdologyFormPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mLogger.error("Couldn't subscribe to IdologyFormValidConnector, shouldn't happen", (Throwable) obj);
    }
}
