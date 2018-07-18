package com.coinbase.android.signin;

import rx.functions.Action1;

final /* synthetic */ class IntroActivity$$Lambda$7 implements Action1 {
    private final IntroActivity arg$1;

    private IntroActivity$$Lambda$7(IntroActivity introActivity) {
        this.arg$1 = introActivity;
    }

    public static Action1 lambdaFactory$(IntroActivity introActivity) {
        return new IntroActivity$$Lambda$7(introActivity);
    }

    public void call(Object obj) {
        this.arg$1.onAuthComplete(null);
    }
}
