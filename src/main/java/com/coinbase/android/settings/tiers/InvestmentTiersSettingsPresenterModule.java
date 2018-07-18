package com.coinbase.android.settings.tiers;

import android.app.Application;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.AutoResizeTextViewAdjustor;

public class InvestmentTiersSettingsPresenterModule {
    private final ActionBarController mController;
    private final InvestmentTiersSettingsScreen mScreen;

    public InvestmentTiersSettingsPresenterModule(InvestmentTiersSettingsController controller) {
        this.mScreen = controller;
        this.mController = controller;
    }

    @ControllerScope
    public InvestmentTiersSettingsScreen providesInvestmentTiersScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesController() {
        return this.mController;
    }

    @ControllerScope
    public AutoResizeTextViewAdjustor providesAutoResizeTextViewAdjustor(Application app) {
        return new AutoResizeTextViewAdjustor();
    }
}
