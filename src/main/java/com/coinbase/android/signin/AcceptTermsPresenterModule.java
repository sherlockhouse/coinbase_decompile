package com.coinbase.android.signin;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class AcceptTermsPresenterModule {
    private final ActionBarController mController;
    private final AcceptTermsScreen mScreen;

    public AcceptTermsPresenterModule(AcceptTermsScreen screen, ActionBarController controller) {
        this.mScreen = screen;
        this.mController = controller;
    }

    @ControllerScope
    public AcceptTermsScreen providesScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesController() {
        return this.mController;
    }
}
