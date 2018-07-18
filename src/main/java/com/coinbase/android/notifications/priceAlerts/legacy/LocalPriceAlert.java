package com.coinbase.android.notifications.priceAlerts.legacy;

import com.coinbase.android.utils.MoneyUtils.Currency;
import org.joda.money.CurrencyUnit;

public class LocalPriceAlert {
    public String amount;
    public Currency baseCurrency;
    public long createdOn;
    public CurrencyUnit currencyUnit;
    public boolean enabled;
    public String id;
    public String isAbove;
    public String message;
    public long triggeredOn;
}
