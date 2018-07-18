package com.coinbase.android.paymentmethods;

import android.app.Activity;
import android.content.Intent;

public interface AddPlaidAccountScreen {
    void clearSearchText();

    Activity getActivity();

    boolean isShown();

    void notifyBanksDataSetChanged();

    void popCurrentController();

    void startActivityForResult(Intent intent, int i);
}
