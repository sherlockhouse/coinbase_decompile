package com.coinbase.android.paymentmethods.card;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.coinbase.android.Constants;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivity;
import com.coinbase.android.R;
import com.coinbase.android.billing.AddBillingAddressActivity;
import com.coinbase.android.dialog.ConfirmationDialogController;
import com.coinbase.android.paymentmethods.PlaidAccountLoginActivity;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.SuccessRouter;
import com.coinbase.v2.models.paymentMethods.Data;
import com.google.gson.Gson;
import javax.inject.Inject;

@ControllerScope
public class CardFormRouter {
    AppCompatActivity mActivity;
    ActionBarController mController;
    CardFormScreen mScreen;
    SuccessRouter mSuccessRouter;

    public static class CardCDVDialogController extends ConfirmationDialogController {
        static String MESSAGE_KEY = "message_key";

        public CardCDVDialogController(Bundle args) {
            super(args);
        }

        public String getMessage() {
            int message = R.string.card_cdv_confrimation;
            if (getArgs().containsKey(MESSAGE_KEY)) {
                message = getArgs().getInt(MESSAGE_KEY);
            }
            return getApplicationContext().getString(message);
        }

        protected int getPositiveButtonText() {
            return R.string.ok;
        }

        public void onUserConfirm() {
            dismiss();
            SuccessRouter successRouter = new SuccessRouter(this);
            if (successRouter.shouldRouteSuccess()) {
                successRouter.routeSuccess();
            }
        }

        public void onUserCancel() {
            dismiss();
        }
    }

    @Inject
    CardFormRouter(CardFormScreen screen, ActionBarController controller, AppCompatActivity activity, SuccessRouter successRouter) {
        this.mScreen = screen;
        this.mController = controller;
        this.mActivity = activity;
        this.mSuccessRouter = successRouter;
    }

    void routePlaidAccountLoginActivity(Data paymentMethod) {
        Intent intent = new Intent(this.mActivity, PlaidAccountLoginActivity.class);
        intent.putExtra("payment_method", new Gson().toJson((Object) paymentMethod));
        intent.putExtra(PlaidAccountLoginActivity.MANUAL, true);
        intent.putExtra(Constants.PARENT_SUCCESS_ROUTER, this.mSuccessRouter.shouldRouteSuccess());
        this.mController.startActivityForResult(intent, 104);
    }

    void routeCardCDVDialog() {
        this.mController.pushDialogModalController(new CardCDVDialogController(this.mController.appendModalArgsWithRoot(new Bundle())));
    }

    void routeWorldpayCDVPermissionDialog() {
        Bundle bundle = this.mController.appendModalArgsWithRoot(new Bundle());
        bundle.putInt(CardCDVDialogController.MESSAGE_KEY, R.string.world_pay_cdv_message);
        this.mController.pushDialogModalController(new CardCDVDialogController(bundle));
    }

    void routeBuyActionToMainActivity() {
        Intent intent = new Intent(this.mActivity, MainActivity.class);
        intent.setFlags(67108864);
        intent.setAction(MainActivity.ACTION_BUY);
        this.mActivity.startActivity(intent);
    }

    void routeNewAddressActivity() {
        this.mController.startActivityForResult(new Intent(this.mActivity, AddBillingAddressActivity.class), 103);
    }

    void routePaymentVerificationController(Bundle args) {
        this.mController.replaceModalController(new PaymentMethodVerificationController(this.mController.appendModalArgs(args)));
    }
}
