package com.coinbase.android.event;

import com.coinbase.android.ui.MystiqueListSelectorConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesMystiqueListSelectorConnectorFactory implements Factory<MystiqueListSelectorConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesMystiqueListSelectorConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public MystiqueListSelectorConnector get() {
        return provideInstance(this.module);
    }

    public static MystiqueListSelectorConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesMystiqueListSelectorConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesMystiqueListSelectorConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesMystiqueListSelectorConnectorFactory(module);
    }

    public static MystiqueListSelectorConnector proxyProvidesMystiqueListSelectorConnector(CoinbaseEventsModule instance) {
        return (MystiqueListSelectorConnector) Preconditions.checkNotNull(instance.providesMystiqueListSelectorConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
