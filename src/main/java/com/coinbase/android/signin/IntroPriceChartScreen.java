package com.coinbase.android.signin;

import com.coinbase.android.utils.MoneyUtils.Currency;

public interface IntroPriceChartScreen {
    void setSelectedPrice(String str, String str2, String str3);

    void setSpotPrice(String str, String str2, String str3, boolean z, String str4);

    void showCurrentPrice(boolean z);

    void updateBaseCurrencyViews(Currency currency);
}
