package com.coinbase.android.signin;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class LoginControllerSubcomponentModule {
    private final ActionBarController mController;

    public LoginControllerSubcomponentModule(ActionBarController controller) {
        this.mController = controller;
    }

    @ControllerScope
    public ActionBarController providesController() {
        return this.mController;
    }
}
