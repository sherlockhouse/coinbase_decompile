package com.coinbase.android.settings.tiers;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class InvestmentTiersRequirementsPresenterModule_ProvidesControllerFactory implements Factory<ActionBarController> {
    private final InvestmentTiersRequirementsPresenterModule module;

    public InvestmentTiersRequirementsPresenterModule_ProvidesControllerFactory(InvestmentTiersRequirementsPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(InvestmentTiersRequirementsPresenterModule module) {
        return proxyProvidesController(module);
    }

    public static InvestmentTiersRequirementsPresenterModule_ProvidesControllerFactory create(InvestmentTiersRequirementsPresenterModule module) {
        return new InvestmentTiersRequirementsPresenterModule_ProvidesControllerFactory(module);
    }

    public static ActionBarController proxyProvidesController(InvestmentTiersRequirementsPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
