package com.coinbase.android.notifications.priceAlerts;

import com.coinbase.android.ControllerScope;

@ControllerScope
public interface PriceAlertsControllerSubcomponent {
    void inject(PriceAlertsController priceAlertsController);
}
