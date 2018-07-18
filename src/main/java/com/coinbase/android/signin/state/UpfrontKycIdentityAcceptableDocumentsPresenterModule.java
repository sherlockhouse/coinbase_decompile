package com.coinbase.android.signin.state;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class UpfrontKycIdentityAcceptableDocumentsPresenterModule {
    private final ActionBarController mController;
    private final UpfrontKycIdentityAcceptableDocumentsScreen mScreen;

    public UpfrontKycIdentityAcceptableDocumentsPresenterModule(UpfrontKycIdentityAcceptableDocumentsScreen screen, ActionBarController controller) {
        this.mScreen = screen;
        this.mController = controller;
    }

    @ControllerScope
    public UpfrontKycIdentityAcceptableDocumentsScreen providesScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesController() {
        return this.mController;
    }
}
