package com.coinbase.android.paymentmethods;

import android.content.Intent;
import com.coinbase.android.ui.ActionBarController;

public interface AddBankPickerScreen {
    void closeModal();

    ActionBarController getController();

    void startActivity(Intent intent);
}
