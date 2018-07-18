package com.coinbase.android.notifications.priceAlerts;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class PriceAlertsPresenterModule_ProvidesPriceAlertsScreenFactory implements Factory<PriceAlertsScreen> {
    private final PriceAlertsPresenterModule module;

    public PriceAlertsPresenterModule_ProvidesPriceAlertsScreenFactory(PriceAlertsPresenterModule module) {
        this.module = module;
    }

    public PriceAlertsScreen get() {
        return provideInstance(this.module);
    }

    public static PriceAlertsScreen provideInstance(PriceAlertsPresenterModule module) {
        return proxyProvidesPriceAlertsScreen(module);
    }

    public static PriceAlertsPresenterModule_ProvidesPriceAlertsScreenFactory create(PriceAlertsPresenterModule module) {
        return new PriceAlertsPresenterModule_ProvidesPriceAlertsScreenFactory(module);
    }

    public static PriceAlertsScreen proxyProvidesPriceAlertsScreen(PriceAlertsPresenterModule instance) {
        return (PriceAlertsScreen) Preconditions.checkNotNull(instance.providesPriceAlertsScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
