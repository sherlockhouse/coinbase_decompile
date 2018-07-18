package com.coinbase.android;

import com.coinbase.android.notifications.priceAlerts.create.CreatePriceAlertActivity;
import com.coinbase.android.widgets.WidgetChooseAccountActivity;
import com.coinbase.android.widgets.WidgetChooseCurrencyActivity;

@ActivityScope
public interface GenericActivitySubcomponent {
    void inject(CreatePriceAlertActivity createPriceAlertActivity);

    void inject(WidgetChooseAccountActivity widgetChooseAccountActivity);

    void inject(WidgetChooseCurrencyActivity widgetChooseCurrencyActivity);
}
