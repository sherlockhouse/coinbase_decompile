package com.coinbase.android.signin.state;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.idology.IdologyFailureRouter;
import com.coinbase.android.settings.idology.IdologySuccessRouter;
import com.coinbase.android.ui.ActionBarController;

public class StateIdologyRetryFormPresenterModule {
    private final ActionBarController mActionBarController;
    private final StateIdologyRetryFormScreen mScreen;

    public StateIdologyRetryFormPresenterModule(StateIdologyRetryFormScreen screen, ActionBarController controller) {
        this.mScreen = screen;
        this.mActionBarController = controller;
    }

    @ControllerScope
    public StateIdologyRetryFormScreen providesStateIdologyRetryFormScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mActionBarController;
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
