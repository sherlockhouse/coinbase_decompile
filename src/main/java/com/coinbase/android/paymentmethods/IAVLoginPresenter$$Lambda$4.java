package com.coinbase.android.paymentmethods;

import rx.functions.Action1;

final /* synthetic */ class IAVLoginPresenter$$Lambda$4 implements Action1 {
    private final IAVLoginPresenter arg$1;

    private IAVLoginPresenter$$Lambda$4(IAVLoginPresenter iAVLoginPresenter) {
        this.arg$1 = iAVLoginPresenter;
    }

    public static Action1 lambdaFactory$(IAVLoginPresenter iAVLoginPresenter) {
        return new IAVLoginPresenter$$Lambda$4(iAVLoginPresenter);
    }

    public void call(Object obj) {
        IAVLoginPresenter.lambda$createAchAccountManually$3(this.arg$1, (Throwable) obj);
    }
}
