package com.coinbase.android.signin.state;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class UpfrontKycIdentityFailedPresenterModule {
    private final ActionBarController mController;
    private final UpfrontKycIdentityFailedScreen mScreen;

    UpfrontKycIdentityFailedPresenterModule(ActionBarController controller, UpfrontKycIdentityFailedScreen screen) {
        this.mController = controller;
        this.mScreen = screen;
    }

    @ControllerScope
    public ActionBarController providesController() {
        return this.mController;
    }

    @ControllerScope
    public UpfrontKycIdentityFailedScreen providesScreen() {
        return this.mScreen;
    }
}
