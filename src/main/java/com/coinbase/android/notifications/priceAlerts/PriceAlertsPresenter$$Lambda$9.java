package com.coinbase.android.notifications.priceAlerts;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class PriceAlertsPresenter$$Lambda$9 implements Action1 {
    private static final PriceAlertsPresenter$$Lambda$9 instance = new PriceAlertsPresenter$$Lambda$9();

    private PriceAlertsPresenter$$Lambda$9() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        PriceAlertsPresenter.lambda$registerGcmToken$8((Pair) obj);
    }
}
