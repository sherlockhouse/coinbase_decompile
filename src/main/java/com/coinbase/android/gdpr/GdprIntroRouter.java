package com.coinbase.android.gdpr;

import android.os.Bundle;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;
import javax.inject.Inject;

@ControllerScope
public class GdprIntroRouter {
    private final ActionBarController mController;

    @Inject
    public GdprIntroRouter(ActionBarController controller) {
        this.mController = controller;
    }

    void routeToGdprPrivacyPolicy(boolean shouldCenterModalWithOverlay) {
        Bundle args = new Bundle();
        args.putBoolean(OnboardingScreen.CENTER_MODAL_WITH_OVERLAY, shouldCenterModalWithOverlay);
        this.mController.pushModalController(new GdprPrivacyPolicyController(this.mController.appendModalArgsWithRoot(args)));
    }
}
