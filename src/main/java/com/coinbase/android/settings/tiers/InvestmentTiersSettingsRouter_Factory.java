package com.coinbase.android.settings.tiers;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class InvestmentTiersSettingsRouter_Factory implements Factory<InvestmentTiersSettingsRouter> {
    private final Provider<ActionBarController> controllerProvider;

    public InvestmentTiersSettingsRouter_Factory(Provider<ActionBarController> controllerProvider) {
        this.controllerProvider = controllerProvider;
    }

    public InvestmentTiersSettingsRouter get() {
        return provideInstance(this.controllerProvider);
    }

    public static InvestmentTiersSettingsRouter provideInstance(Provider<ActionBarController> controllerProvider) {
        return new InvestmentTiersSettingsRouter((ActionBarController) controllerProvider.get());
    }

    public static InvestmentTiersSettingsRouter_Factory create(Provider<ActionBarController> controllerProvider) {
        return new InvestmentTiersSettingsRouter_Factory(controllerProvider);
    }

    public static InvestmentTiersSettingsRouter newInvestmentTiersSettingsRouter(ActionBarController controller) {
        return new InvestmentTiersSettingsRouter(controller);
    }
}
