package com.coinbase.android.wbl;

public interface AvailableBalanceAppBarScreen {
    void hideAvailableBalanceClickable();

    void registerOnClickListener(AvailableBalanceAppBarPresenter availableBalanceAppBarPresenter);

    void setAvailableBalance(String str);

    void showAvailableBalanceClickable();
}
