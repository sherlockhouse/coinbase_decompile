package com.coinbase.android.utils;

import com.coinbase.android.ui.CurrenciesUpdatedConnector;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class MoneyFormatterUtil_Factory implements Factory<MoneyFormatterUtil> {
    private final Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider;

    public MoneyFormatterUtil_Factory(Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider) {
        this.currenciesUpdatedConnectorProvider = currenciesUpdatedConnectorProvider;
    }

    public MoneyFormatterUtil get() {
        return provideInstance(this.currenciesUpdatedConnectorProvider);
    }

    public static MoneyFormatterUtil provideInstance(Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider) {
        return new MoneyFormatterUtil((CurrenciesUpdatedConnector) currenciesUpdatedConnectorProvider.get());
    }

    public static MoneyFormatterUtil_Factory create(Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider) {
        return new MoneyFormatterUtil_Factory(currenciesUpdatedConnectorProvider);
    }

    public static MoneyFormatterUtil newMoneyFormatterUtil(CurrenciesUpdatedConnector currenciesUpdatedConnector) {
        return new MoneyFormatterUtil(currenciesUpdatedConnector);
    }
}
