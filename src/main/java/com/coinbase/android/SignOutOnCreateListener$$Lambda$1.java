package com.coinbase.android;

import rx.functions.Action1;

final /* synthetic */ class SignOutOnCreateListener$$Lambda$1 implements Action1 {
    private final SignOutOnCreateListener arg$1;

    private SignOutOnCreateListener$$Lambda$1(SignOutOnCreateListener signOutOnCreateListener) {
        this.arg$1 = signOutOnCreateListener;
    }

    public static Action1 lambdaFactory$(SignOutOnCreateListener signOutOnCreateListener) {
        return new SignOutOnCreateListener$$Lambda$1(signOutOnCreateListener);
    }

    public void call(Object obj) {
        SignOutOnCreateListener.lambda$onCreate$0(this.arg$1, (Void) obj);
    }
}
