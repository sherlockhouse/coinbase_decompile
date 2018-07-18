package com.coinbase.android.notifications.priceAlerts;

import rx.functions.Action1;

final /* synthetic */ class PriceAlertsPresenter$$Lambda$4 implements Action1 {
    private static final PriceAlertsPresenter$$Lambda$4 instance = new PriceAlertsPresenter$$Lambda$4();

    private PriceAlertsPresenter$$Lambda$4() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        PriceAlertsPresenter.lambda$createPriceAlert$3((Throwable) obj);
    }
}
