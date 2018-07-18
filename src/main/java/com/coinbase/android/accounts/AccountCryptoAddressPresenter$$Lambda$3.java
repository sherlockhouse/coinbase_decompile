package com.coinbase.android.accounts;

import com.coinbase.android.accounts.AccountCryptoAddressButtonConnector.AccountCryptoAddressButtonEvent;
import rx.functions.Func1;

final /* synthetic */ class AccountCryptoAddressPresenter$$Lambda$3 implements Func1 {
    private static final AccountCryptoAddressPresenter$$Lambda$3 instance = new AccountCryptoAddressPresenter$$Lambda$3();

    private AccountCryptoAddressPresenter$$Lambda$3() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return AccountCryptoAddressPresenter.lambda$onViewInit$2((AccountCryptoAddressButtonEvent) obj);
    }
}
