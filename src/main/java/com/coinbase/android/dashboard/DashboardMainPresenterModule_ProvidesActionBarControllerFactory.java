package com.coinbase.android.dashboard;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class DashboardMainPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final DashboardMainPresenterModule module;

    public DashboardMainPresenterModule_ProvidesActionBarControllerFactory(DashboardMainPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(DashboardMainPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static DashboardMainPresenterModule_ProvidesActionBarControllerFactory create(DashboardMainPresenterModule module) {
        return new DashboardMainPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(DashboardMainPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
