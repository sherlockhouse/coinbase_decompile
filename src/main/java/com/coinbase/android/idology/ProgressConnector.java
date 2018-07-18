package com.coinbase.android.idology;

import rx.subjects.PublishSubject;

public class ProgressConnector {
    private final PublishSubject<Boolean> mSubject;

    public ProgressConnector() {
        this(PublishSubject.create());
    }

    ProgressConnector(PublishSubject<Boolean> subject) {
        this.mSubject = subject;
    }

    public PublishSubject<Boolean> get() {
        return this.mSubject;
    }
}
