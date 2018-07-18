package com.coinbase.android.paymentmethods;

import android.webkit.WebView;
import com.coinbase.android.ui.ActionBarController;

public interface PlaidScreen {
    void closeForm();

    void closeModal();

    ActionBarController getController();

    WebView getWebView();

    void hideProgressDialog();

    void showProgressDialog();
}
