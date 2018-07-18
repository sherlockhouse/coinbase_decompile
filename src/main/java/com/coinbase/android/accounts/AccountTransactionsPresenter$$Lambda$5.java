package com.coinbase.android.accounts;

import com.coinbase.android.event.TransferMadeEvent;
import rx.functions.Func1;

final /* synthetic */ class AccountTransactionsPresenter$$Lambda$5 implements Func1 {
    private static final AccountTransactionsPresenter$$Lambda$5 instance = new AccountTransactionsPresenter$$Lambda$5();

    private AccountTransactionsPresenter$$Lambda$5() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return AccountTransactionsPresenter.lambda$onViewCreated$4((TransferMadeEvent) obj);
    }
}
