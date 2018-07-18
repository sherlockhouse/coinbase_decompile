package com.coinbase.android.dashboard;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class DashboardMainPresenterModule_ProvideDashboardPresenterModuleFactory implements Factory<DashboardMainScreen> {
    private final DashboardMainPresenterModule module;

    public DashboardMainPresenterModule_ProvideDashboardPresenterModuleFactory(DashboardMainPresenterModule module) {
        this.module = module;
    }

    public DashboardMainScreen get() {
        return provideInstance(this.module);
    }

    public static DashboardMainScreen provideInstance(DashboardMainPresenterModule module) {
        return proxyProvideDashboardPresenterModule(module);
    }

    public static DashboardMainPresenterModule_ProvideDashboardPresenterModuleFactory create(DashboardMainPresenterModule module) {
        return new DashboardMainPresenterModule_ProvideDashboardPresenterModuleFactory(module);
    }

    public static DashboardMainScreen proxyProvideDashboardPresenterModule(DashboardMainPresenterModule instance) {
        return (DashboardMainScreen) Preconditions.checkNotNull(instance.provideDashboardPresenterModule(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
