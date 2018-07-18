package com.coinbase.android.deposits.fiat;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.TransferUtils;
import com.coinbase.v2.models.account.Data;
import com.google.gson.Gson;
import javax.inject.Inject;

@ControllerScope
public class WithdrawDepositSuccessPresenter {
    static final String DEPOSIT_WITHDRAW_SUCCESS_ACCOUNT = "DEPOSIT_WITHDRAW_SUCCESS_ACCOUNT";
    static final String DEPOSIT_WITHDRAW_SUCCESS_TRANSFER_DATA = "DEPOSIT_WITHDRAW_SUCCESS_TRANSFER_DATA";
    private Data mAccount;
    private com.coinbase.v2.models.transfers.Data mCompletedTransferData;
    private final Context mContext;
    private final WithdrawDepositSuccessRouter mRouter;
    private final WithdrawDepositSuccessScreen mScreen;
    private final SnackBarWrapper mSnackBarWrapper;
    private final TransferUtils mTransferUtils;

    @Inject
    public WithdrawDepositSuccessPresenter(Application application, WithdrawDepositSuccessScreen screen, WithdrawDepositSuccessRouter withdrawDepositSuccessRouter, SnackBarWrapper snackBarWrapper, TransferUtils transferUtils) {
        this.mContext = application;
        this.mScreen = screen;
        this.mRouter = withdrawDepositSuccessRouter;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mTransferUtils = transferUtils;
    }

    public void onViewCreated(Bundle args) {
        Gson gson = new Gson();
        this.mCompletedTransferData = (com.coinbase.v2.models.transfers.Data) gson.fromJson(args.getString(DEPOSIT_WITHDRAW_SUCCESS_TRANSFER_DATA), com.coinbase.v2.models.transfers.Data.class);
        this.mAccount = (Data) gson.fromJson(args.getString(DEPOSIT_WITHDRAW_SUCCESS_ACCOUNT), Data.class);
        if (this.mCompletedTransferData == null || this.mAccount == null) {
            this.mSnackBarWrapper.showGenericError();
            return;
        }
        this.mScreen.setSuccessMessage(String.format(this.mContext.getString(R.string.transfer_success_message), new Object[]{this.mCompletedTransferData.getResource()}));
        this.mScreen.setAmountTransferred(this.mTransferUtils.getCurrencyAmountString(this.mCompletedTransferData));
    }

    public void onAccountClicked() {
        if (this.mAccount == null) {
            this.mSnackBarWrapper.showGenericError();
            this.mScreen.closeModal();
            return;
        }
        this.mScreen.closeModal();
        this.mRouter.routeBackToAccount(this.mAccount);
    }
}
