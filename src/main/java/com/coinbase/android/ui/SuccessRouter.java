package com.coinbase.android.ui;

import android.text.TextUtils;
import com.bluelinelabs.conductor.Controller;
import com.coinbase.android.ControllerScope;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerScope
public class SuccessRouter {
    public static final int SUCCESS_REQUEST_CODE = 66;
    public static final int SUCCESS_RESULT_CODE = 66;
    private final ActionBarController mController;
    private final Logger mLogger = LoggerFactory.getLogger(SuccessRouter.class);

    @Inject
    public SuccessRouter(ActionBarController controller) {
        this.mController = controller;
    }

    public boolean shouldRouteSuccess() {
        return !TextUtils.isEmpty(this.mController.getLocalRoot());
    }

    public void routeSuccess() {
        Controller rootController;
        if (this.mController.getParentController() == null || this.mController.getParentController().getRouter() == null) {
            rootController = null;
        } else {
            rootController = this.mController.getParentController().getRouter().getControllerWithTag(this.mController.getLocalRoot());
        }
        if (this.mController.getRouter().getControllerWithTag(this.mController.getLocalRoot()) != null && rootController == null) {
            this.mController.getRouter().getControllerWithTag(this.mController.getLocalRoot()).onActivityResult(66, 66, null);
            this.mController.getRouter().popToTag(this.mController.getLocalRoot());
        } else if (rootController == null || this.mController.getParentController() == null) {
            this.mLogger.error("Couldn't pop to tag in success router [" + this.mController.getLocalRoot() + "]");
        } else {
            this.mController.getParentController().removeChildRouter(this.mController.getRouter());
            this.mController.getParentController().getRouter().getControllerWithTag(this.mController.getLocalRoot()).onActivityResult(66, 66, null);
            this.mController.getParentController().getRouter().popToTag(this.mController.getLocalRoot());
        }
    }

    public boolean hasRootTag(Class<?> clazz) {
        if (shouldRouteSuccess()) {
            return TextUtils.equals(clazz.getName(), this.mController.getLocalRoot());
        }
        return false;
    }
}
