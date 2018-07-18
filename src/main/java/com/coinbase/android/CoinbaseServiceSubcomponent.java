package com.coinbase.android;

import com.coinbase.android.notifications.fcm.InstanceIDService;
import com.coinbase.android.transfers.DelayedTxSenderService;
import com.coinbase.android.widgets.UpdateWidgetBalanceService;
import com.coinbase.android.widgets.UpdateWidgetPriceService;

@ActivityScope
public interface CoinbaseServiceSubcomponent {
    void inject(BalanceAppWidgetProvider balanceAppWidgetProvider);

    void inject(PriceAppWidgetProvider priceAppWidgetProvider);

    void inject(InstanceIDService instanceIDService);

    void inject(DelayedTxSenderService delayedTxSenderService);

    void inject(UpdateWidgetBalanceService updateWidgetBalanceService);

    void inject(UpdateWidgetPriceService updateWidgetPriceService);
}
