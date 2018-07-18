package com.coinbase.android.billing;

import com.coinbase.api.internal.models.billingAddress.Data;
import rx.subjects.PublishSubject;

public final class BillingAddressDeletedConnector {
    private final PublishSubject<Data> mBillingAddressSubject;

    public BillingAddressDeletedConnector() {
        this(PublishSubject.create());
    }

    public BillingAddressDeletedConnector(PublishSubject<Data> billingAddressSubject) {
        this.mBillingAddressSubject = billingAddressSubject;
    }

    public PublishSubject<Data> get() {
        return this.mBillingAddressSubject;
    }
}
