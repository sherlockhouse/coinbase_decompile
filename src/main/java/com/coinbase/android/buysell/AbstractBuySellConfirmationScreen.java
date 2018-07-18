package com.coinbase.android.buysell;

import android.os.Bundle;
import com.coinbase.android.ui.ActionBarController;

public interface AbstractBuySellConfirmationScreen {
    void displayFeeHelpUrl(String str);

    Bundle getArgs();

    ActionBarController getController();

    void hideWarningMessage();

    void notifyDataSetChanged();

    void onConfirmCompleted();

    void setButtonText(String str);

    void setCryptoAmount(String str);

    void showWarningMessage(String str);
}
