package com.coinbase.android;

import com.coinbase.android.pin.PINManager;
import com.coinbase.android.settings.LocalUserDataUpdatedConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoinbaseApplicationModule_ProvidesPINManagerFactory implements Factory<PINManager> {
    private final Provider<LocalUserDataUpdatedConnector> connectorProvider;
    private final CoinbaseApplicationModule module;

    public CoinbaseApplicationModule_ProvidesPINManagerFactory(CoinbaseApplicationModule module, Provider<LocalUserDataUpdatedConnector> connectorProvider) {
        this.module = module;
        this.connectorProvider = connectorProvider;
    }

    public PINManager get() {
        return provideInstance(this.module, this.connectorProvider);
    }

    public static PINManager provideInstance(CoinbaseApplicationModule module, Provider<LocalUserDataUpdatedConnector> connectorProvider) {
        return proxyProvidesPINManager(module, (LocalUserDataUpdatedConnector) connectorProvider.get());
    }

    public static CoinbaseApplicationModule_ProvidesPINManagerFactory create(CoinbaseApplicationModule module, Provider<LocalUserDataUpdatedConnector> connectorProvider) {
        return new CoinbaseApplicationModule_ProvidesPINManagerFactory(module, connectorProvider);
    }

    public static PINManager proxyProvidesPINManager(CoinbaseApplicationModule instance, LocalUserDataUpdatedConnector connector) {
        return (PINManager) Preconditions.checkNotNull(instance.providesPINManager(connector), "Cannot return null from a non-@Nullable @Provides method");
    }
}
