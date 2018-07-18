package com.coinbase.android.paymentmethods;

import com.coinbase.v2.models.paymentMethods.Data;
import rx.subjects.PublishSubject;

public class VerifyPaymentMethodConnector {
    private final PublishSubject<Data> mSubject;

    public VerifyPaymentMethodConnector() {
        this(PublishSubject.create());
    }

    VerifyPaymentMethodConnector(PublishSubject<Data> subject) {
        this.mSubject = subject;
    }

    public PublishSubject<Data> get() {
        return this.mSubject;
    }
}
