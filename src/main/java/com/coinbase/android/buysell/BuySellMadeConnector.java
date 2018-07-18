package com.coinbase.android.buysell;

import com.coinbase.v2.models.account.Data;
import rx.subjects.PublishSubject;

public class BuySellMadeConnector {
    private final PublishSubject<Data> mSubject;

    public BuySellMadeConnector() {
        this(PublishSubject.create());
    }

    BuySellMadeConnector(PublishSubject<Data> subject) {
        this.mSubject = subject;
    }

    public PublishSubject<Data> get() {
        return this.mSubject;
    }
}
