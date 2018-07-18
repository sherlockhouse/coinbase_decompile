package com.coinbase.android.gdpr;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class GdprIntroPresenterModule {
    private final ActionBarController mController;
    private final GdprIntroScreen mScreen;

    public GdprIntroPresenterModule(GdprIntroController gdprIntroController) {
        this.mController = gdprIntroController;
        this.mScreen = gdprIntroController;
    }

    @ControllerScope
    public GdprIntroScreen providesScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mController;
    }
}
