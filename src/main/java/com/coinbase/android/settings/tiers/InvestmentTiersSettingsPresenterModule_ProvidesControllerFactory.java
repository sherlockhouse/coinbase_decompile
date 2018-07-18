package com.coinbase.android.settings.tiers;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class InvestmentTiersSettingsPresenterModule_ProvidesControllerFactory implements Factory<ActionBarController> {
    private final InvestmentTiersSettingsPresenterModule module;

    public InvestmentTiersSettingsPresenterModule_ProvidesControllerFactory(InvestmentTiersSettingsPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(InvestmentTiersSettingsPresenterModule module) {
        return proxyProvidesController(module);
    }

    public static InvestmentTiersSettingsPresenterModule_ProvidesControllerFactory create(InvestmentTiersSettingsPresenterModule module) {
        return new InvestmentTiersSettingsPresenterModule_ProvidesControllerFactory(module);
    }

    public static ActionBarController proxyProvidesController(InvestmentTiersSettingsPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
