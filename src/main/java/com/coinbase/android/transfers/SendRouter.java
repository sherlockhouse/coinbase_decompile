package com.coinbase.android.transfers;

import android.os.Bundle;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.ApiErrorDialogController;
import com.coinbase.v2.models.account.Data;
import com.google.gson.Gson;
import javax.inject.Inject;
import org.joda.money.BigMoney;

@ControllerScope
public class SendRouter {
    private final ActionBarController mController;

    @Inject
    public SendRouter(ActionBarController controller) {
        this.mController = controller;
    }

    void routeShowTransferSend(BigMoney amount, Data selectedAccount, boolean isMax) {
        Bundle args = new Bundle();
        args.putSerializable(TransferSendPresenter.AMOUNT, amount);
        args.putString(TransferSendPresenter.ACCOUNT, new Gson().toJson((Object) selectedAccount));
        args.putSerializable(TransferSendPresenter.IS_SEND_MAX, Boolean.valueOf(isMax));
        this.mController.pushModalController(new TransferSendController(this.mController.appendModalArgs(args)));
    }

    void routeToError(String errorMessage) {
        Bundle bundle = new Bundle();
        bundle.putString("error_message", errorMessage);
        this.mController.pushDialogModalController(new ApiErrorDialogController(this.mController.appendModalArgs(bundle)));
    }
}
