package com.coinbase.android.paymentmethods;

import com.coinbase.android.event.ClassConsumableEvent;
import rx.subjects.BehaviorSubject;

public class PaymentMethodsUpdatedConnector {
    private final BehaviorSubject<ClassConsumableEvent> mSubject;

    public PaymentMethodsUpdatedConnector() {
        this(BehaviorSubject.create());
    }

    PaymentMethodsUpdatedConnector(BehaviorSubject<ClassConsumableEvent> subject) {
        this.mSubject = subject;
    }

    public BehaviorSubject<ClassConsumableEvent> get() {
        return this.mSubject;
    }
}
