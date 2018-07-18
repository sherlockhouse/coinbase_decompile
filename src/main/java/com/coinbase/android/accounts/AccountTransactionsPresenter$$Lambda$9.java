package com.coinbase.android.accounts;

import rx.functions.Func4;

final /* synthetic */ class AccountTransactionsPresenter$$Lambda$9 implements Func4 {
    private final AccountTransactionsPresenter arg$1;

    private AccountTransactionsPresenter$$Lambda$9(AccountTransactionsPresenter accountTransactionsPresenter) {
        this.arg$1 = accountTransactionsPresenter;
    }

    public static Func4 lambdaFactory$(AccountTransactionsPresenter accountTransactionsPresenter) {
        return new AccountTransactionsPresenter$$Lambda$9(accountTransactionsPresenter);
    }

    public Object call(Object obj, Object obj2, Object obj3, Object obj4) {
        return AccountTransactionsPresenter.lambda$onShowSendClicked$8(this.arg$1, (Boolean) obj, (String) obj2, (String) obj3, (String) obj4);
    }
}
