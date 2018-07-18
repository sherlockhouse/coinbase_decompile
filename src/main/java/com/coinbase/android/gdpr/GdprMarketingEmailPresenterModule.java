package com.coinbase.android.gdpr;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class GdprMarketingEmailPresenterModule {
    private final ActionBarController mController;
    private final GdprMarketingEmailScreen mScreen;

    public GdprMarketingEmailPresenterModule(GdprMarketingEmailController gdprMarketingEmailController) {
        this.mController = gdprMarketingEmailController;
        this.mScreen = gdprMarketingEmailController;
    }

    @ControllerScope
    public GdprMarketingEmailScreen providesScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mController;
    }
}
