package com.coinbase.android.signin.state;

import android.os.Bundle;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.task.GetUserTask.AdminFlags;
import com.coinbase.android.ui.ActionBarController;
import javax.inject.Inject;

@ControllerScope
public class UpfrontKycIdentityFailedRouter {
    private final ActionBarController mController;

    @Inject
    public UpfrontKycIdentityFailedRouter(ActionBarController controller) {
        this.mController = controller;
    }

    void routeToDocumentSelector() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(AdminFlags.REQUIRE_JUMIO_FACE_MATCH.toString(), this.mController.getArgs().getBoolean(AdminFlags.REQUIRE_JUMIO_FACE_MATCH.toString(), false));
        this.mController.pushModalController(new UpfrontKycIdentityAcceptableDocumentsController(this.mController.appendModalArgs(bundle)));
    }
}
