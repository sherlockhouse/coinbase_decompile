package com.coinbase.android.event;

import com.coinbase.android.buysell.Buy3dsVerificationConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesBuy3dsVerificationConnectorFactory implements Factory<Buy3dsVerificationConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesBuy3dsVerificationConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public Buy3dsVerificationConnector get() {
        return provideInstance(this.module);
    }

    public static Buy3dsVerificationConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesBuy3dsVerificationConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesBuy3dsVerificationConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesBuy3dsVerificationConnectorFactory(module);
    }

    public static Buy3dsVerificationConnector proxyProvidesBuy3dsVerificationConnector(CoinbaseEventsModule instance) {
        return (Buy3dsVerificationConnector) Preconditions.checkNotNull(instance.providesBuy3dsVerificationConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
