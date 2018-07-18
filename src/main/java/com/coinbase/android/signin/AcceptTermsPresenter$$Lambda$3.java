package com.coinbase.android.signin;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class AcceptTermsPresenter$$Lambda$3 implements Action1 {
    private final AcceptTermsPresenter arg$1;

    private AcceptTermsPresenter$$Lambda$3(AcceptTermsPresenter acceptTermsPresenter) {
        this.arg$1 = acceptTermsPresenter;
    }

    public static Action1 lambdaFactory$(AcceptTermsPresenter acceptTermsPresenter) {
        return new AcceptTermsPresenter$$Lambda$3(acceptTermsPresenter);
    }

    public void call(Object obj) {
        AcceptTermsPresenter.lambda$setTNCDescription$2(this.arg$1, (Pair) obj);
    }
}
