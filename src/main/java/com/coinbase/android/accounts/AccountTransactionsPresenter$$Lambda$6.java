package com.coinbase.android.accounts;

import com.coinbase.android.event.TransferMadeEvent;
import rx.functions.Action1;

final /* synthetic */ class AccountTransactionsPresenter$$Lambda$6 implements Action1 {
    private final AccountTransactionsPresenter arg$1;

    private AccountTransactionsPresenter$$Lambda$6(AccountTransactionsPresenter accountTransactionsPresenter) {
        this.arg$1 = accountTransactionsPresenter;
    }

    public static Action1 lambdaFactory$(AccountTransactionsPresenter accountTransactionsPresenter) {
        return new AccountTransactionsPresenter$$Lambda$6(accountTransactionsPresenter);
    }

    public void call(Object obj) {
        AccountTransactionsPresenter.lambda$onViewCreated$5(this.arg$1, (TransferMadeEvent) obj);
    }
}
