package com.coinbase.android.notifications.priceAlerts;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class PriceAlertsPresenter$$Lambda$3 implements Action1 {
    private static final PriceAlertsPresenter$$Lambda$3 instance = new PriceAlertsPresenter$$Lambda$3();

    private PriceAlertsPresenter$$Lambda$3() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        PriceAlertsPresenter.lambda$createPriceAlert$2((Pair) obj);
    }
}
