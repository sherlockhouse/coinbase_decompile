package com.coinbase.android.notifications.priceAlerts;

import android.util.Log;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import rx.functions.Action1;

final /* synthetic */ class PriceAlertsPresenter$$Lambda$10 implements Action1 {
    private static final PriceAlertsPresenter$$Lambda$10 instance = new PriceAlertsPresenter$$Lambda$10();

    private PriceAlertsPresenter$$Lambda$10() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        Answers.getInstance().logCustom((CustomEvent) new CustomEvent("registerGcmToken").putCustomAttribute("exception", Log.getStackTraceString((Throwable) obj)));
    }
}
