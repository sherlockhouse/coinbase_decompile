package com.coinbase.android.signin.state;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.idology.IdologyFailureRouter;
import com.coinbase.android.settings.idology.IdologySettingsQuestionsScreen;
import com.coinbase.android.settings.idology.IdologySuccessRouter;
import com.coinbase.android.ui.ActionBarController;

public class StateIdologySettingsQuestionsPresenterModule {
    private final IdologySettingsQuestionsScreen mScreen;

    public StateIdologySettingsQuestionsPresenterModule(IdologySettingsQuestionsScreen screen) {
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
    public IdologySuccessRouter providesIdologySuccessRouter(IdologySuccessAuthRouter idologySuccessAuthRouter) {
        return idologySuccessAuthRouter;
    }

    @ControllerScope
    public IdologyFailureRouter providesIdologyFailureRouter(UpfrontKycIdologyFailureRouter router) {
        return router;
    }
}
