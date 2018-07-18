package com.coinbase.android.paymentmethods;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.coinbase.android.Constants;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.paymentmethods.card.CardFormController;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.v2.models.paymentMethods.Data;
import com.google.gson.Gson;
import javax.inject.Inject;

@ControllerScope
public class PaymentMethodsRouter {
    ActionBarController mController;
    PaymentMethodsScreen mScreen;

    @Inject
    PaymentMethodsRouter(ActionBarController controller, PaymentMethodsScreen screen) {
        this.mController = controller;
        this.mScreen = screen;
    }

    void routeVerifyPaymentMethod(Data method) {
        boolean z = true;
        Intent intent = new Intent(this.mController.getActivity(), PlaidAccountLoginActivity.class);
        intent.putExtra("payment_method", new Gson().toJson((Object) method));
        intent.putExtra(PlaidAccountLoginActivity.MANUAL, true);
        String str = Constants.PARENT_SUCCESS_ROUTER;
        if (TextUtils.isEmpty(this.mController.getLocalRoot())) {
            z = false;
        }
        intent.putExtra(str, z);
        this.mController.startActivityForResult(intent, PaymentMethodsPresenter.INTENT_VERIFY);
    }

    void routeCardForm(Bundle args) {
        this.mController.pushModalController(new CardFormController(this.mController.appendModalArgs(args)));
    }

    void routeAddPlaidAccount() {
        this.mController.pushModalController(new AddPlaidAccountController(this.mController.appendModalArgs(new Bundle())));
    }

    void routeConnectBankAccount(boolean shouldPassThroughToPlaid) {
        Bundle args = new Bundle();
        args.putBoolean(AddBankPickerPresenter.PASS_THROUGH_TO_PLAID, shouldPassThroughToPlaid);
        this.mController.pushModalController(new AddBankPickerController(this.mController.appendModalArgsWithRoot(args)), true);
    }
}
