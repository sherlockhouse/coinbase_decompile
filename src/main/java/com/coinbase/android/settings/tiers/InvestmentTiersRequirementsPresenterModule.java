package com.coinbase.android.settings.tiers;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class InvestmentTiersRequirementsPresenterModule {
    private final ActionBarController mController;
    private final InvestmentTiersRequirementsScreen mScreen;

    public InvestmentTiersRequirementsPresenterModule(InvestmentTiersRequirementsController controller) {
        this.mScreen = controller;
        this.mController = controller;
    }

    @ControllerScope
    public InvestmentTiersRequirementsScreen providesInvestmentTiersScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesController() {
        return this.mController;
    }
}
