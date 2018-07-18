package com.coinbase.android.event;

import com.coinbase.android.ui.MystiqueListButtonConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesMystiqueListButtonConnectorFactory implements Factory<MystiqueListButtonConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesMystiqueListButtonConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public MystiqueListButtonConnector get() {
        return provideInstance(this.module);
    }

    public static MystiqueListButtonConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesMystiqueListButtonConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesMystiqueListButtonConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesMystiqueListButtonConnectorFactory(module);
    }

    public static MystiqueListButtonConnector proxyProvidesMystiqueListButtonConnector(CoinbaseEventsModule instance) {
        return (MystiqueListButtonConnector) Preconditions.checkNotNull(instance.providesMystiqueListButtonConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
