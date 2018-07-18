package com.coinbase.android.accounts;

import rx.functions.Action1;

final /* synthetic */ class AccountTransactionsPresenter$$Lambda$8 implements Action1 {
    private final AccountTransactionsPresenter arg$1;

    private AccountTransactionsPresenter$$Lambda$8(AccountTransactionsPresenter accountTransactionsPresenter) {
        this.arg$1 = accountTransactionsPresenter;
    }

    public static Action1 lambdaFactory$(AccountTransactionsPresenter accountTransactionsPresenter) {
        return new AccountTransactionsPresenter$$Lambda$8(accountTransactionsPresenter);
    }

    public void call(Object obj) {
        AccountTransactionsPresenter.lambda$onShow$7(this.arg$1, (Throwable) obj);
    }
}
