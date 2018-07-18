package com.coinbase.android.event;

import com.coinbase.android.buysell.BuySellMadeConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesBuySellMadeConnectorFactory implements Factory<BuySellMadeConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesBuySellMadeConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public BuySellMadeConnector get() {
        return provideInstance(this.module);
    }

    public static BuySellMadeConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesBuySellMadeConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesBuySellMadeConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesBuySellMadeConnectorFactory(module);
    }

    public static BuySellMadeConnector proxyProvidesBuySellMadeConnector(CoinbaseEventsModule instance) {
        return (BuySellMadeConnector) Preconditions.checkNotNull(instance.providesBuySellMadeConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
