package com.coinbase.android.signin;

import android.content.Intent;
import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class IntroActivity$$Lambda$6 implements Action1 {
    private final IntroActivity arg$1;

    private IntroActivity$$Lambda$6(IntroActivity introActivity) {
        this.arg$1 = introActivity;
    }

    public static Action1 lambdaFactory$(IntroActivity introActivity) {
        return new IntroActivity$$Lambda$6(introActivity);
    }

    public void call(Object obj) {
        this.arg$1.onAuthComplete((Intent) ((Pair) obj).first);
    }
}
