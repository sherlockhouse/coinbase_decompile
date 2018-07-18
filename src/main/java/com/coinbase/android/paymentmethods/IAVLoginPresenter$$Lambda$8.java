package com.coinbase.android.paymentmethods;

import rx.functions.Action1;

final /* synthetic */ class IAVLoginPresenter$$Lambda$8 implements Action1 {
    private final IAVLoginPresenter arg$1;

    private IAVLoginPresenter$$Lambda$8(IAVLoginPresenter iAVLoginPresenter) {
        this.arg$1 = iAVLoginPresenter;
    }

    public static Action1 lambdaFactory$(IAVLoginPresenter iAVLoginPresenter) {
        return new IAVLoginPresenter$$Lambda$8(iAVLoginPresenter);
    }

    public void call(Object obj) {
        IAVLoginPresenter.lambda$completeBankCDVVerification$7(this.arg$1, (Throwable) obj);
    }
}
