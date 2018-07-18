package com.coinbase.android.signin.state;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class UpfrontKycIdentityProcessingPresenterModule {
    private final ActionBarController mController;
    private final UpfrontKycIdentityProcessingScreen mScreen;

    public UpfrontKycIdentityProcessingPresenterModule(UpfrontKycIdentityProcessingScreen screen, ActionBarController controller) {
        this.mScreen = screen;
        this.mController = controller;
    }

    @ControllerScope
    public ActionBarController providesController() {
        return this.mController;
    }

    @ControllerScope
    public UpfrontKycIdentityProcessingScreen providesScreen() {
        return this.mScreen;
    }
}
