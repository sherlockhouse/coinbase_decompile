package com.coinbase.android.settings;

import com.coinbase.v2.models.account.Data;
import rx.subjects.PublishSubject;

public class AccountDeleteRequestConnector {
    private final PublishSubject<Data> mSubject;

    public AccountDeleteRequestConnector() {
        this(PublishSubject.create());
    }

    AccountDeleteRequestConnector(PublishSubject<Data> subject) {
        this.mSubject = subject;
    }

    public PublishSubject<Data> get() {
        return this.mSubject;
    }
}
