package com.coinbase.android.accounts;

import rx.subjects.PublishSubject;

public class AccountCryptoAddressUpdatedConnector {
    private final PublishSubject<Void> mSubject;

    public AccountCryptoAddressUpdatedConnector() {
        this(PublishSubject.create());
    }

    public AccountCryptoAddressUpdatedConnector(PublishSubject<Void> subject) {
        this.mSubject = subject;
    }

    public PublishSubject<Void> get() {
        return this.mSubject;
    }
}
