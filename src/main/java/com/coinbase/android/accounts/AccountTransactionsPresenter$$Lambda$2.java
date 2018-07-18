package com.coinbase.android.accounts;

import rx.functions.Action1;

final /* synthetic */ class AccountTransactionsPresenter$$Lambda$2 implements Action1 {
    private final AccountTransactionsPresenter arg$1;

    private AccountTransactionsPresenter$$Lambda$2(AccountTransactionsPresenter accountTransactionsPresenter) {
        this.arg$1 = accountTransactionsPresenter;
    }

    public static Action1 lambdaFactory$(AccountTransactionsPresenter accountTransactionsPresenter) {
        return new AccountTransactionsPresenter$$Lambda$2(accountTransactionsPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mScreen.hideAccountAddressLayout();
    }
}
