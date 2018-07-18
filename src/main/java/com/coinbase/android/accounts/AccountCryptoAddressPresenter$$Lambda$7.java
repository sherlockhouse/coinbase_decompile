package com.coinbase.android.accounts;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class AccountCryptoAddressPresenter$$Lambda$7 implements Action1 {
    private final AccountCryptoAddressPresenter arg$1;

    private AccountCryptoAddressPresenter$$Lambda$7(AccountCryptoAddressPresenter accountCryptoAddressPresenter) {
        this.arg$1 = accountCryptoAddressPresenter;
    }

    public static Action1 lambdaFactory$(AccountCryptoAddressPresenter accountCryptoAddressPresenter) {
        return new AccountCryptoAddressPresenter$$Lambda$7(accountCryptoAddressPresenter);
    }

    public void call(Object obj) {
        AccountCryptoAddressPresenter.lambda$generateAccountCryptoAddress$6(this.arg$1, (Pair) obj);
    }
}
