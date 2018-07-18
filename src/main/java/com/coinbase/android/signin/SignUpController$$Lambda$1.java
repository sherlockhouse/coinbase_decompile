package com.coinbase.android.signin;

import com.coinbase.android.event.ClassConsumableEvent;
import rx.functions.Func1;

final /* synthetic */ class SignUpController$$Lambda$1 implements Func1 {
    private static final SignUpController$$Lambda$1 instance = new SignUpController$$Lambda$1();

    private SignUpController$$Lambda$1() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return SignUpController.lambda$onCreateView$0((ClassConsumableEvent) obj);
    }
}
