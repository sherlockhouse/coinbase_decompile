package com.coinbase.android.signin.state;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class StateSuspendedPresenterModule {
    private final ActionBarController mActionBarController;
    private final StateSuspendedScreen mScreen;

    public StateSuspendedPresenterModule(StateSuspendedScreen screen, ActionBarController actionBarController) {
        this.mScreen = screen;
        this.mActionBarController = actionBarController;
    }

    @ControllerScope
    public StateSuspendedScreen providesStateSuspendedScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mActionBarController;
    }
}
