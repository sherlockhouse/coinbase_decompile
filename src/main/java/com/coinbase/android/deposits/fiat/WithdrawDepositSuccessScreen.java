package com.coinbase.android.deposits.fiat;

interface WithdrawDepositSuccessScreen {
    void closeModal();

    void setAmountTransferred(String str);

    void setSuccessMessage(String str);
}
