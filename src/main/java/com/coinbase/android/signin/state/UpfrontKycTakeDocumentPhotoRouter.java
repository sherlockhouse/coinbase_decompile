package com.coinbase.android.signin.state;

import android.os.Bundle;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.identityverification.IdentityVerificationConstants;
import com.coinbase.android.task.GetUserTask.AdminFlags;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.utils.BundleWrapper;
import javax.inject.Inject;

@ControllerScope
public class UpfrontKycTakeDocumentPhotoRouter {
    private final ActionBarController mController;

    @Inject
    public UpfrontKycTakeDocumentPhotoRouter(ActionBarController controller) {
        this.mController = controller;
    }

    void routeToVerifyPhoto(Bundle args) {
        args.putString(IdentityVerificationConstants.JUMIO_DOCUMENT_KEY, this.mController.getArgs().getString(IdentityVerificationConstants.JUMIO_DOCUMENT_KEY));
        args.putInt(IdentityVerificationConstants.JUMIO_DOCUMENT_INDEX_KEY, this.mController.getArgs().getInt(IdentityVerificationConstants.JUMIO_DOCUMENT_INDEX_KEY));
        this.mController.replaceModalController(new UpfrontKycIdentityDocumentScanController(this.mController.appendModalArgs(new BundleWrapper().passAlongBoolean(this.mController.getArgs(), args, AdminFlags.REQUIRE_JUMIO_FACE_MATCH.toString()))));
    }
}
