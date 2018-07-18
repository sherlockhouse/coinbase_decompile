package com.coinbase.android.settings;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class AccountSettingsPresenterModule {
    private final ActionBarController mController;
    private final AccountSettingsScreen mScreen;

    public AccountSettingsPresenterModule(AccountSettingsScreen screen, ActionBarController controller) {
        this.mScreen = screen;
        this.mController = controller;
    }

    @ControllerScope
    public AccountSettingsScreen providesAccountSettingsScreen() {
        return this.mScreen;
    }

    @ControllerScope
    ActionBarController providesActionBarController() {
        return this.mController;
    }
}
