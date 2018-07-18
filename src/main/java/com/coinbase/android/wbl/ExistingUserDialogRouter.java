package com.coinbase.android.wbl;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.settings.tiers.InvestmentTiersSettingsController;
import com.coinbase.android.ui.ActionBarController;
import javax.inject.Inject;

@ControllerScope
public class ExistingUserDialogRouter {
    private static final String SUPPORT_URL = "https://support.coinbase.com/customer/portal/articles/2932046-limits-faq";
    private final ActionBarController mController;

    @Inject
    public ExistingUserDialogRouter(ActionBarController controller) {
        this.mController = controller;
    }

    void routeToAccountLevels() {
        this.mController.pushModalController(new InvestmentTiersSettingsController(this.mController.appendModalArgs(new Bundle())));
    }

    void routeToLearnMore() {
        this.mController.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(SUPPORT_URL)));
    }
}
