package com.coinbase.android.settings.idology;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.idology.IdologyFailureRouter;
import com.coinbase.android.ui.ActionBarController;

public class IdologySettingsQuestionsPresenterModule {
    private final IdologySettingsQuestionsScreen mScreen;

    public IdologySettingsQuestionsPresenterModule(IdologySettingsQuestionsScreen screen) {
        this.mScreen = screen;
    }

    @ControllerScope
    public IdologySettingsQuestionsScreen providesIdologyQuestionsScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mScreen.getController();
    }

    @ControllerScope
    public IdologySuccessRouter providesIdologySuccessRouter(IdologySuccessSettingsRouter idologySuccessSettingsRouter) {
        return idologySuccessSettingsRouter;
    }

    @ControllerScope
    public IdologyFailureRouter providesIdologyFailureRouter(InAppIdologyFailureRouter router) {
        return router;
    }
}
