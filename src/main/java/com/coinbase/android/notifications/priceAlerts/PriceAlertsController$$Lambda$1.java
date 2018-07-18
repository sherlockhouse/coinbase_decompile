package com.coinbase.android.notifications.priceAlerts;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class PriceAlertsController$$Lambda$1 implements OnClickListener {
    private final PriceAlertsController arg$1;

    private PriceAlertsController$$Lambda$1(PriceAlertsController priceAlertsController) {
        this.arg$1 = priceAlertsController;
    }

    public static OnClickListener lambdaFactory$(PriceAlertsController priceAlertsController) {
        return new PriceAlertsController$$Lambda$1(priceAlertsController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onCreatePriceAlertClicked();
    }
}
