package com.coinbase.android.accounts;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AccountTransactionsController$$Lambda$4 implements OnClickListener {
    private final AccountTransactionsController arg$1;

    private AccountTransactionsController$$Lambda$4(AccountTransactionsController accountTransactionsController) {
        this.arg$1 = accountTransactionsController;
    }

    public static OnClickListener lambdaFactory$(AccountTransactionsController accountTransactionsController) {
        return new AccountTransactionsController$$Lambda$4(accountTransactionsController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onSellButtonClicked();
    }
}
