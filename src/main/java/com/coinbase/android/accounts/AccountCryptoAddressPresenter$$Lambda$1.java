package com.coinbase.android.accounts;

import rx.functions.Func1;

final /* synthetic */ class AccountCryptoAddressPresenter$$Lambda$1 implements Func1 {
    private final AccountCryptoAddressPresenter arg$1;

    private AccountCryptoAddressPresenter$$Lambda$1(AccountCryptoAddressPresenter accountCryptoAddressPresenter) {
        this.arg$1 = accountCryptoAddressPresenter;
    }

    public static Func1 lambdaFactory$(AccountCryptoAddressPresenter accountCryptoAddressPresenter) {
        return new AccountCryptoAddressPresenter$$Lambda$1(accountCryptoAddressPresenter);
    }

    public Object call(Object obj) {
        return this.arg$1.getCryptoAddressBitmap();
    }
}
