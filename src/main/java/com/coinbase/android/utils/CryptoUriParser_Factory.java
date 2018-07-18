package com.coinbase.android.utils;

import com.coinbase.android.ui.CurrenciesUpdatedConnector;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class CryptoUriParser_Factory implements Factory<CryptoUriParser> {
    private final Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider;

    public CryptoUriParser_Factory(Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider) {
        this.currenciesUpdatedConnectorProvider = currenciesUpdatedConnectorProvider;
    }

    public CryptoUriParser get() {
        return provideInstance(this.currenciesUpdatedConnectorProvider);
    }

    public static CryptoUriParser provideInstance(Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider) {
        return new CryptoUriParser((CurrenciesUpdatedConnector) currenciesUpdatedConnectorProvider.get());
    }

    public static CryptoUriParser_Factory create(Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider) {
        return new CryptoUriParser_Factory(currenciesUpdatedConnectorProvider);
    }

    public static CryptoUriParser newCryptoUriParser(CurrenciesUpdatedConnector currenciesUpdatedConnector) {
        return new CryptoUriParser(currenciesUpdatedConnector);
    }
}
