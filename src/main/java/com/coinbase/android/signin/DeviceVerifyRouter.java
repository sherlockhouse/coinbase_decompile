package com.coinbase.android.signin;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;
import javax.inject.Inject;

@ControllerScope
public class DeviceVerifyRouter {
    private final ActionBarController mController;

    @Inject
    public DeviceVerifyRouter(ActionBarController controller) {
        this.mController = controller;
    }

    void routeToFailure() {
        this.mController.getRouter().popToRoot();
    }
}
