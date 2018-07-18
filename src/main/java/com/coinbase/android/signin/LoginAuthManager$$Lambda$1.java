package com.coinbase.android.signin;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class LoginAuthManager$$Lambda$1 implements Func1 {
    private final LoginAuthManager arg$1;
    private final String arg$2;
    private final String arg$3;

    private LoginAuthManager$$Lambda$1(LoginAuthManager loginAuthManager, String str, String str2) {
        this.arg$1 = loginAuthManager;
        this.arg$2 = str;
        this.arg$3 = str2;
    }

    public static Func1 lambdaFactory$(LoginAuthManager loginAuthManager, String str, String str2) {
        return new LoginAuthManager$$Lambda$1(loginAuthManager, str, str2);
    }

    public Object call(Object obj) {
        return LoginAuthManager.lambda$getAuthTypeForLogin$0(this.arg$1, this.arg$2, this.arg$3, (Pair) obj);
    }
}
