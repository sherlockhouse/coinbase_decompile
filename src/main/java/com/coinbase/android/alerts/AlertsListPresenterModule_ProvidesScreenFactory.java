package com.coinbase.android.alerts;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class AlertsListPresenterModule_ProvidesScreenFactory implements Factory<AlertsListScreen> {
    private final AlertsListPresenterModule module;

    public AlertsListPresenterModule_ProvidesScreenFactory(AlertsListPresenterModule module) {
        this.module = module;
    }

    public AlertsListScreen get() {
        return provideInstance(this.module);
    }

    public static AlertsListScreen provideInstance(AlertsListPresenterModule module) {
        return proxyProvidesScreen(module);
    }

    public static AlertsListPresenterModule_ProvidesScreenFactory create(AlertsListPresenterModule module) {
        return new AlertsListPresenterModule_ProvidesScreenFactory(module);
    }

    public static AlertsListScreen proxyProvidesScreen(AlertsListPresenterModule instance) {
        return (AlertsListScreen) Preconditions.checkNotNull(instance.providesScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
