package com.coinbase.android.dashboard;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class DashboardCurrencyPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final DashboardCurrencyPresenterModule module;

    public DashboardCurrencyPresenterModule_ProvidesActionBarControllerFactory(DashboardCurrencyPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(DashboardCurrencyPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static DashboardCurrencyPresenterModule_ProvidesActionBarControllerFactory create(DashboardCurrencyPresenterModule module) {
        return new DashboardCurrencyPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(DashboardCurrencyPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
