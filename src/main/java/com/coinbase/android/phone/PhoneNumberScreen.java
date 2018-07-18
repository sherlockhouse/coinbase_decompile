package com.coinbase.android.phone;

import android.app.Activity;
import com.coinbase.android.ui.ActionBarController;

public interface PhoneNumberScreen {
    void deletePhoneNumber(String str, boolean z);

    Activity getActivity();

    ActionBarController getController();

    void hideProgressBar();

    boolean isShown();

    void notifyDataSetChanged();

    void showProgressBar();

    void showVerifyPhoneNumberDialog(String str);
}
