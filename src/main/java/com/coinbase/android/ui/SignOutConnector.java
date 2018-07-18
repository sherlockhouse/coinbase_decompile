package com.coinbase.android.ui;

import rx.subjects.PublishSubject;

public final class SignOutConnector {
    private final PublishSubject<Void> mSubject;

    public SignOutConnector() {
        this(PublishSubject.create());
    }

    public SignOutConnector(PublishSubject<Void> subject) {
        this.mSubject = subject;
    }

    public PublishSubject<Void> get() {
        return this.mSubject;
    }
}
