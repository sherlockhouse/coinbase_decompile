package com.coinbase.android.wbl;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class ExistingUserDialogPresenterModule {
    private final ActionBarController mController;
    private final ExistingUserDialogScreen mScreen;

    public ExistingUserDialogPresenterModule(ExistingUserDialogScreen screen, ActionBarController controller) {
        this.mScreen = screen;
        this.mController = controller;
    }

    @ControllerScope
    public ExistingUserDialogScreen providesScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesController() {
        return this.mController;
    }
}
