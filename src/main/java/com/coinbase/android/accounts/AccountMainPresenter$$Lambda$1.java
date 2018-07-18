package com.coinbase.android.accounts;

import com.coinbase.v2.models.account.Data;
import rx.functions.Action1;

final /* synthetic */ class AccountMainPresenter$$Lambda$1 implements Action1 {
    private final AccountMainPresenter arg$1;

    private AccountMainPresenter$$Lambda$1(AccountMainPresenter accountMainPresenter) {
        this.arg$1 = accountMainPresenter;
    }

    public static Action1 lambdaFactory$(AccountMainPresenter accountMainPresenter) {
        return new AccountMainPresenter$$Lambda$1(accountMainPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mScreen.gotoAccountTransactions((Data) obj, this.arg$1.getCurrency((Data) obj));
    }
}
