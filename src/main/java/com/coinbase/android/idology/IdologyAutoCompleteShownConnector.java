package com.coinbase.android.idology;

import rx.subjects.PublishSubject;

public class IdologyAutoCompleteShownConnector {
    private final PublishSubject<Boolean> mSubject;

    public IdologyAutoCompleteShownConnector() {
        this(PublishSubject.create());
    }

    IdologyAutoCompleteShownConnector(PublishSubject<Boolean> subject) {
        this.mSubject = subject;
    }

    public PublishSubject<Boolean> get() {
        return this.mSubject;
    }
}
