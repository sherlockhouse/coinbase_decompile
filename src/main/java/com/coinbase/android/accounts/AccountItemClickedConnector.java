package com.coinbase.android.accounts;

import com.coinbase.v2.models.account.Data;
import rx.subjects.PublishSubject;

public class AccountItemClickedConnector {
    private final PublishSubject<Data> mSubject;

    public AccountItemClickedConnector() {
        this(PublishSubject.create());
    }

    public AccountItemClickedConnector(PublishSubject<Data> subject) {
        this.mSubject = subject;
    }

    public PublishSubject<Data> get() {
        return this.mSubject;
    }
}
