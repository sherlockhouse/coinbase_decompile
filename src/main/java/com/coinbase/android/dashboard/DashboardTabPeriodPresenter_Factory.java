package com.coinbase.android.dashboard;

import dagger.internal.Factory;
import javax.inject.Provider;

public final class DashboardTabPeriodPresenter_Factory implements Factory<DashboardTabPeriodPresenter> {
    private final Provider<DashboardTabPeriodScreen> screenProvider;
    private final Provider<DashboardTabPeriodSelectionConnector> tabPeriodSelectionConnectorProvider;

    public DashboardTabPeriodPresenter_Factory(Provider<DashboardTabPeriodScreen> screenProvider, Provider<DashboardTabPeriodSelectionConnector> tabPeriodSelectionConnectorProvider) {
        this.screenProvider = screenProvider;
        this.tabPeriodSelectionConnectorProvider = tabPeriodSelectionConnectorProvider;
    }

    public DashboardTabPeriodPresenter get() {
        return provideInstance(this.screenProvider, this.tabPeriodSelectionConnectorProvider);
    }

    public static DashboardTabPeriodPresenter provideInstance(Provider<DashboardTabPeriodScreen> screenProvider, Provider<DashboardTabPeriodSelectionConnector> tabPeriodSelectionConnectorProvider) {
        return new DashboardTabPeriodPresenter((DashboardTabPeriodScreen) screenProvider.get(), (DashboardTabPeriodSelectionConnector) tabPeriodSelectionConnectorProvider.get());
    }

    public static DashboardTabPeriodPresenter_Factory create(Provider<DashboardTabPeriodScreen> screenProvider, Provider<DashboardTabPeriodSelectionConnector> tabPeriodSelectionConnectorProvider) {
        return new DashboardTabPeriodPresenter_Factory(screenProvider, tabPeriodSelectionConnectorProvider);
    }

    public static DashboardTabPeriodPresenter newDashboardTabPeriodPresenter(DashboardTabPeriodScreen screen, DashboardTabPeriodSelectionConnector tabPeriodSelectionConnector) {
        return new DashboardTabPeriodPresenter(screen, tabPeriodSelectionConnector);
    }
}
