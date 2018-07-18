package com.coinbase.api;

import android.content.SharedPreferences.Editor;
import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class LoginManager$$Lambda$3 implements Action1 {
    private final LoginManager arg$1;
    private final Editor arg$2;

    private LoginManager$$Lambda$3(LoginManager loginManager, Editor editor) {
        this.arg$1 = loginManager;
        this.arg$2 = editor;
    }

    public static Action1 lambdaFactory$(LoginManager loginManager, Editor editor) {
        return new LoginManager$$Lambda$3(loginManager, editor);
    }

    public void call(Object obj) {
        LoginManager.lambda$updateAccounts$3(this.arg$1, this.arg$2, (Pair) obj);
    }
}
