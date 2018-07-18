package com.coinbase.android.signin.state;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class StateLockedPresenterModule {
    private final ActionBarController mActionBarController;
    private final StateLockedScreen mScreen;

    public StateLockedPresenterModule(StateLockedScreen screen, ActionBarController controller) {
        this.mScreen = screen;
        this.mActionBarController = controller;
    }

    @ControllerScope
    public StateLockedScreen providesStateLockedScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mActionBarController;
    }
}
