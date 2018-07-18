package com.coinbase.android.settings.tiers;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class InvestmentTiersSettingsPresenterModule_ProvidesInvestmentTiersScreenFactory implements Factory<InvestmentTiersSettingsScreen> {
    private final InvestmentTiersSettingsPresenterModule module;

    public InvestmentTiersSettingsPresenterModule_ProvidesInvestmentTiersScreenFactory(InvestmentTiersSettingsPresenterModule module) {
        this.module = module;
    }

    public InvestmentTiersSettingsScreen get() {
        return provideInstance(this.module);
    }

    public static InvestmentTiersSettingsScreen provideInstance(InvestmentTiersSettingsPresenterModule module) {
        return proxyProvidesInvestmentTiersScreen(module);
    }

    public static InvestmentTiersSettingsPresenterModule_ProvidesInvestmentTiersScreenFactory create(InvestmentTiersSettingsPresenterModule module) {
        return new InvestmentTiersSettingsPresenterModule_ProvidesInvestmentTiersScreenFactory(module);
    }

    public static InvestmentTiersSettingsScreen proxyProvidesInvestmentTiersScreen(InvestmentTiersSettingsPresenterModule instance) {
        return (InvestmentTiersSettingsScreen) Preconditions.checkNotNull(instance.providesInvestmentTiersScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
