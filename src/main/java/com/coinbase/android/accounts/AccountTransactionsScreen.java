package com.coinbase.android.accounts;

import android.app.Activity;
import android.view.Menu;
import com.coinbase.android.deposits.FiatTransactionsController.Type;
import com.coinbase.v2.models.account.Data;

public interface AccountTransactionsScreen {
    Activity getActivity();

    void hideAccountAddressLayout();

    void hideButtonLayout();

    void hideDetailLayout();

    void hideEnableSendReceive(boolean z);

    boolean isAccountAddressLayoutVisible();

    boolean isDetailLayoutVisible();

    boolean isShowingEnableSendReceive();

    void showAccountAddressLayout();

    void showAccountLabels(String str, String str2, String str3);

    void showAddressMenu(Menu menu, boolean z);

    void showBuyOption();

    void showDepositOption(boolean z);

    void showEnableSendReceive(String str, String str2, String str3, int i);

    void showEnableSendReceiveProgress(boolean z);

    void showFiatDepositWithdraw(Data data, Type type);

    void showSellOption();

    void showSendMenu(Menu menu, boolean z);

    void showSepaDeposits();

    void showWithdrawOption(boolean z);

    void updateTransactionDefaultList(Data data);

    void updateTransactionFiatList(Data data);

    void updateTransactionWalletList(Data data);
}
