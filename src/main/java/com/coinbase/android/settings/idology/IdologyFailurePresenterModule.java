package com.coinbase.android.settings.idology;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class IdologyFailurePresenterModule {
    private final IdologyFailureScreen mScreen;

    public IdologyFailurePresenterModule(IdologyFailureScreen screen) {
        this.mScreen = screen;
    }

    @ControllerScope
    public IdologyFailureScreen providesIdologyFailureScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mScreen.getController();
    }
}
