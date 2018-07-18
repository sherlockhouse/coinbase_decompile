package com.coinbase.android.settings;

import rx.subjects.PublishSubject;

public class LocalUserDataUpdatedConnector {
    private final PublishSubject<Void> mSubject;

    public LocalUserDataUpdatedConnector() {
        this(PublishSubject.create());
    }

    LocalUserDataUpdatedConnector(PublishSubject<Void> subject) {
        this.mSubject = subject;
    }

    public PublishSubject<Void> get() {
        return this.mSubject;
    }
}
