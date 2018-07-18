package com.coinbase.android.paymentmethods;

import com.coinbase.android.event.ClassConsumableEvent;
import rx.subjects.BehaviorSubject;

public class AddPaymentMethodConnector {
    private final BehaviorSubject<ClassConsumableEvent> mSubject;

    public AddPaymentMethodConnector() {
        this(BehaviorSubject.create());
    }

    AddPaymentMethodConnector(BehaviorSubject<ClassConsumableEvent> subject) {
        this.mSubject = subject;
    }

    public BehaviorSubject<ClassConsumableEvent> get() {
        return this.mSubject;
    }
}
