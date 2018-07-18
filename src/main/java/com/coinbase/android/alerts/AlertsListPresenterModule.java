package com.coinbase.android.alerts;

import com.coinbase.android.ControllerScope;
import com.coinbase.api.internal.models.alerts.Data;
import java.util.List;

public class AlertsListPresenterModule {
    private final List<Data> mAlertsData;
    private final AlertsContainerScreen mParentScreen;
    private final AlertsListScreen mScreen;

    public AlertsListPresenterModule(AlertsListScreen screen, List<Data> alertsData, AlertsContainerScreen parentScreen) {
        this.mScreen = screen;
        this.mAlertsData = alertsData;
        this.mParentScreen = parentScreen;
    }

    @ControllerScope
    AlertsListScreen providesScreen() {
        return this.mScreen;
    }

    @ControllerScope
    List<Data> providesAlertsData() {
        return this.mAlertsData;
    }

    @ControllerScope
    AlertsContainerScreen providesParentScreen() {
        return this.mParentScreen;
    }
}
