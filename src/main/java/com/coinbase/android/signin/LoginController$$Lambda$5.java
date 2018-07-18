package com.coinbase.android.signin;

import com.coinbase.android.event.ClassConsumableEvent;
import rx.functions.Func1;

final /* synthetic */ class LoginController$$Lambda$5 implements Func1 {
    private static final LoginController$$Lambda$5 instance = new LoginController$$Lambda$5();

    private LoginController$$Lambda$5() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return ((Exception) ((ClassConsumableEvent) obj).get());
    }
}
