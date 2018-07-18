package com.coinbase.android.accounts;

import java.util.List;
import rx.functions.Action1;

final /* synthetic */ class AccountListPresenter$$Lambda$3 implements Action1 {
    private final AccountListPresenter arg$1;

    private AccountListPresenter$$Lambda$3(AccountListPresenter accountListPresenter) {
        this.arg$1 = accountListPresenter;
    }

    public static Action1 lambdaFactory$(AccountListPresenter accountListPresenter) {
        return new AccountListPresenter$$Lambda$3(accountListPresenter);
    }

    public void call(Object obj) {
        this.arg$1.setAccountList((List) obj);
    }
}
