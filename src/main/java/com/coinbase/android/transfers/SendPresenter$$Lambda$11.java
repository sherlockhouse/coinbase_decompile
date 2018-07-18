package com.coinbase.android.transfers;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class SendPresenter$$Lambda$11 implements Func1 {
    private static final SendPresenter$$Lambda$11 instance = new SendPresenter$$Lambda$11();

    private SendPresenter$$Lambda$11() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return SendPresenter.lambda$onShow$10((Pair) obj);
    }
}
