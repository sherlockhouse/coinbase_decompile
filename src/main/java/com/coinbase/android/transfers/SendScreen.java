package com.coinbase.android.transfers;

import android.os.Bundle;
import android.text.SpannableStringBuilder;

interface SendScreen {
    Bundle getArgs();

    void hideProgressDialog();

    void hideSendMaxButtons();

    void showInvalidKeystroke();

    void showProgressDialog();

    void showSendMaxButtons();

    void updatePrimaryAmountText(SpannableStringBuilder spannableStringBuilder);

    void updateTitle(String str);
}
