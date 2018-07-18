package com.coinbase.android.paymentmethods;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class AddBankPickerPresenterModule {
    private final AddBankPickerScreen mScreen;

    public AddBankPickerPresenterModule(AddBankPickerScreen screen) {
        this.mScreen = screen;
    }

    @ControllerScope
    public AddBankPickerScreen providesAddBankPickerScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mScreen.getController();
    }
}
