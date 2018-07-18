package com.coinbase.android.ui;

import android.view.View;
import rx.functions.Action0;

final /* synthetic */ class BackgroundDimmer$$Lambda$1 implements Action0 {
    private final BackgroundDimmer arg$1;
    private final View arg$2;
    private final Runnable arg$3;
    private final int arg$4;

    private BackgroundDimmer$$Lambda$1(BackgroundDimmer backgroundDimmer, View view, Runnable runnable, int i) {
        this.arg$1 = backgroundDimmer;
        this.arg$2 = view;
        this.arg$3 = runnable;
        this.arg$4 = i;
    }

    public static Action0 lambdaFactory$(BackgroundDimmer backgroundDimmer, View view, Runnable runnable, int i) {
        return new BackgroundDimmer$$Lambda$1(backgroundDimmer, view, runnable, i);
    }

    public void call() {
        this.arg$1.dim(this.arg$2, this.arg$3, this.arg$4);
    }
}
