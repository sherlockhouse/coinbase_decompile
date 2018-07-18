package com.coinbase.android.accounts;

import android.graphics.Bitmap;
import rx.functions.Action1;

final /* synthetic */ class AccountCryptoAddressPresenter$$Lambda$2 implements Action1 {
    private final AccountCryptoAddressPresenter arg$1;

    private AccountCryptoAddressPresenter$$Lambda$2(AccountCryptoAddressPresenter accountCryptoAddressPresenter) {
        this.arg$1 = accountCryptoAddressPresenter;
    }

    public static Action1 lambdaFactory$(AccountCryptoAddressPresenter accountCryptoAddressPresenter) {
        return new AccountCryptoAddressPresenter$$Lambda$2(accountCryptoAddressPresenter);
    }

    public void call(Object obj) {
        this.arg$1.onReceiveCryptoAddressUpdated((Bitmap) obj);
    }
}
