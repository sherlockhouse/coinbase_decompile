package com.coinbase.android.gdpr;

import android.os.Bundle;
import com.coinbase.android.ControllerScope;
import javax.inject.Inject;

@ControllerScope
public class GdprIntroPresenter {
    private final GdprIntroRouter mRouter;
    private final GdprIntroScreen mScreen;

    @Inject
    public GdprIntroPresenter(GdprIntroScreen gdprIntroScreen, GdprIntroRouter gdprIntroRouter) {
        this.mScreen = gdprIntroScreen;
        this.mRouter = gdprIntroRouter;
    }

    public void onNextClicked(Bundle args) {
        this.mRouter.routeToGdprPrivacyPolicy(args.getBoolean(OnboardingScreen.CENTER_MODAL_WITH_OVERLAY, false));
    }

    public void onCreateView(Bundle args) {
        if (args.getBoolean(OnboardingScreen.CENTER_MODAL_WITH_OVERLAY, false)) {
            this.mScreen.setBackgroundTranslucent(true);
            this.mScreen.setModalCentered(true);
            return;
        }
        this.mScreen.setBackgroundTranslucent(false);
        this.mScreen.setModalCentered(false);
    }
}
