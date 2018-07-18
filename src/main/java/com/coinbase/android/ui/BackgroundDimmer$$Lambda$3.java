package com.coinbase.android.ui;

import rx.functions.Action1;

final /* synthetic */ class BackgroundDimmer$$Lambda$3 implements Action1 {
    private static final BackgroundDimmer$$Lambda$3 instance = new BackgroundDimmer$$Lambda$3();

    private BackgroundDimmer$$Lambda$3() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        BackgroundDimmer.lambda$dim$2((Throwable) obj);
    }
}
