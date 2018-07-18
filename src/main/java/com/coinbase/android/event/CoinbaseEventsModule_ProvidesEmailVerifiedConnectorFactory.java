package com.coinbase.android.event;

import com.coinbase.android.signin.EmailVerifiedConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesEmailVerifiedConnectorFactory implements Factory<EmailVerifiedConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesEmailVerifiedConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public EmailVerifiedConnector get() {
        return provideInstance(this.module);
    }

    public static EmailVerifiedConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesEmailVerifiedConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesEmailVerifiedConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesEmailVerifiedConnectorFactory(module);
    }

    public static EmailVerifiedConnector proxyProvidesEmailVerifiedConnector(CoinbaseEventsModule instance) {
        return (EmailVerifiedConnector) Preconditions.checkNotNull(instance.providesEmailVerifiedConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
