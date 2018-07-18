package com.coinbase.android.paymentmethods.linkedaccounts;

import android.util.Pair;
import com.coinbase.android.event.ClassConsumableEvent;
import com.coinbase.v2.models.paymentMethods.Data;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

public class LinkedAccountConnector {
    private final PublishSubject<Void> mLinkedAccountClickedSubject;
    private final PublishSubject<Void> mMissingAccountClickedSubject;
    private final BehaviorSubject<Pair<ClassConsumableEvent, Data>> mPaymentMethodSelectedSubject;

    public LinkedAccountConnector() {
        this(PublishSubject.create(), PublishSubject.create(), BehaviorSubject.create());
    }

    public LinkedAccountConnector(PublishSubject<Void> linkedAccountClickedSubject, PublishSubject<Void> missingAccountClickedSubject, BehaviorSubject<Pair<ClassConsumableEvent, Data>> paymentMethodSelectedSubject) {
        this.mLinkedAccountClickedSubject = linkedAccountClickedSubject;
        this.mMissingAccountClickedSubject = missingAccountClickedSubject;
        this.mPaymentMethodSelectedSubject = paymentMethodSelectedSubject;
    }

    public PublishSubject<Void> getLinkedAccountClickedSubject() {
        return this.mLinkedAccountClickedSubject;
    }

    public PublishSubject<Void> getMissingAccountClickedSubject() {
        return this.mMissingAccountClickedSubject;
    }

    public BehaviorSubject<Pair<ClassConsumableEvent, Data>> getPaymentMethodSelectedSubject() {
        return this.mPaymentMethodSelectedSubject;
    }
}
