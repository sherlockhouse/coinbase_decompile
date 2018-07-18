package com.coinbase.android.signin;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class TwoFactorPresenterModule {
    private final ActionBarController mController;
    private final TwoFactorScreen mScreen;

    public TwoFactorPresenterModule(TwoFactorScreen screen, ActionBarController controller) {
        this.mScreen = screen;
        this.mController = controller;
    }

    @ControllerScope
    public TwoFactorScreen providesScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesController() {
        return this.mController;
    }
}
