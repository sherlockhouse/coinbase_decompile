package com.coinbase.android.signin.state;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.idology.IdologyFailureRouter;
import com.coinbase.android.ui.ActionBarController;

public class StateIdologySourceOfFundsFormPresenterModule {
    private final ActionBarController mActionBarController;
    private final StateIdologySourceOfFundsScreen mScreen;

    public StateIdologySourceOfFundsFormPresenterModule(StateIdologySourceOfFundsScreen screen, ActionBarController controller) {
        this.mScreen = screen;
        this.mActionBarController = controller;
    }

    @ControllerScope
    public StateIdologySourceOfFundsScreen providesStateIdologySourceOfFundsFormScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mActionBarController;
    }

    @ControllerScope
    public IdologyFailureRouter providesIdologyFailureRouter(UpfrontKycIdologyFailureRouter router) {
        return router;
    }
}
