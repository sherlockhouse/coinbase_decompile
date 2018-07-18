package com.coinbase.android.deposits.fiat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.paymentmethods.linkedaccounts.LinkedAccountsPickerController;
import com.coinbase.android.paymentmethods.linkedaccounts.LinkedAccountsPickerPresenter;
import com.coinbase.android.settings.GoToSettingsDialogController;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.ApiErrorDialogController;
import com.coinbase.android.ui.ControllerLifeCycle;
import com.coinbase.android.ui.ModalControllerLifeCycle;
import com.coinbase.v2.models.paymentMethods.Data;
import com.google.gson.Gson;
import java.util.List;
import javax.inject.Inject;

@ControllerScope
public class DepositFiatRouter {
    public static final String ACCOUNT = "DEPOSIT_CONFIRMATION_ACCOUNT";
    private static final String BLOG_URL = "https://blog.coinbase.com";
    public static final String PAYMENT_METHOD = "DEPOSIT_CONFIRMATION_PAYMENT_METHOD";
    public static final String SELECTED_ACCOUNT = "selected_account";
    public static final String TRANSFER = "DEPOSIT_CONFIRMATION_TRANSFER";
    public static final String VALID_PAYMENT_METHODS = "DEPOSIT_CONFIRMATION_VALID_PAYMENT_METHOD_LIST";
    private final ActionBarController mController;

    @Inject
    public DepositFiatRouter(ActionBarController controller) {
        this.mController = controller;
    }

    void routeToLinkedAccountsPicker(List<Data> paymentMethods) {
        String paymentMethodString = new Gson().toJson((Object) paymentMethods);
        if (paymentMethodString != null) {
            Bundle bundle = new Bundle();
            bundle.putString(LinkedAccountsPickerPresenter.PAYMENT_METHOD_LIST, paymentMethodString);
            this.mController.pushModalController(new LinkedAccountsPickerController(this.mController.appendModalArgs(bundle)));
        }
    }

    void routeToError(String errorTitle, String errorMessage) {
        Bundle bundle = new Bundle();
        bundle.putString(ApiErrorDialogController.ERROR_TITLE, errorTitle);
        bundle.putString("error_message", errorMessage);
        this.mController.pushDialogModalController(new ApiErrorDialogController(this.mController.appendModalArgs(bundle)));
    }

    void routeToGoToSettingsDialog() {
        this.mController.pushDialogModalController(new GoToSettingsDialogController(this.mController.appendModalArgs(new Bundle())));
    }

    void routeToSupportBlog() {
        this.mController.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(BLOG_URL)));
    }

    void routeToDepositConfirmation(com.coinbase.v2.models.transfers.Data transferData, com.coinbase.v2.models.account.Data account, Data selectedPaymentMethod, List<Data> validPaymentMethods) {
        Bundle args = new Bundle();
        Gson gson = new Gson();
        args.putString(TRANSFER, gson.toJson((Object) transferData));
        args.putString(PAYMENT_METHOD, gson.toJson((Object) selectedPaymentMethod));
        args.putString("selected_account", gson.toJson((Object) account));
        args.putString(VALID_PAYMENT_METHODS, gson.toJson((Object) validPaymentMethods));
        this.mController.pushModalController(new DepositFiatConfirmationController(this.mController.appendModalArgs(args)));
    }

    void closeModal() {
        ControllerLifeCycle controllerLifeCycle = this.mController.getControllerLifeCycle();
        if (controllerLifeCycle instanceof ModalControllerLifeCycle) {
            ((ModalControllerLifeCycle) controllerLifeCycle).closeModal();
        }
    }
}
