package com.coinbase.android.settings.gdpr;

import android.os.Bundle;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.settings.SettingsPreferenceItem;
import com.coinbase.android.ui.ActionBarController;
import javax.inject.Inject;

@ControllerScope
public class GdprSettingsRouter {
    private final ActionBarController mController;

    @Inject
    GdprSettingsRouter(ActionBarController controller) {
        this.mController = controller;
    }

    void routeToRequestController(SettingsPreferenceItem item) {
        if (item != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("GDPR_PRIVACY_REQUEST_CLASS", item.getClass());
            this.mController.pushModalController(new GdprPrivacyRequestController(this.mController.appendModalArgs(bundle)));
        }
    }

    void routeToSuccessPage() {
        this.mController.pushModalController(new GdprRequestSentController(this.mController.appendModalArgs(new Bundle())));
    }
}
