package com.coinbase.android.event;

import com.coinbase.android.identityverification.IdentityVerificationBitmapConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesIdentityVerificationBitmapConnectorFactory implements Factory<IdentityVerificationBitmapConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesIdentityVerificationBitmapConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public IdentityVerificationBitmapConnector get() {
        return provideInstance(this.module);
    }

    public static IdentityVerificationBitmapConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesIdentityVerificationBitmapConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesIdentityVerificationBitmapConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesIdentityVerificationBitmapConnectorFactory(module);
    }

    public static IdentityVerificationBitmapConnector proxyProvidesIdentityVerificationBitmapConnector(CoinbaseEventsModule instance) {
        return (IdentityVerificationBitmapConnector) Preconditions.checkNotNull(instance.providesIdentityVerificationBitmapConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
