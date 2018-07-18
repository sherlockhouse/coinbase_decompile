package com.coinbase.android.signin.state;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class UpfrontKycTakeDocumentPhotoPresenterModule {
    private final ActionBarController mController;
    private final UpfrontKycTakeDocumentPhotoScreen mScreen;

    public UpfrontKycTakeDocumentPhotoPresenterModule(UpfrontKycTakeDocumentPhotoScreen screen, ActionBarController controller) {
        this.mScreen = screen;
        this.mController = controller;
    }

    @ControllerScope
    public UpfrontKycTakeDocumentPhotoScreen providesTakeDocumentPhotoScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesController() {
        return this.mController;
    }
}
