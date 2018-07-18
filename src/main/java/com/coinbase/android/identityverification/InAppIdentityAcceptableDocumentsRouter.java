package com.coinbase.android.identityverification;

import android.os.Bundle;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.task.GetUserTask.AdminFlags;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.utils.BundleWrapper;
import javax.inject.Inject;

@ControllerScope
public class InAppIdentityAcceptableDocumentsRouter {
    private final ActionBarController mController;

    @Inject
    public InAppIdentityAcceptableDocumentsRouter(ActionBarController controller) {
        this.mController = controller;
    }

    public void routeToNext(String doc, int position) {
        Bundle extras = new Bundle();
        extras.putString(IdentityVerificationConstants.JUMIO_DOCUMENT_KEY, doc);
        extras.putInt(IdentityVerificationConstants.JUMIO_DOCUMENT_INDEX_KEY, position);
        this.mController.pushModalController(new InAppIdentityDocumentScanController(this.mController.appendModalArgs(new BundleWrapper().passAlongBoolean(this.mController.getArgs(), extras, AdminFlags.REQUIRE_JUMIO_FACE_MATCH.toString()))));
    }
}
