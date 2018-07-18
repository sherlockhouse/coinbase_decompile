package com.coinbase.android.accounts;

import rx.functions.Action1;

final /* synthetic */ class AccountCryptoAddressPresenter$$Lambda$9 implements Action1 {
    private final AccountCryptoAddressPresenter arg$1;

    private AccountCryptoAddressPresenter$$Lambda$9(AccountCryptoAddressPresenter accountCryptoAddressPresenter) {
        this.arg$1 = accountCryptoAddressPresenter;
    }

    public static Action1 lambdaFactory$(AccountCryptoAddressPresenter accountCryptoAddressPresenter) {
        return new AccountCryptoAddressPresenter$$Lambda$9(accountCryptoAddressPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mScreen.hideCopySuccessfulDialog();
    }
}
