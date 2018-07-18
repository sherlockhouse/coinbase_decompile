package com.coinbase.android.idology;

import rx.subjects.PublishSubject;

public class IdologyFormValidConnector {
    private final PublishSubject<Boolean> mSubject;

    public IdologyFormValidConnector() {
        this(PublishSubject.create());
    }

    public IdologyFormValidConnector(PublishSubject<Boolean> subject) {
        this.mSubject = subject;
    }

    public PublishSubject<Boolean> get() {
        return this.mSubject;
    }
}
