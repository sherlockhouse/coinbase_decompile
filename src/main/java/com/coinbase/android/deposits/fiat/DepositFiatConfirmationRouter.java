package com.coinbase.android.deposits.fiat;

import android.os.Bundle;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.paymentmethods.linkedaccounts.LinkedAccountsPickerController;
import com.coinbase.android.paymentmethods.linkedaccounts.LinkedAccountsPickerPresenter;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.ApiErrorDialogController;
import com.coinbase.v2.models.paymentMethods.Data;
import com.google.gson.Gson;
import java.util.List;
import javax.inject.Inject;

@ControllerScope
public class DepositFiatConfirmationRouter {
    private ActionBarController mController;

    @Inject
    DepositFiatConfirmationRouter(ActionBarController actionBarController) {
        this.mController = actionBarController;
    }

    void routeToError(String errorTitle, String errorMessage) {
        Bundle bundle = new Bundle();
        bundle.putString(ApiErrorDialogController.ERROR_TITLE, errorTitle);
        bundle.putString("error_message", errorMessage);
        this.mController.pushDialogModalController(new ApiErrorDialogController(this.mController.appendModalArgs(bundle)));
    }

    void routeToLinkedAccountsPicker(List<Data> paymentMethods) {
        String paymentMethodString = new Gson().toJson((Object) paymentMethods);
        if (paymentMethodString != null) {
            Bundle bundle = new Bundle();
            bundle.putString(LinkedAccountsPickerPresenter.PAYMENT_METHOD_LIST, paymentMethodString);
            this.mController.pushModalController(new LinkedAccountsPickerController(this.mController.appendModalArgs(bundle)));
        }
    }

    void routeToDepositSuccess(com.coinbase.v2.models.transfers.Data transferData, com.coinbase.v2.models.account.Data accountData) {
        Gson gson = new Gson();
        Bundle args = new Bundle();
        args.putString("DEPOSIT_WITHDRAW_SUCCESS_TRANSFER_DATA", gson.toJson((Object) transferData));
        args.putString("DEPOSIT_WITHDRAW_SUCCESS_ACCOUNT", gson.toJson((Object) accountData));
        this.mController.pushModalController(new WithdrawDepositSuccessController(this.mController.appendModalArgs(args)), true);
    }
}
