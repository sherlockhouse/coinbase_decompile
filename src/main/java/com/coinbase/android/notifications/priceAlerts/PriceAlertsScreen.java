package com.coinbase.android.notifications.priceAlerts;

import com.coinbase.api.internal.models.currency.Data;

interface PriceAlertsScreen {
    void notifyDataSetChanged();

    void showCurrentPrice(String str);

    void showCurrentPriceNotAvailable();

    void updateCurrencyView(Data data);
}
