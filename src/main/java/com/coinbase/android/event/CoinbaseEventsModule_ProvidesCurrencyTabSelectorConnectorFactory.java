package com.coinbase.android.event;

import com.coinbase.android.ui.CurrenciesUpdatedConnector;
import com.coinbase.android.ui.CurrencyTabSelectorConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoinbaseEventsModule_ProvidesCurrencyTabSelectorConnectorFactory implements Factory<CurrencyTabSelectorConnector> {
    private final Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider;
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesCurrencyTabSelectorConnectorFactory(CoinbaseEventsModule module, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider) {
        this.module = module;
        this.currenciesUpdatedConnectorProvider = currenciesUpdatedConnectorProvider;
    }

    public CurrencyTabSelectorConnector get() {
        return provideInstance(this.module, this.currenciesUpdatedConnectorProvider);
    }

    public static CurrencyTabSelectorConnector provideInstance(CoinbaseEventsModule module, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider) {
        return proxyProvidesCurrencyTabSelectorConnector(module, (CurrenciesUpdatedConnector) currenciesUpdatedConnectorProvider.get());
    }

    public static CoinbaseEventsModule_ProvidesCurrencyTabSelectorConnectorFactory create(CoinbaseEventsModule module, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider) {
        return new CoinbaseEventsModule_ProvidesCurrencyTabSelectorConnectorFactory(module, currenciesUpdatedConnectorProvider);
    }

    public static CurrencyTabSelectorConnector proxyProvidesCurrencyTabSelectorConnector(CoinbaseEventsModule instance, CurrenciesUpdatedConnector currenciesUpdatedConnector) {
        return (CurrencyTabSelectorConnector) Preconditions.checkNotNull(instance.providesCurrencyTabSelectorConnector(currenciesUpdatedConnector), "Cannot return null from a non-@Nullable @Provides method");
    }
}
