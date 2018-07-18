package com.coinbase.android.deposits;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.wbl.LimitExceededTrackingContext;
import com.coinbase.android.wbl.WithdrawalBasedLimitsErrorControllerRouter;
import com.coinbase.android.wbl.WithdrawalBasedLimitsErrorRouter;

public class FiatTransactionsControllerModule {
    private final ActionBarController mController;

    public FiatTransactionsControllerModule(ActionBarController controller) {
        this.mController = controller;
    }

    @ControllerScope
    public ActionBarController providesController() {
        return this.mController;
    }

    @ControllerScope
    public WithdrawalBasedLimitsErrorRouter providesWithdrawalBasedLimitsErrorRouter(WithdrawalBasedLimitsErrorControllerRouter router) {
        return router;
    }

    @ControllerScope
    LimitExceededTrackingContext providesSendExceededTrackingContext() {
        return LimitExceededTrackingContext.WITHDRAW;
    }
}
