package com.coinbase.android.dashboard;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class DashboardTabPeriodPresenterModule_ProvidesDashboardTabPeriodScreenFactory implements Factory<DashboardTabPeriodScreen> {
    private final DashboardTabPeriodPresenterModule module;

    public DashboardTabPeriodPresenterModule_ProvidesDashboardTabPeriodScreenFactory(DashboardTabPeriodPresenterModule module) {
        this.module = module;
    }

    public DashboardTabPeriodScreen get() {
        return provideInstance(this.module);
    }

    public static DashboardTabPeriodScreen provideInstance(DashboardTabPeriodPresenterModule module) {
        return proxyProvidesDashboardTabPeriodScreen(module);
    }

    public static DashboardTabPeriodPresenterModule_ProvidesDashboardTabPeriodScreenFactory create(DashboardTabPeriodPresenterModule module) {
        return new DashboardTabPeriodPresenterModule_ProvidesDashboardTabPeriodScreenFactory(module);
    }

    public static DashboardTabPeriodScreen proxyProvidesDashboardTabPeriodScreen(DashboardTabPeriodPresenterModule instance) {
        return (DashboardTabPeriodScreen) Preconditions.checkNotNull(instance.providesDashboardTabPeriodScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
