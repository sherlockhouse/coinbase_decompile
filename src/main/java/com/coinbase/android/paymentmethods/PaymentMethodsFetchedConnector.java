package com.coinbase.android.paymentmethods;

import com.coinbase.android.ApplicationScope;
import com.coinbase.android.ApplicationSignOutListener;
import com.coinbase.v2.models.paymentMethods.Data;
import java.util.ArrayList;
import java.util.List;
import rx.subjects.BehaviorSubject;

@ApplicationScope
public class PaymentMethodsFetchedConnector implements ApplicationSignOutListener {
    private BehaviorSubject<List<Data>> mSubject;

    public PaymentMethodsFetchedConnector() {
        this(BehaviorSubject.create(new ArrayList()));
    }

    public PaymentMethodsFetchedConnector(BehaviorSubject<List<Data>> subject) {
        this.mSubject = subject;
    }

    public BehaviorSubject<List<Data>> get() {
        return this.mSubject;
    }

    public void onApplicationSignOut() {
        this.mSubject = BehaviorSubject.create(new ArrayList());
    }
}
