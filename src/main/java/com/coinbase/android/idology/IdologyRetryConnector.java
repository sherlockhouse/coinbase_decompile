package com.coinbase.android.idology;

import rx.subjects.PublishSubject;

public class IdologyRetryConnector {
    private final PublishSubject<Boolean> mSubject;

    public IdologyRetryConnector() {
        this(PublishSubject.create());
    }

    public IdologyRetryConnector(PublishSubject<Boolean> subject) {
        this.mSubject = subject;
    }

    public PublishSubject<Boolean> get() {
        return this.mSubject;
    }
}
