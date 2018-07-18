package com.coinbase.android.alerts;

import com.coinbase.api.internal.models.alerts.Data;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;

public final class AlertsListPresenterModule_ProvidesAlertsDataFactory implements Factory<List<Data>> {
    private final AlertsListPresenterModule module;

    public AlertsListPresenterModule_ProvidesAlertsDataFactory(AlertsListPresenterModule module) {
        this.module = module;
    }

    public List<Data> get() {
        return provideInstance(this.module);
    }

    public static List<Data> provideInstance(AlertsListPresenterModule module) {
        return proxyProvidesAlertsData(module);
    }

    public static AlertsListPresenterModule_ProvidesAlertsDataFactory create(AlertsListPresenterModule module) {
        return new AlertsListPresenterModule_ProvidesAlertsDataFactory(module);
    }

    public static List<Data> proxyProvidesAlertsData(AlertsListPresenterModule instance) {
        return (List) Preconditions.checkNotNull(instance.providesAlertsData(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
