package com.coinbase.android.notifications.priceAlerts;

import com.coinbase.api.internal.models.currency.Data;
import rx.functions.Action1;

final /* synthetic */ class PriceAlertsPresenter$$Lambda$1 implements Action1 {
    private final PriceAlertsPresenter arg$1;

    private PriceAlertsPresenter$$Lambda$1(PriceAlertsPresenter priceAlertsPresenter) {
        this.arg$1 = priceAlertsPresenter;
    }

    public static Action1 lambdaFactory$(PriceAlertsPresenter priceAlertsPresenter) {
        return new PriceAlertsPresenter$$Lambda$1(priceAlertsPresenter);
    }

    public void call(Object obj) {
        this.arg$1.onCurrencyUpdated((Data) obj);
    }
}
