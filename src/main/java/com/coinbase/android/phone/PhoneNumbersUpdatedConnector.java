package com.coinbase.android.phone;

import rx.subjects.PublishSubject;

public class PhoneNumbersUpdatedConnector {
    private final PublishSubject<Object> mSubject;

    public PhoneNumbersUpdatedConnector() {
        this(PublishSubject.create());
    }

    PhoneNumbersUpdatedConnector(PublishSubject<Object> subject) {
        this.mSubject = subject;
    }

    public PublishSubject<Object> get() {
        return this.mSubject;
    }
}
