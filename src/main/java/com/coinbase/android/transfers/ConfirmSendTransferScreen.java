package com.coinbase.android.transfers;

import android.content.Intent;
import android.text.Spanned;

public interface ConfirmSendTransferScreen {
    void dismissDialog();

    void handleRequestError(Intent intent, int i);

    void handleSuccess(Intent intent);

    void setMessage(Spanned spanned);

    void showCounterParty(String str);

    void showTransferSendProgress();
}
