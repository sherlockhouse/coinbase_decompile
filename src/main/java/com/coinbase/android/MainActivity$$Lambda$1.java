package com.coinbase.android;

import rx.functions.Action1;

final /* synthetic */ class MainActivity$$Lambda$1 implements Action1 {
    private final MainActivity arg$1;

    private MainActivity$$Lambda$1(MainActivity mainActivity) {
        this.arg$1 = mainActivity;
    }

    public static Action1 lambdaFactory$(MainActivity mainActivity) {
        return new MainActivity$$Lambda$1(mainActivity);
    }

    public void call(Object obj) {
        this.arg$1.onRefreshRequested();
    }
}
