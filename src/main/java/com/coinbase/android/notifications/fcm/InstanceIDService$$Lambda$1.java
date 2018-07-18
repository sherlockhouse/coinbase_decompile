package com.coinbase.android.notifications.fcm;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class InstanceIDService$$Lambda$1 implements Action1 {
    private static final InstanceIDService$$Lambda$1 instance = new InstanceIDService$$Lambda$1();

    private InstanceIDService$$Lambda$1() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        InstanceIDService.lambda$sendRegistrationToServer$0((Pair) obj);
    }
}
