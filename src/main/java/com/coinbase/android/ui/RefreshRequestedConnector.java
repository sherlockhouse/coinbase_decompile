package com.coinbase.android.ui;

import rx.subjects.PublishSubject;

public class RefreshRequestedConnector {
    private final PublishSubject<Void> mSubject;

    public RefreshRequestedConnector() {
        this(PublishSubject.create());
    }

    public RefreshRequestedConnector(PublishSubject<Void> subject) {
        this.mSubject = subject;
    }

    public PublishSubject<Void> get() {
        return this.mSubject;
    }
}
