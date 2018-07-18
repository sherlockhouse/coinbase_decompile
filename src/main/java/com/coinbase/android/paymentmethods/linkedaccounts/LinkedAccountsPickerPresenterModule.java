package com.coinbase.android.paymentmethods.linkedaccounts;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class LinkedAccountsPickerPresenterModule {
    private final ActionBarController mController;
    private final LinkedAccountsPickerScreen mScreen;

    public LinkedAccountsPickerPresenterModule(LinkedAccountsPickerScreen screen, ActionBarController controller) {
        this.mScreen = screen;
        this.mController = controller;
    }

    @ControllerScope
    public LinkedAccountsPickerScreen providesLinkedAccountsPickerScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mController;
    }
}
