package com.coinbase.android.transfers;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.wbl.LimitExceededTrackingContext;
import com.coinbase.android.wbl.WithdrawalBasedLimitsErrorControllerRouter;
import com.coinbase.android.wbl.WithdrawalBasedLimitsErrorRouter;

public class TransferSendControllerModule {
    private final ActionBarController mController;

    public TransferSendControllerModule(ActionBarController controller) {
        this.mController = controller;
    }

    @ControllerScope
    public ActionBarController providesController() {
        return this.mController;
    }

    @ControllerScope
    public WithdrawalBasedLimitsErrorRouter providesWithdrawalBasedLimitsErrorControllerRouter(WithdrawalBasedLimitsErrorControllerRouter router) {
        return router;
    }

    @ControllerScope
    LimitExceededTrackingContext providesSendExceededTrackingContext() {
        return LimitExceededTrackingContext.SEND;
    }
}
