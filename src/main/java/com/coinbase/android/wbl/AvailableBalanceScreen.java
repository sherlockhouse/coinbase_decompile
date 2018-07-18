package com.coinbase.android.wbl;

public interface AvailableBalanceScreen {
    void hideProgress();

    void notifyFundsOnHold();

    void setTotalAvailableBalance(String str);

    void setTotalAvailableBalanceSum(String str);

    void setTotalPendingFunds(String str);

    void setTotalPortfolioBalance(String str);

    void showProgress();
}
