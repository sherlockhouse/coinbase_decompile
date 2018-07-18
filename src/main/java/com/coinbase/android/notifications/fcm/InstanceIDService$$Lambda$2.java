package com.coinbase.android.notifications.fcm;

import android.util.Log;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import rx.functions.Action1;

final /* synthetic */ class InstanceIDService$$Lambda$2 implements Action1 {
    private static final InstanceIDService$$Lambda$2 instance = new InstanceIDService$$Lambda$2();

    private InstanceIDService$$Lambda$2() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        Answers.getInstance().logCustom((CustomEvent) new CustomEvent("sendRegistrationToServer").putCustomAttribute("exception", Log.getStackTraceString((Throwable) obj)));
    }
}
