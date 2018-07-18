package com.coinbase.android.signin.state;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class UpfrontKycIdentityDocumentScanPresenterModule {
    private final ActionBarController mController;
    private final UpfrontKycIdentityDocumentScanScreen mScreen;

    public UpfrontKycIdentityDocumentScanPresenterModule(ActionBarController controller, UpfrontKycIdentityDocumentScanScreen screen) {
        this.mController = controller;
        this.mScreen = screen;
    }

    @ControllerScope
    public ActionBarController providesController() {
        return this.mController;
    }

    @ControllerScope
    public UpfrontKycIdentityDocumentScanScreen providesJumioDocumentScanScreen() {
        return this.mScreen;
    }
}
