package com.coinbase.android.signin;

import com.coinbase.android.event.ClassConsumableEvent;
import com.coinbase.android.signin.EmailVerifiedConnector.Type;
import rx.functions.Func1;

final /* synthetic */ class LoginController$$Lambda$2 implements Func1 {
    private static final LoginController$$Lambda$2 instance = new LoginController$$Lambda$2();

    private LoginController$$Lambda$2() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return ((Type) ((ClassConsumableEvent) obj).get());
    }
}
