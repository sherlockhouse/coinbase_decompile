package com.coinbase.android.event;

import com.coinbase.android.signin.LoginSignUpFinishedConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesTwoFactorConnectorFactory implements Factory<LoginSignUpFinishedConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesTwoFactorConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public LoginSignUpFinishedConnector get() {
        return provideInstance(this.module);
    }

    public static LoginSignUpFinishedConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesTwoFactorConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesTwoFactorConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesTwoFactorConnectorFactory(module);
    }

    public static LoginSignUpFinishedConnector proxyProvidesTwoFactorConnector(CoinbaseEventsModule instance) {
        return (LoginSignUpFinishedConnector) Preconditions.checkNotNull(instance.providesTwoFactorConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
