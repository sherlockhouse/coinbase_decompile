package com.coinbase.android.idology;

import com.coinbase.api.internal.models.idology.Data;
import rx.subjects.PublishSubject;

public class IdologyVerificationConnector {
    private final PublishSubject<Data> mSubject;

    public IdologyVerificationConnector() {
        this(PublishSubject.create());
    }

    public IdologyVerificationConnector(PublishSubject<Data> subject) {
        this.mSubject = subject;
    }

    public PublishSubject<Data> get() {
        return this.mSubject;
    }
}
