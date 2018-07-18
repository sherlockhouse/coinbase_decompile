package com.coinbase.android.signin.state;

import android.os.Bundle;
import com.coinbase.android.identityverification.IdentityVerificationConstants;
import com.coinbase.android.signin.SignInRouter;
import com.coinbase.android.task.GetUserTask.AdminFlags;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.utils.BundleWrapper;
import javax.inject.Inject;

public class UpfrontKycIdentityAcceptableDocumentsRouter {
    private final ActionBarController mController;
    private final SignInRouter mSignInRouter;

    @Inject
    public UpfrontKycIdentityAcceptableDocumentsRouter(ActionBarController controller, SignInRouter signInRouter) {
        this.mController = controller;
        this.mSignInRouter = signInRouter;
    }

    void routeToNext(String doc, int position) {
        Bundle extras = new Bundle();
        extras.putString(IdentityVerificationConstants.JUMIO_DOCUMENT_KEY, doc);
        extras.putInt(IdentityVerificationConstants.JUMIO_DOCUMENT_INDEX_KEY, position);
        this.mController.pushModalController(new UpfrontKycTakeDocumentPhotoController(this.mController.appendModalArgs(new BundleWrapper().passAlongBoolean(this.mController.getArgs(), extras, AdminFlags.REQUIRE_JUMIO_FACE_MATCH.toString()))));
    }

    void routeToBack() {
        this.mSignInRouter.cancel(UpfrontKycIdentityAcceptableDocumentsRouter$$Lambda$1.lambdaFactory$());
    }

    static /* synthetic */ void lambda$routeToBack$0() {
    }
}
