package com.coinbase.android.buysell;

import com.coinbase.v2.models.account.Data;

public interface BuySellSuccessScreen {
    void closeModal();

    void gotoAccount(Data data);

    void setCryptoAmount(String str);

    void setSuccessMessage(String str);
}
