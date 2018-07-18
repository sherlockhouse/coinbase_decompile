package com.coinbase.android.buysell;

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
public class BuySellSuccessPresenter {
    public static final String BUY_SELL_SUCCESS_ACCOUNT = "BUY_SELL_SUCCESS_ACCOUNT";
    public static final String BUY_SELL_SUCCESS_TRANSFER_DATA = "BUY_SELL_SUCCESS_TRANSFER_DATA";
    protected Data mAccount;
    private final Context mContext;
    private final BuySellSuccessScreen mScreen;
    private final SnackBarWrapper mSnackBarWrapper;
    protected com.coinbase.v2.models.transfers.Data mTransferData;
    private final TransferUtils mTransferUtils;

    @Inject
    public BuySellSuccessPresenter(Application app, BuySellSuccessScreen screen, TransferUtils transferUtils, SnackBarWrapper snackBarWrapper) {
        this.mContext = app;
        this.mScreen = screen;
        this.mTransferUtils = transferUtils;
        this.mSnackBarWrapper = snackBarWrapper;
    }

    void onViewCreated(Bundle args) {
        Gson gson = new Gson();
        this.mTransferData = (com.coinbase.v2.models.transfers.Data) gson.fromJson(args.getString(BUY_SELL_SUCCESS_TRANSFER_DATA), com.coinbase.v2.models.transfers.Data.class);
        this.mAccount = (Data) gson.fromJson(args.getString(BUY_SELL_SUCCESS_ACCOUNT), Data.class);
        if (this.mTransferData == null || this.mAccount == null) {
            this.mSnackBarWrapper.showGenericError();
            return;
        }
        this.mScreen.setSuccessMessage(String.format(this.mContext.getString(R.string.transfer_success_message), new Object[]{this.mTransferData.getResource()}));
        this.mScreen.setCryptoAmount(this.mTransferUtils.getCurrencyAmountString(this.mTransferData));
    }

    void onAccountClicked() {
        if (this.mAccount == null) {
            this.mSnackBarWrapper.showGenericError();
            this.mScreen.closeModal();
            return;
        }
        this.mScreen.gotoAccount(this.mAccount);
    }
}
