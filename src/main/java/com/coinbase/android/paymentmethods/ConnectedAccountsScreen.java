package com.coinbase.android.paymentmethods;

import com.coinbase.v2.models.paymentMethods.Data;

interface ConnectedAccountsScreen {
    void hideRemoveConfirmationDialog();

    void hideRemovePaymentFooterView();

    void notifyAccountListAdapterChanged();

    void showRemoveConfirmationDialog(Data data);

    void showRemovePaymentFooterView(Data data);
}
