package com.coinbase.android.idology;

import rx.subjects.PublishSubject;

public class IdologyAnswerListValidConnector {
    private final PublishSubject<Boolean> mSubject;

    public IdologyAnswerListValidConnector() {
        this(PublishSubject.create());
    }

    public IdologyAnswerListValidConnector(PublishSubject<Boolean> subject) {
        this.mSubject = subject;
    }

    public PublishSubject<Boolean> get() {
        return this.mSubject;
    }
}
