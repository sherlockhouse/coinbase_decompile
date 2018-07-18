package com.coinbase.android.accounts;

import com.coinbase.v2.models.account.Data;
import rx.subjects.PublishSubject;

public class AccountUpdatedConnector {
    private final PublishSubject<Data> mSubject;

    public AccountUpdatedConnector() {
        this(PublishSubject.create());
    }

    public AccountUpdatedConnector(PublishSubject<Data> subject) {
        this.mSubject = subject;
    }

    public PublishSubject<Data> get() {
        return this.mSubject;
    }
}
