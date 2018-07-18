package com.coinbase.android.transfers;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class SendPresenter$$Lambda$8 implements Func1 {
    private static final SendPresenter$$Lambda$8 instance = new SendPresenter$$Lambda$8();

    private SendPresenter$$Lambda$8() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return SendPresenter.lambda$onShow$7((Pair) obj);
    }
}
