package com.coinbase.android.paymentmethods;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class IAVLoginPresenter$$Lambda$1 implements Action1 {
    private final IAVLoginPresenter arg$1;

    private IAVLoginPresenter$$Lambda$1(IAVLoginPresenter iAVLoginPresenter) {
        this.arg$1 = iAVLoginPresenter;
    }

    public static Action1 lambdaFactory$(IAVLoginPresenter iAVLoginPresenter) {
        return new IAVLoginPresenter$$Lambda$1(iAVLoginPresenter);
    }

    public void call(Object obj) {
        IAVLoginPresenter.lambda$startAchVerification$0(this.arg$1, (Pair) obj);
    }
}
