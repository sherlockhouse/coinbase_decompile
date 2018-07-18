package com.coinbase.android.quickstart;

import rx.functions.Action1;

final /* synthetic */ class QuickstartManager$$Lambda$3 implements Action1 {
    private static final QuickstartManager$$Lambda$3 instance = new QuickstartManager$$Lambda$3();

    private QuickstartManager$$Lambda$3() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        QuickstartManager.lambda$showCdvVerification$2((Throwable) obj);
    }
}
