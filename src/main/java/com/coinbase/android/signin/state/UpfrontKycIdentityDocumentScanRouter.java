package com.coinbase.android.signin.state;

import android.os.Bundle;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;
import javax.inject.Inject;

@ControllerScope
public class UpfrontKycIdentityDocumentScanRouter {
    private final ActionBarController mController;

    @Inject
    public UpfrontKycIdentityDocumentScanRouter(ActionBarController controller) {
        this.mController = controller;
    }

    public void routeToTakePhoto(Bundle extras) {
        this.mController.replaceModalController(new UpfrontKycTakeDocumentPhotoController(this.mController.appendModalArgs(extras)));
    }

    public void routeToComplete() {
        this.mController.replaceModalController(new UpfrontKycIdentityProcessingController(this.mController.appendModalArgs(new Bundle())));
    }
}
