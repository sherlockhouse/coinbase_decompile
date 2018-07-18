package com.coinbase.android.accounts;

import android.util.Pair;
import rx.functions.Action1;
import rx.functions.Func4;

final /* synthetic */ class AccountTransactionsPresenter$$Lambda$11 implements Action1 {
    private final AccountTransactionsPresenter arg$1;
    private final Func4 arg$2;
    private final boolean arg$3;

    private AccountTransactionsPresenter$$Lambda$11(AccountTransactionsPresenter accountTransactionsPresenter, Func4 func4, boolean z) {
        this.arg$1 = accountTransactionsPresenter;
        this.arg$2 = func4;
        this.arg$3 = z;
    }

    public static Action1 lambdaFactory$(AccountTransactionsPresenter accountTransactionsPresenter, Func4 func4, boolean z) {
        return new AccountTransactionsPresenter$$Lambda$11(accountTransactionsPresenter, func4, z);
    }

    public void call(Object obj) {
        AccountTransactionsPresenter.lambda$checkPolicyRestrictions$10(this.arg$1, this.arg$2, this.arg$3, (Pair) obj);
    }
}
