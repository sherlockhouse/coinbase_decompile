package com.coinbase.android.event;

import com.coinbase.android.idology.IdologyVerificationConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesIdologyVerificationConnectorFactory implements Factory<IdologyVerificationConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesIdologyVerificationConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public IdologyVerificationConnector get() {
        return provideInstance(this.module);
    }

    public static IdologyVerificationConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesIdologyVerificationConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesIdologyVerificationConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesIdologyVerificationConnectorFactory(module);
    }

    public static IdologyVerificationConnector proxyProvidesIdologyVerificationConnector(CoinbaseEventsModule instance) {
        return (IdologyVerificationConnector) Preconditions.checkNotNull(instance.providesIdologyVerificationConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
