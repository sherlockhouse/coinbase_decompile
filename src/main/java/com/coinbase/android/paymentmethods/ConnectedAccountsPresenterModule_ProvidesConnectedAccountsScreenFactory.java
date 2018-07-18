package com.coinbase.android.paymentmethods;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ConnectedAccountsPresenterModule_ProvidesConnectedAccountsScreenFactory implements Factory<ConnectedAccountsScreen> {
    private final ConnectedAccountsPresenterModule module;

    public ConnectedAccountsPresenterModule_ProvidesConnectedAccountsScreenFactory(ConnectedAccountsPresenterModule module) {
        this.module = module;
    }

    public ConnectedAccountsScreen get() {
        return provideInstance(this.module);
    }

    public static ConnectedAccountsScreen provideInstance(ConnectedAccountsPresenterModule module) {
        return proxyProvidesConnectedAccountsScreen(module);
    }

    public static ConnectedAccountsPresenterModule_ProvidesConnectedAccountsScreenFactory create(ConnectedAccountsPresenterModule module) {
        return new ConnectedAccountsPresenterModule_ProvidesConnectedAccountsScreenFactory(module);
    }

    public static ConnectedAccountsScreen proxyProvidesConnectedAccountsScreen(ConnectedAccountsPresenterModule instance) {
        return (ConnectedAccountsScreen) Preconditions.checkNotNull(instance.providesConnectedAccountsScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
