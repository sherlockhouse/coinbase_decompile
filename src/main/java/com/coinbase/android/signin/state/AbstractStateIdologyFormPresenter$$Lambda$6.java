package com.coinbase.android.signin.state;

import rx.functions.Action1;

final /* synthetic */ class AbstractStateIdologyFormPresenter$$Lambda$6 implements Action1 {
    private final AbstractStateIdologyFormPresenter arg$1;

    private AbstractStateIdologyFormPresenter$$Lambda$6(AbstractStateIdologyFormPresenter abstractStateIdologyFormPresenter) {
        this.arg$1 = abstractStateIdologyFormPresenter;
    }

    public static Action1 lambdaFactory$(AbstractStateIdologyFormPresenter abstractStateIdologyFormPresenter) {
        return new AbstractStateIdologyFormPresenter$$Lambda$6(abstractStateIdologyFormPresenter);
    }

    public void call(Object obj) {
        AbstractStateIdologyFormPresenter.lambda$onShow$5(this.arg$1, (Boolean) obj);
    }
}
