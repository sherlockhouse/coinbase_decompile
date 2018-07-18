package com.coinbase.android.dashboard;

import com.coinbase.v2.models.account.Data;
import org.joda.money.CurrencyUnit;

public interface DashboardCurrencyScreen {
    void gotoAccountTransactions(Data data, com.coinbase.api.internal.models.currency.Data data2);

    void setCurrencyUnit(CurrencyUnit currencyUnit);

    void setScrollingEnabled(Boolean bool);

    void setSelectedPrice(String str, String str2);

    void setSpotPrice(String str, String str2, int i);

    void showCurrentPrice(boolean z);
}
