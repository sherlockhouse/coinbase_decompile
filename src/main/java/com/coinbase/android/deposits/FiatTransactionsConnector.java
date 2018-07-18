package com.coinbase.android.deposits;

import com.coinbase.android.ApplicationScope;
import com.coinbase.v2.models.transfers.Transfer;
import rx.subjects.PublishSubject;

@ApplicationScope
public class FiatTransactionsConnector {
    PublishSubject<Transfer> mSubject = PublishSubject.create();

    public PublishSubject<Transfer> get() {
        return this.mSubject;
    }
}
