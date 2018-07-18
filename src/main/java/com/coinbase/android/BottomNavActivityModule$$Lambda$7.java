package com.coinbase.android;

import rx.functions.Func0;

final /* synthetic */ class BottomNavActivityModule$$Lambda$7 implements Func0 {
    private final BottomNavActivityModule arg$1;

    private BottomNavActivityModule$$Lambda$7(BottomNavActivityModule bottomNavActivityModule) {
        this.arg$1 = bottomNavActivityModule;
    }

    public static Func0 lambdaFactory$(BottomNavActivityModule bottomNavActivityModule) {
        return new BottomNavActivityModule$$Lambda$7(bottomNavActivityModule);
    }

    public Object call() {
        return this.arg$1.mModalViewProvider.getModalView();
    }
}
