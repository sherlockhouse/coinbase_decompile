package com.coinbase.android.transfers;

import com.coinbase.android.event.TransferMadeEvent;
import rx.subjects.BehaviorSubject;

public class TransferMadeConnector {
    private final BehaviorSubject<TransferMadeEvent> mSubject;

    public TransferMadeConnector() {
        this(BehaviorSubject.create());
    }

    public TransferMadeConnector(BehaviorSubject<TransferMadeEvent> subject) {
        this.mSubject = subject;
    }

    public BehaviorSubject<TransferMadeEvent> get() {
        return this.mSubject;
    }
}
