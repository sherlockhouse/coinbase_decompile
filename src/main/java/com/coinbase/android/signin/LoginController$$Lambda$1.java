package com.coinbase.android.signin;

import com.coinbase.android.event.ClassConsumableEvent;
import rx.functions.Func1;

final /* synthetic */ class LoginController$$Lambda$1 implements Func1 {
    private static final LoginController$$Lambda$1 instance = new LoginController$$Lambda$1();

    private LoginController$$Lambda$1() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return LoginController.lambda$onCreateView$0((ClassConsumableEvent) obj);
    }
}
