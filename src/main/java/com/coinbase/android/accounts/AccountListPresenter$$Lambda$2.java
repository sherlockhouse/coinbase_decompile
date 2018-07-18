package com.coinbase.android.accounts;

import rx.functions.Action1;

final /* synthetic */ class AccountListPresenter$$Lambda$2 implements Action1 {
    private final AccountListPresenter arg$1;

    private AccountListPresenter$$Lambda$2(AccountListPresenter accountListPresenter) {
        this.arg$1 = accountListPresenter;
    }

    public static Action1 lambdaFactory$(AccountListPresenter accountListPresenter) {
        return new AccountListPresenter$$Lambda$2(accountListPresenter);
    }

    public void call(Object obj) {
        this.arg$1.updateAccountList();
    }
}
