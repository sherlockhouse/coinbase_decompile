package com.coinbase.android.signin.state;

import android.os.Bundle;
import com.bluelinelabs.conductor.changehandler.SimpleSwapChangeHandler;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.task.GetUserTask.AdminFlags;
import com.coinbase.android.ui.ActionBarController;
import javax.inject.Inject;

@ControllerScope
public class UpfrontKycIdentityProcessingRouter {
    private final ActionBarController mController;

    @Inject
    public UpfrontKycIdentityProcessingRouter(ActionBarController controller) {
        this.mController = controller;
    }

    void routeFailure(String reason, String hint) {
        this.mController.pushModalController(new UpfrontKycIdentityFailedController(this.mController.appendModalArgs(createBundle(reason, hint))));
    }

    void routeToDocumentSelector() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(AdminFlags.REQUIRE_JUMIO_FACE_MATCH.toString(), this.mController.getArgs().getBoolean(AdminFlags.REQUIRE_JUMIO_FACE_MATCH.toString(), false));
        this.mController.replaceModalController(new UpfrontKycIdentityAcceptableDocumentsController(this.mController.appendModalArgs(bundle)), new SimpleSwapChangeHandler(), new SimpleSwapChangeHandler());
    }

    private Bundle createBundle(String reason, String hint) {
        Bundle bundle = new Bundle();
        bundle.putString(UpfrontKycIdentityFailedPresenter.HEADER, reason);
        bundle.putString(UpfrontKycIdentityFailedPresenter.HINT, hint);
        return bundle;
    }
}
