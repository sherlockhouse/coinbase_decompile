package com.coinbase.android.ui;

import rx.functions.Action1;

final /* synthetic */ class BackgroundDimmer$$Lambda$6 implements Action1 {
    private final BackgroundDimmer arg$1;
    private final Runnable arg$2;

    private BackgroundDimmer$$Lambda$6(BackgroundDimmer backgroundDimmer, Runnable runnable) {
        this.arg$1 = backgroundDimmer;
        this.arg$2 = runnable;
    }

    public static Action1 lambdaFactory$(BackgroundDimmer backgroundDimmer, Runnable runnable) {
        return new BackgroundDimmer$$Lambda$6(backgroundDimmer, runnable);
    }

    public void call(Object obj) {
        this.arg$1.undim(this.arg$2);
    }
}
