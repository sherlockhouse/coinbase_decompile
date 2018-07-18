package com.coinbase.android.identityverification;

import org.apache.commons.lang3.mutable.MutableBoolean;
import rx.subjects.BehaviorSubject;

public class RetakeAndContinueConnector {
    private final BehaviorSubject<MutableBoolean> mContinueSubject;
    private final BehaviorSubject<MutableBoolean> mRetakeSubject;

    public RetakeAndContinueConnector() {
        this(BehaviorSubject.create(new MutableBoolean(false)), BehaviorSubject.create(new MutableBoolean(false)));
    }

    RetakeAndContinueConnector(BehaviorSubject<MutableBoolean> retakeSubject, BehaviorSubject<MutableBoolean> continueSubject) {
        this.mRetakeSubject = retakeSubject;
        this.mContinueSubject = continueSubject;
    }

    public BehaviorSubject<MutableBoolean> getRetake() {
        return this.mRetakeSubject;
    }

    public BehaviorSubject<MutableBoolean> getContinue() {
        return this.mContinueSubject;
    }
}
