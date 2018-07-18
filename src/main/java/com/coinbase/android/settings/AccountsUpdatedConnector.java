package com.coinbase.android.settings;

import com.coinbase.v2.models.account.Data;
import rx.subjects.PublishSubject;

public class AccountsUpdatedConnector {
    private final PublishSubject<Data> mSubject;

    public AccountsUpdatedConnector() {
        this(PublishSubject.create());
    }

    public AccountsUpdatedConnector(PublishSubject<Data> subject) {
        this.mSubject = subject;
    }

    public PublishSubject<Data> get() {
        return this.mSubject;
    }
}
