package com.coinbase.android.accounts;

import com.coinbase.android.accounts.AccountCryptoAddressButtonConnector.AccountCryptoAddressButtonEvent;
import rx.functions.Func1;

final /* synthetic */ class AccountTransactionsPresenter$$Lambda$1 implements Func1 {
    private static final AccountTransactionsPresenter$$Lambda$1 instance = new AccountTransactionsPresenter$$Lambda$1();

    private AccountTransactionsPresenter$$Lambda$1() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return AccountTransactionsPresenter.lambda$onViewCreated$0((AccountCryptoAddressButtonEvent) obj);
    }
}
