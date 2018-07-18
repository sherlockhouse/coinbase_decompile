package com.coinbase.android.settings.gdpr;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class GdprRequestSentPresenterModule {
    private final ActionBarController mActionBarController;
    private final GdprRequestSentScreen mScreen;

    public GdprRequestSentPresenterModule(ActionBarController actionBarController, GdprRequestSentScreen screen) {
        this.mActionBarController = actionBarController;
        this.mScreen = screen;
    }

    @ControllerScope
    public GdprRequestSentScreen providesScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mActionBarController;
    }
}
