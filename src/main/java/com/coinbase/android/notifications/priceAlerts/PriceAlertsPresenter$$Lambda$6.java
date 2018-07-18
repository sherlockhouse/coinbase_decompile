package com.coinbase.android.notifications.priceAlerts;

import rx.functions.Action1;

final /* synthetic */ class PriceAlertsPresenter$$Lambda$6 implements Action1 {
    private static final PriceAlertsPresenter$$Lambda$6 instance = new PriceAlertsPresenter$$Lambda$6();

    private PriceAlertsPresenter$$Lambda$6() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        PriceAlertsPresenter.lambda$deletePriceAlert$5((Throwable) obj);
    }
}
