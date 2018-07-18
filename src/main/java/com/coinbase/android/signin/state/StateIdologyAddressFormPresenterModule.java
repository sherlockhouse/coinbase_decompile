package com.coinbase.android.signin.state;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class StateIdologyAddressFormPresenterModule {
    private final ActionBarController mActionBarController;
    private final StateIdologyAddressFormScreen mScreen;

    public StateIdologyAddressFormPresenterModule(StateIdologyAddressFormScreen screen, ActionBarController controller) {
        this.mScreen = screen;
        this.mActionBarController = controller;
    }

    @ControllerScope
    public StateIdologyAddressFormScreen providesStateIdologyAddressFormScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mActionBarController;
    }
}
