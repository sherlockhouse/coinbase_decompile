package com.coinbase.android.wbl;

import android.os.Bundle;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;
import javax.inject.Inject;

@ControllerScope
public class WithdrawalBasedLimitsErrorControllerRouter implements WithdrawalBasedLimitsErrorRouter {
    private final ActionBarController mController;
    private final LimitExceededTrackingContext mTrackingContext;

    @Inject
    public WithdrawalBasedLimitsErrorControllerRouter(ActionBarController controller, LimitExceededTrackingContext trackingContext) {
        this.mController = controller;
        this.mTrackingContext = trackingContext;
    }

    public void routeWithdrawalBasedLimitsError(String message, String title, String learnMoreText, String dismissText) {
        Bundle args = new Bundle();
        args.putString("message", message);
        args.putString("title", title);
        args.putString("learn_more", learnMoreText);
        args.putString("dismiss_text", dismissText);
        args.putInt("tracking_context", this.mTrackingContext.getValue());
        this.mController.pushDialogModalController(new WithdrawalBasedLimitsDialogController(this.mController.appendModalArgs(args)));
    }
}
