package com.coinbase.android.signin;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class SignUpControllerSubcomponentModule {
    private final ActionBarController mController;

    public SignUpControllerSubcomponentModule(ActionBarController controller) {
        this.mController = controller;
    }

    @ControllerScope
    public ActionBarController providesController() {
        return this.mController;
    }
}
