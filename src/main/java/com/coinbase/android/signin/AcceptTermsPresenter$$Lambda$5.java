package com.coinbase.android.signin;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class AcceptTermsPresenter$$Lambda$5 implements Action1 {
    private final AcceptTermsPresenter arg$1;

    private AcceptTermsPresenter$$Lambda$5(AcceptTermsPresenter acceptTermsPresenter) {
        this.arg$1 = acceptTermsPresenter;
    }

    public static Action1 lambdaFactory$(AcceptTermsPresenter acceptTermsPresenter) {
        return new AcceptTermsPresenter$$Lambda$5(acceptTermsPresenter);
    }

    public void call(Object obj) {
        AcceptTermsPresenter.lambda$onAcceptTermsClicked$4(this.arg$1, (Pair) obj);
    }
}
