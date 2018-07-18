package com.coinbase.android.buysell;

interface BuyConfirmationScreen {
    void hideBuyButtonProgress();

    void hideProgressBar();

    void hideProgressDialog();

    void hideWorldPayInfo();

    void showBuyButtonProgress();

    void showProgressBar();

    void showProgressDialog(String str);

    void showWorldPayInfo();
}
