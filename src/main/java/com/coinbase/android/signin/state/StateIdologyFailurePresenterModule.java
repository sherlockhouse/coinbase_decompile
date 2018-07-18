package com.coinbase.android.signin.state;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class StateIdologyFailurePresenterModule {
    private final StateIdologyFailureScreen mScreen;

    public StateIdologyFailurePresenterModule(StateIdologyFailureScreen screen) {
        this.mScreen = screen;
    }

    @ControllerScope
    public StateIdologyFailureScreen providesIdologyFailureScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mScreen.getController();
    }
}
