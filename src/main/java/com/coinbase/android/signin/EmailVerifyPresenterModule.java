package com.coinbase.android.signin;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class EmailVerifyPresenterModule {
    private final ActionBarController mController;
    private final EmailVerifyScreen mScreen;

    public EmailVerifyPresenterModule(EmailVerifyScreen screen, ActionBarController controller) {
        this.mScreen = screen;
        this.mController = controller;
    }

    @ControllerScope
    public EmailVerifyScreen providesScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesController() {
        return this.mController;
    }
}
