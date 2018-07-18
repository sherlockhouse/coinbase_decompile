package com.coinbase.android.buysell;

interface SellConfirmationScreen {
    void hideProgressBar();

    void hideProgressDialog();

    void hideSellButtonProgress();

    void showProgressBar();

    void showProgressDialog(String str);

    void showSellButtonProgress();
}
