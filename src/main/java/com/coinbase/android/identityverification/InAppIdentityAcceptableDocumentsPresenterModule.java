package com.coinbase.android.identityverification;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class InAppIdentityAcceptableDocumentsPresenterModule {
    private final ActionBarController mController;
    private final IdentityAcceptableDocumentsScreen mScreen;

    public InAppIdentityAcceptableDocumentsPresenterModule(IdentityAcceptableDocumentsScreen screen, ActionBarController controller) {
        this.mScreen = screen;
        this.mController = controller;
    }

    @ControllerScope
    public IdentityAcceptableDocumentsScreen providesScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesController() {
        return this.mController;
    }
}
