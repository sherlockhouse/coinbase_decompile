package com.coinbase.android.signin;

import rx.functions.Func1;

final /* synthetic */ class AuthCompletionFactory$SignUpCompletionHandler$$Lambda$1 implements Func1 {
    private final SignUpCompletionHandler arg$1;

    private AuthCompletionFactory$SignUpCompletionHandler$$Lambda$1(SignUpCompletionHandler signUpCompletionHandler) {
        this.arg$1 = signUpCompletionHandler;
    }

    public static Func1 lambdaFactory$(SignUpCompletionHandler signUpCompletionHandler) {
        return new AuthCompletionFactory$SignUpCompletionHandler$$Lambda$1(signUpCompletionHandler);
    }

    public Object call(Object obj) {
        return this.arg$1.this$0.proceed();
    }
}
