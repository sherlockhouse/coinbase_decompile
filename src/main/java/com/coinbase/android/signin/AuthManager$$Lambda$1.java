package com.coinbase.android.signin;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class AuthManager$$Lambda$1 implements Func1 {
    private final AuthManager arg$1;

    private AuthManager$$Lambda$1(AuthManager authManager) {
        this.arg$1 = authManager;
    }

    public static Func1 lambdaFactory$(AuthManager authManager) {
        return new AuthManager$$Lambda$1(authManager);
    }

    public Object call(Object obj) {
        return AuthManager.lambda$getAuthTypeForUser$0(this.arg$1, (Pair) obj);
    }
}
