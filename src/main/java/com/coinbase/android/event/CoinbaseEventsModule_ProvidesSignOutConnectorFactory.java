package com.coinbase.android.event;

import com.coinbase.android.ui.SignOutConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesSignOutConnectorFactory implements Factory<SignOutConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesSignOutConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public SignOutConnector get() {
        return provideInstance(this.module);
    }

    public static SignOutConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesSignOutConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesSignOutConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesSignOutConnectorFactory(module);
    }

    public static SignOutConnector proxyProvidesSignOutConnector(CoinbaseEventsModule instance) {
        return (SignOutConnector) Preconditions.checkNotNull(instance.providesSignOutConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
