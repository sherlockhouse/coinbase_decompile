package com.coinbase.android.signin;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class AcceptTermsPresenter$$Lambda$2 implements Func1 {
    private static final AcceptTermsPresenter$$Lambda$2 instance = new AcceptTermsPresenter$$Lambda$2();

    private AcceptTermsPresenter$$Lambda$2() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return AcceptTermsPresenter.lambda$setTNCDescription$1((Pair) obj);
    }
}
