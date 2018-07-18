package com.coinbase.android.notifications.priceAlerts;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class PriceAlertsPresenter$$Lambda$7 implements Action1 {
    private final PriceAlertsPresenter arg$1;

    private PriceAlertsPresenter$$Lambda$7(PriceAlertsPresenter priceAlertsPresenter) {
        this.arg$1 = priceAlertsPresenter;
    }

    public static Action1 lambdaFactory$(PriceAlertsPresenter priceAlertsPresenter) {
        return new PriceAlertsPresenter$$Lambda$7(priceAlertsPresenter);
    }

    public void call(Object obj) {
        PriceAlertsPresenter.lambda$getSpotPrice$6(this.arg$1, (Pair) obj);
    }
}
