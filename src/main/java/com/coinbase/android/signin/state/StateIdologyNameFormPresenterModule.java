package com.coinbase.android.signin.state;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class StateIdologyNameFormPresenterModule {
    private final ActionBarController mActionBarController;
    private final StateIdologyFormScreen mScreen;

    public StateIdologyNameFormPresenterModule(StateIdologyFormScreen screen, ActionBarController controller) {
        this.mScreen = screen;
        this.mActionBarController = controller;
    }

    @ControllerScope
    public StateIdologyFormScreen providesStateIdologyNameFormScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mActionBarController;
    }
}
