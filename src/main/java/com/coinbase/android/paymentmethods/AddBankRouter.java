package com.coinbase.android.paymentmethods;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.bluelinelabs.conductor.changehandler.SimpleSwapChangeHandler;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.SuccessRouter;
import javax.inject.Inject;

@ControllerScope
public class AddBankRouter {
    private final AppCompatActivity mActivity;
    private final ActionBarController mController;
    AddBankPickerScreen mScreen;
    private final SuccessRouter mSuccessRouter;

    @Inject
    AddBankRouter(ActionBarController controller, AppCompatActivity activity, AddBankPickerScreen screen, SuccessRouter successRouter) {
        this.mController = controller;
        this.mActivity = activity;
        this.mScreen = screen;
        this.mSuccessRouter = successRouter;
    }

    void routeBankAccountManualEntry() {
        Intent intent = new Intent(this.mActivity, PlaidAccountLoginActivity.class);
        intent.putExtra(PlaidAccountLoginActivity.MANUAL, true);
        this.mScreen.startActivity(intent);
    }

    void routePlaidWebView(boolean isPassThrough) {
        if (isPassThrough) {
            this.mController.pushModalController(new PlaidController(this.mController.appendModalArgsWithRoot(new Bundle())), false, new SimpleSwapChangeHandler(), null);
        } else {
            this.mController.pushModalController(new PlaidController(this.mController.appendModalArgsWithRoot(new Bundle())));
        }
    }

    void routeAddBankError() {
        this.mController.pushModalController(new AddBankErrorController(this.mController.appendModalArgsWithRoot(new Bundle())));
    }

    void routeSettings() {
        if (this.mSuccessRouter.shouldRouteSuccess()) {
            this.mSuccessRouter.routeSuccess();
        }
    }
}
