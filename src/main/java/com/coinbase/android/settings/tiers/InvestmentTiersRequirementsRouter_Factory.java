package com.coinbase.android.settings.tiers;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class InvestmentTiersRequirementsRouter_Factory implements Factory<InvestmentTiersRequirementsRouter> {
    private final Provider<ActionBarController> controllerProvider;

    public InvestmentTiersRequirementsRouter_Factory(Provider<ActionBarController> controllerProvider) {
        this.controllerProvider = controllerProvider;
    }

    public InvestmentTiersRequirementsRouter get() {
        return provideInstance(this.controllerProvider);
    }

    public static InvestmentTiersRequirementsRouter provideInstance(Provider<ActionBarController> controllerProvider) {
        return new InvestmentTiersRequirementsRouter((ActionBarController) controllerProvider.get());
    }

    public static InvestmentTiersRequirementsRouter_Factory create(Provider<ActionBarController> controllerProvider) {
        return new InvestmentTiersRequirementsRouter_Factory(controllerProvider);
    }

    public static InvestmentTiersRequirementsRouter newInvestmentTiersRequirementsRouter(ActionBarController controller) {
        return new InvestmentTiersRequirementsRouter(controller);
    }
}
