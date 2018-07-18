package com.coinbase.android;

import android.widget.RelativeLayout;
import rx.functions.Func0;

final /* synthetic */ class NonBottomNavActivityModule$$Lambda$1 implements Func0 {
    private final Func0 arg$1;

    private NonBottomNavActivityModule$$Lambda$1(Func0 func0) {
        this.arg$1 = func0;
    }

    public static Func0 lambdaFactory$(Func0 func0) {
        return new NonBottomNavActivityModule$$Lambda$1(func0);
    }

    public Object call() {
        return ((RelativeLayout) this.arg$1.call());
    }
}
