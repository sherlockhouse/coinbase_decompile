package com.coinbase.android.signin;

import rx.Observable.OnSubscribe;
import rx.Subscriber;

final /* synthetic */ class OAuthTask$$Lambda$1 implements OnSubscribe {
    private final OAuthTask arg$1;

    private OAuthTask$$Lambda$1(OAuthTask oAuthTask) {
        this.arg$1 = oAuthTask;
    }

    public static OnSubscribe lambdaFactory$(OAuthTask oAuthTask) {
        return new OAuthTask$$Lambda$1(oAuthTask);
    }

    public void call(Object obj) {
        OAuthTask.lambda$call$0(this.arg$1, (Subscriber) obj);
    }
}
