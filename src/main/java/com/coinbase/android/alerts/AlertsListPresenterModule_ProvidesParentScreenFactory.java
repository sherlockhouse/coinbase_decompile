package com.coinbase.android.alerts;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class AlertsListPresenterModule_ProvidesParentScreenFactory implements Factory<AlertsContainerScreen> {
    private final AlertsListPresenterModule module;

    public AlertsListPresenterModule_ProvidesParentScreenFactory(AlertsListPresenterModule module) {
        this.module = module;
    }

    public AlertsContainerScreen get() {
        return provideInstance(this.module);
    }

    public static AlertsContainerScreen provideInstance(AlertsListPresenterModule module) {
        return proxyProvidesParentScreen(module);
    }

    public static AlertsListPresenterModule_ProvidesParentScreenFactory create(AlertsListPresenterModule module) {
        return new AlertsListPresenterModule_ProvidesParentScreenFactory(module);
    }

    public static AlertsContainerScreen proxyProvidesParentScreen(AlertsListPresenterModule instance) {
        return (AlertsContainerScreen) Preconditions.checkNotNull(instance.providesParentScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
