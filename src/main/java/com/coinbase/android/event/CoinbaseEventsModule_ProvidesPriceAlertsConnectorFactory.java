package com.coinbase.android.event;

import com.coinbase.android.notifications.priceAlerts.PriceAlertsConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesPriceAlertsConnectorFactory implements Factory<PriceAlertsConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesPriceAlertsConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public PriceAlertsConnector get() {
        return provideInstance(this.module);
    }

    public static PriceAlertsConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesPriceAlertsConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesPriceAlertsConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesPriceAlertsConnectorFactory(module);
    }

    public static PriceAlertsConnector proxyProvidesPriceAlertsConnector(CoinbaseEventsModule instance) {
        return (PriceAlertsConnector) Preconditions.checkNotNull(instance.providesPriceAlertsConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
