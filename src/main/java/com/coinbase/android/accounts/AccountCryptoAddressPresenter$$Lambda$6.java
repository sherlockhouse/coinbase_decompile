package com.coinbase.android.accounts;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class AccountCryptoAddressPresenter$$Lambda$6 implements Func1 {
    private final AccountCryptoAddressPresenter arg$1;

    private AccountCryptoAddressPresenter$$Lambda$6(AccountCryptoAddressPresenter accountCryptoAddressPresenter) {
        this.arg$1 = accountCryptoAddressPresenter;
    }

    public static Func1 lambdaFactory$(AccountCryptoAddressPresenter accountCryptoAddressPresenter) {
        return new AccountCryptoAddressPresenter$$Lambda$6(accountCryptoAddressPresenter);
    }

    public Object call(Object obj) {
        return AccountCryptoAddressPresenter.lambda$generateAccountCryptoAddress$5(this.arg$1, (Pair) obj);
    }
}
