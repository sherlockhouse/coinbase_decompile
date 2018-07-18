package com.coinbase.android.paymentmethods;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class AddBankErrorPresenterModule {
    private final AddBankErrorScreen mScreen;

    public AddBankErrorPresenterModule(AddBankErrorScreen screen) {
        this.mScreen = screen;
    }

    @ControllerScope
    public AddBankErrorScreen providesAddBankErrorScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mScreen.getController();
    }
}
