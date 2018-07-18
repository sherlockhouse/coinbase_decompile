package com.coinbase.android.ui;

import rx.functions.Action1;

final /* synthetic */ class BackgroundDimmer$$Lambda$2 implements Action1 {
    private final BackgroundDimmer arg$1;

    private BackgroundDimmer$$Lambda$2(BackgroundDimmer backgroundDimmer) {
        this.arg$1 = backgroundDimmer;
    }

    public static Action1 lambdaFactory$(BackgroundDimmer backgroundDimmer) {
        return new BackgroundDimmer$$Lambda$2(backgroundDimmer);
    }

    public void call(Object obj) {
        this.arg$1.undim(null);
    }
}
