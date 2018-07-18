package com.coinbase.android.wbl;

public interface WithdrawalBasedLimitsDialogScreen {
    void dismiss();

    void routeToAvailableBalance();

    void setTextFields(String str, String str2, String str3, String str4);
}
