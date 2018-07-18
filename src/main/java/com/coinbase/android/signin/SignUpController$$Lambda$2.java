package com.coinbase.android.signin;

import com.coinbase.android.event.ClassConsumableEvent;
import com.coinbase.android.signin.EmailVerifiedConnector.Type;
import rx.functions.Func1;

final /* synthetic */ class SignUpController$$Lambda$2 implements Func1 {
    private static final SignUpController$$Lambda$2 instance = new SignUpController$$Lambda$2();

    private SignUpController$$Lambda$2() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return ((Type) ((ClassConsumableEvent) obj).get());
    }
}
