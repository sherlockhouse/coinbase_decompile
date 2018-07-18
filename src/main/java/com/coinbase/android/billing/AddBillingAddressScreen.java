package com.coinbase.android.billing;

import android.content.Intent;

public interface AddBillingAddressScreen {
    void dismissProgressDialog();

    void displayStateList();

    void finish();

    void setAddAddressButtonDisabled();

    void setAddAddressButtonEnabled();

    void setResult(int i, Intent intent);

    void setStateChosen(String str);

    void showNonUSPostalFields();

    void showProgressDialog();
}
