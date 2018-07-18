package com.coinbase.android.paymentmethods;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class AddPlaidAccountPresenterModule {
    private final ActionBarController mController;
    private final AddPlaidAccountScreen mScreen;

    public AddPlaidAccountPresenterModule(AddPlaidAccountController controller) {
        this.mScreen = controller;
        this.mController = controller;
    }

    @ControllerScope
    AddPlaidAccountScreen providesAddPlaidAccountScreen() {
        return this.mScreen;
    }

    @ControllerScope
    ActionBarController providesActionBarController() {
        return this.mController;
    }
}
