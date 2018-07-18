package com.coinbase.android.settings.gdpr;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class EmailSettingsPresenterModule {
    private final EmailSettingsController mController;

    public EmailSettingsPresenterModule(EmailSettingsController controller) {
        this.mController = controller;
    }

    @ControllerScope
    public EmailSettingsScreen providesScreen() {
        return this.mController;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mController;
    }
}
