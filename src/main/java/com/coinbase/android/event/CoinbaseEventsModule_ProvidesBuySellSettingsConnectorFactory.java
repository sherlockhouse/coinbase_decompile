package com.coinbase.android.event;

import com.coinbase.android.settings.GoToSettingsConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesBuySellSettingsConnectorFactory implements Factory<GoToSettingsConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesBuySellSettingsConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public GoToSettingsConnector get() {
        return provideInstance(this.module);
    }

    public static GoToSettingsConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesBuySellSettingsConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesBuySellSettingsConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesBuySellSettingsConnectorFactory(module);
    }

    public static GoToSettingsConnector proxyProvidesBuySellSettingsConnector(CoinbaseEventsModule instance) {
        return (GoToSettingsConnector) Preconditions.checkNotNull(instance.providesBuySellSettingsConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
