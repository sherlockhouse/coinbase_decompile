package com.coinbase.android.gdpr;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class GdprPrivacyPolicyPresenterModule {
    private final ActionBarController mController;
    private final GdprPrivacyPolicyScreen mScreen;

    public GdprPrivacyPolicyPresenterModule(GdprPrivacyPolicyController controller) {
        this.mScreen = controller;
        this.mController = controller;
    }

    @ControllerScope
    public GdprPrivacyPolicyScreen providesScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mController;
    }
}
