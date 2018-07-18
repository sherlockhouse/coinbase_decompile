package com.coinbase.android.signin;

import rx.functions.Func1;

final /* synthetic */ class AuthCompletionFactory$LoginCompletionHandler$$Lambda$1 implements Func1 {
    private final LoginCompletionHandler arg$1;

    private AuthCompletionFactory$LoginCompletionHandler$$Lambda$1(LoginCompletionHandler loginCompletionHandler) {
        this.arg$1 = loginCompletionHandler;
    }

    public static Func1 lambdaFactory$(LoginCompletionHandler loginCompletionHandler) {
        return new AuthCompletionFactory$LoginCompletionHandler$$Lambda$1(loginCompletionHandler);
    }

    public Object call(Object obj) {
        return this.arg$1.this$0.proceed();
    }
}
