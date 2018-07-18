package com.coinbase.android.ui;

import rx.functions.Action1;

final /* synthetic */ class BackgroundDimmer$$Lambda$7 implements Action1 {
    private static final BackgroundDimmer$$Lambda$7 instance = new BackgroundDimmer$$Lambda$7();

    private BackgroundDimmer$$Lambda$7() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        BackgroundDimmer.lambda$dim$6((Throwable) obj);
    }
}
