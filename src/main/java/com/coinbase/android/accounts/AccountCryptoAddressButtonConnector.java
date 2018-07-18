package com.coinbase.android.accounts;

import rx.subjects.PublishSubject;

public class AccountCryptoAddressButtonConnector {
    private final PublishSubject<AccountCryptoAddressButtonEvent> mAccountAddressButtonSubject;

    public enum AccountCryptoAddressButtonEvent {
        CLOSE,
        COPY,
        HELP
    }

    public AccountCryptoAddressButtonConnector() {
        this(PublishSubject.create());
    }

    public AccountCryptoAddressButtonConnector(PublishSubject<AccountCryptoAddressButtonEvent> accountAddressButtonSubject) {
        this.mAccountAddressButtonSubject = accountAddressButtonSubject;
    }

    public PublishSubject<AccountCryptoAddressButtonEvent> get() {
        return this.mAccountAddressButtonSubject;
    }
}
