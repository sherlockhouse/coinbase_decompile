package com.coinbase.android.settings.idology;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class IdologySuccessPresenterModule {
    private final IdologySuccessScreen mScreen;

    public IdologySuccessPresenterModule(IdologySuccessScreen screen) {
        this.mScreen = screen;
    }

    @ControllerScope
    public IdologySuccessScreen providesIdologySuccessScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mScreen.getController();
    }
}
