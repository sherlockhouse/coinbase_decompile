package com.coinbase.android.signin;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class IntroActivity$$Lambda$5 implements Func1 {
    private final IntroActivity arg$1;

    private IntroActivity$$Lambda$5(IntroActivity introActivity) {
        this.arg$1 = introActivity;
    }

    public static Func1 lambdaFactory$(IntroActivity introActivity) {
        return new IntroActivity$$Lambda$5(introActivity);
    }

    public Object call(Object obj) {
        return this.arg$1.authIntentManager.getAuthIntentForResult((Pair) obj);
    }
}
