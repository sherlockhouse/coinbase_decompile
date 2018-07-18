package com.coinbase.android.signin;

import rx.functions.Action1;

final /* synthetic */ class IntroActivity$$Lambda$4 implements Action1 {
    private final IntroActivity arg$1;
    private final boolean arg$2;

    private IntroActivity$$Lambda$4(IntroActivity introActivity, boolean z) {
        this.arg$1 = introActivity;
        this.arg$2 = z;
    }

    public static Action1 lambdaFactory$(IntroActivity introActivity, boolean z) {
        return new IntroActivity$$Lambda$4(introActivity, z);
    }

    public void call(Object obj) {
        this.arg$1.showJumioFromWebMessage(this.arg$2);
    }
}
