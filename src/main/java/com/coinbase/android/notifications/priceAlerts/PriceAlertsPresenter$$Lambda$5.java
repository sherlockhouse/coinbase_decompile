package com.coinbase.android.notifications.priceAlerts;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class PriceAlertsPresenter$$Lambda$5 implements Action1 {
    private static final PriceAlertsPresenter$$Lambda$5 instance = new PriceAlertsPresenter$$Lambda$5();

    private PriceAlertsPresenter$$Lambda$5() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        PriceAlertsPresenter.lambda$deletePriceAlert$4((Pair) obj);
    }
}
