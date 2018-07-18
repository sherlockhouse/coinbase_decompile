package com.coinbase.android.paymentmethods;

import rx.subjects.PublishSubject;

public class PlaidOnExitConnector {
    private final PublishSubject<Void> mSubject;

    public PlaidOnExitConnector() {
        this(PublishSubject.create());
    }

    PlaidOnExitConnector(PublishSubject<Void> subject) {
        this.mSubject = subject;
    }

    public PublishSubject<Void> get() {
        return this.mSubject;
    }
}
