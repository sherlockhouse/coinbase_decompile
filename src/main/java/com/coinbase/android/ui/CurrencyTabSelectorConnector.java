package com.coinbase.android.ui;

import com.coinbase.api.internal.models.currency.Data;
import rx.subjects.BehaviorSubject;

public final class CurrencyTabSelectorConnector {
    private final BehaviorSubject<Data> mSubject;

    public CurrencyTabSelectorConnector(Data initialValue) {
        this(BehaviorSubject.create(initialValue));
    }

    public CurrencyTabSelectorConnector(BehaviorSubject<Data> subject) {
        this.mSubject = subject;
    }

    public BehaviorSubject<Data> get() {
        return this.mSubject;
    }
}
