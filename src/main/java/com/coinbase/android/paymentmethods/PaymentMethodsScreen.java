package com.coinbase.android.paymentmethods;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.coinbase.v2.models.paymentMethods.Data;

public interface PaymentMethodsScreen {
    Activity getActivity();

    Context getContext();

    void hideProgress();

    void hideRemovePaymentMethodView();

    boolean isShown();

    void notifyAvailablePaymentMethodsDataSetChanged();

    void notifyPaymentMethodsDataSetChanged();

    boolean onBackPressed();

    void popToRoot();

    void showAvailablePaymentMethodsAdapter();

    void showNoAvailablePaymentMethods();

    void showPaymentMethodsAdapter();

    void showRemovePaymentMethodConfirmed(Data data);

    void startActivityForResult(Intent intent, int i);
}
