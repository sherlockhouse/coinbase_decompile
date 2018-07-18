package com.coinbase.android.signin;

import com.coinbase.android.event.ClassConsumableEvent;
import rx.functions.Func1;

final /* synthetic */ class LoginController$$Lambda$4 implements Func1 {
    private static final LoginController$$Lambda$4 instance = new LoginController$$Lambda$4();

    private LoginController$$Lambda$4() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return LoginController.lambda$onCreateView$3((ClassConsumableEvent) obj);
    }
}
