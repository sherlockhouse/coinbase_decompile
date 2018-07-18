package com.coinbase.android.settings.tiers;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class InvestmentTiersRequirementsPresenterModule_ProvidesInvestmentTiersScreenFactory implements Factory<InvestmentTiersRequirementsScreen> {
    private final InvestmentTiersRequirementsPresenterModule module;

    public InvestmentTiersRequirementsPresenterModule_ProvidesInvestmentTiersScreenFactory(InvestmentTiersRequirementsPresenterModule module) {
        this.module = module;
    }

    public InvestmentTiersRequirementsScreen get() {
        return provideInstance(this.module);
    }

    public static InvestmentTiersRequirementsScreen provideInstance(InvestmentTiersRequirementsPresenterModule module) {
        return proxyProvidesInvestmentTiersScreen(module);
    }

    public static InvestmentTiersRequirementsPresenterModule_ProvidesInvestmentTiersScreenFactory create(InvestmentTiersRequirementsPresenterModule module) {
        return new InvestmentTiersRequirementsPresenterModule_ProvidesInvestmentTiersScreenFactory(module);
    }

    public static InvestmentTiersRequirementsScreen proxyProvidesInvestmentTiersScreen(InvestmentTiersRequirementsPresenterModule instance) {
        return (InvestmentTiersRequirementsScreen) Preconditions.checkNotNull(instance.providesInvestmentTiersScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
