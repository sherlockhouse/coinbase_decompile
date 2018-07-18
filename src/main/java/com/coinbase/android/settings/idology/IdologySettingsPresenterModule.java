package com.coinbase.android.settings.idology;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.idology.IdologyFailureRouter;
import com.coinbase.android.ui.ActionBarController;

public class IdologySettingsPresenterModule {
    private final IdologySettingsScreen mScreen;

    public IdologySettingsPresenterModule(IdologySettingsScreen screen) {
        this.mScreen = screen;
    }

    @ControllerScope
    public IdologySettingsScreen providesIdologyLinkedAccountsScreen() {
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
