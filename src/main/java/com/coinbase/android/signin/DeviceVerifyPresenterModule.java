package com.coinbase.android.signin;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class DeviceVerifyPresenterModule {
    private final ActionBarController mController;
    private final DeviceVerifyScreen mScreen;

    public DeviceVerifyPresenterModule(DeviceVerifyScreen screen, ActionBarController controller) {
        this.mScreen = screen;
        this.mController = controller;
    }

    @ControllerScope
    public DeviceVerifyScreen providesScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesController() {
        return this.mController;
    }
}
