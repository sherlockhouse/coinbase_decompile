package com.coinbase.android.paymentmethods;

import android.app.Activity;
import java.util.List;

public interface IAVLoginScreen {
    void finishActivity();

    Activity getActivity();

    int getSelectedAccountTypePosition();

    void hideIAVForm();

    void hideKeyboard();

    void hideProgressDialog();

    void hideRoutingDetails();

    void initializeAccountTypeAdapter(List<AccountTypeSpinnerItem> list);

    void setInvestNowButtonText(String str);

    void showBankCdvVerificationAmountsForm();

    void showCdvVerificationAmountsForm();

    void showCdvVerificationForm();

    void showConfirmPlaidCancelScreen();

    void showContinueButtonDisabled();

    void showContinueButtonEnabled();

    void showCreateAchAccountProgress();

    void showProgressVerifying();

    void showRoutingDetails();

    boolean showingPlaidAllDoneView();

    boolean showingRoutingDetailsHint();

    void switchToAccountDetailsForm();

    void switchToAllDoneView(String str);

    void switchToIAVTypeForm();
}
