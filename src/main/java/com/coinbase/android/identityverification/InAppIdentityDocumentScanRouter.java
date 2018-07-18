package com.coinbase.android.identityverification;

import android.os.Bundle;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;
import javax.inject.Inject;

@ControllerScope
public class InAppIdentityDocumentScanRouter {
    private final ActionBarController mController;

    @Inject
    public InAppIdentityDocumentScanRouter(ActionBarController controller) {
        this.mController = controller;
    }

    public void routeToScanDocument(Bundle extras) {
        extras.putBoolean(IdentityVerificationConstants.SHOULD_NAVIGATE_TO_DEBIT, this.mController.getArgs().getBoolean(IdentityVerificationConstants.SHOULD_NAVIGATE_TO_DEBIT, false));
        this.mController.pushModalController(new InAppIdentityDocumentScanController(this.mController.appendModalArgs(extras)));
    }

    public void routeToComplete() {
        this.mController.pushModalController(new JumioCompleteController(this.mController.appendModalArgs(new Bundle())));
    }
}
