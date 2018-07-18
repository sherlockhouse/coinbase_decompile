package com.coinbase.android.dashboard;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.gdpr.GdprModalRouter;
import com.coinbase.android.modalAlerts.ModalRouter;
import com.coinbase.android.signin.LaunchMessageModalRouter;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.wbl.WithdrawalBasedLimitsExistingUserModalRouter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DashboardMainPresenterModule {
    private final ActionBarController mController;
    private final DashboardMainScreen mScreen;

    public DashboardMainPresenterModule(DashboardMainScreen screen, ActionBarController controller) {
        this.mScreen = screen;
        this.mController = controller;
    }

    @ControllerScope
    public DashboardMainScreen provideDashboardPresenterModule() {
        return this.mScreen;
    }

    @ControllerScope
    ActionBarController providesActionBarController() {
        return this.mController;
    }

    @ControllerScope
    List<ModalRouter> providesModalRouters(LaunchMessageModalRouter launchMessageModalRouter, GdprModalRouter gdprModalRouter, WithdrawalBasedLimitsExistingUserModalRouter withdrawalBasedLimitsExistingUserModalRouter) {
        return new LinkedList(Arrays.asList(new ModalRouter[]{launchMessageModalRouter, gdprModalRouter, withdrawalBasedLimitsExistingUserModalRouter}));
    }
}
