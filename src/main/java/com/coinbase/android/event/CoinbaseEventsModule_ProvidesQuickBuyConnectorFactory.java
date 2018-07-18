package com.coinbase.android.event;

import com.coinbase.android.buysell.QuickBuyConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesQuickBuyConnectorFactory implements Factory<QuickBuyConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesQuickBuyConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public QuickBuyConnector get() {
        return provideInstance(this.module);
    }

    public static QuickBuyConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesQuickBuyConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesQuickBuyConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesQuickBuyConnectorFactory(module);
    }

    public static QuickBuyConnector proxyProvidesQuickBuyConnector(CoinbaseEventsModule instance) {
        return (QuickBuyConnector) Preconditions.checkNotNull(instance.providesQuickBuyConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
