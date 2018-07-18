package com.coinbase.android.accounts;

import com.coinbase.v2.models.account.Data;
import rx.functions.Action1;

final /* synthetic */ class AccountTransactionsPresenter$$Lambda$4 implements Action1 {
    private final AccountTransactionsPresenter arg$1;

    private AccountTransactionsPresenter$$Lambda$4(AccountTransactionsPresenter accountTransactionsPresenter) {
        this.arg$1 = accountTransactionsPresenter;
    }

    public static Action1 lambdaFactory$(AccountTransactionsPresenter accountTransactionsPresenter) {
        return new AccountTransactionsPresenter$$Lambda$4(accountTransactionsPresenter);
    }

    public void call(Object obj) {
        this.arg$1.onAccountUpdated((Data) obj);
    }
}
