package com.coinbase.android.wbl;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class AvailableBalancePresenterModule_ProvidesInvestmentTiersScreenFactory implements Factory<AvailableBalanceScreen> {
    private final AvailableBalancePresenterModule module;

    public AvailableBalancePresenterModule_ProvidesInvestmentTiersScreenFactory(AvailableBalancePresenterModule module) {
        this.module = module;
    }

    public AvailableBalanceScreen get() {
        return provideInstance(this.module);
    }

    public static AvailableBalanceScreen provideInstance(AvailableBalancePresenterModule module) {
        return proxyProvidesInvestmentTiersScreen(module);
    }

    public static AvailableBalancePresenterModule_ProvidesInvestmentTiersScreenFactory create(AvailableBalancePresenterModule module) {
        return new AvailableBalancePresenterModule_ProvidesInvestmentTiersScreenFactory(module);
    }

    public static AvailableBalanceScreen proxyProvidesInvestmentTiersScreen(AvailableBalancePresenterModule instance) {
        return (AvailableBalanceScreen) Preconditions.checkNotNull(instance.providesInvestmentTiersScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
