package com.coinbase.android.signin;

import rx.functions.Action0;
import rx.functions.Action1;

final /* synthetic */ class IntroActivity$$Lambda$3 implements Action1 {
    private final IntroActivity arg$1;
    private final boolean arg$2;

    private IntroActivity$$Lambda$3(IntroActivity introActivity, boolean z) {
        this.arg$1 = introActivity;
        this.arg$2 = z;
    }

    public static Action1 lambdaFactory$(IntroActivity introActivity, boolean z) {
        return new IntroActivity$$Lambda$3(introActivity, z);
    }

    public void call(Object obj) {
        IntroActivity.lambda$checkConfigForIdentityVerficiation$2(this.arg$1, this.arg$2, (Action0) obj);
    }
}
