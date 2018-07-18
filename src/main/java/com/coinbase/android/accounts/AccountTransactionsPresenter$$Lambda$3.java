package com.coinbase.android.accounts;

import com.coinbase.v2.models.account.Data;
import rx.functions.Func1;

final /* synthetic */ class AccountTransactionsPresenter$$Lambda$3 implements Func1 {
    private final AccountTransactionsPresenter arg$1;

    private AccountTransactionsPresenter$$Lambda$3(AccountTransactionsPresenter accountTransactionsPresenter) {
        this.arg$1 = accountTransactionsPresenter;
    }

    public static Func1 lambdaFactory$(AccountTransactionsPresenter accountTransactionsPresenter) {
        return new AccountTransactionsPresenter$$Lambda$3(accountTransactionsPresenter);
    }

    public Object call(Object obj) {
        return Boolean.valueOf(this.arg$1.accountCurrencyEquals((Data) obj, this.arg$1.mSelectedAccount));
    }
}
