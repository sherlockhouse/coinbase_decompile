package com.coinbase.android.identityverification;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class InAppIdentityDocumentScanPresenterModule {
    private final ActionBarController mController;
    private final JumioDocumentScanScreen mScreen;

    public InAppIdentityDocumentScanPresenterModule(ActionBarController controller, JumioDocumentScanScreen screen) {
        this.mController = controller;
        this.mScreen = screen;
    }

    @ControllerScope
    public ActionBarController providesController() {
        return this.mController;
    }

    @ControllerScope
    public JumioDocumentScanScreen providesJumioDocumentScanScreen() {
        return this.mScreen;
    }
}
