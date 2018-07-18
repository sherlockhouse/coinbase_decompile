package com.coinbase.android.transfers;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class SendPresenter$$Lambda$5 implements Func1 {
    private static final SendPresenter$$Lambda$5 instance = new SendPresenter$$Lambda$5();

    private SendPresenter$$Lambda$5() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return SendPresenter.lambda$onShow$4((Pair) obj);
    }
}
