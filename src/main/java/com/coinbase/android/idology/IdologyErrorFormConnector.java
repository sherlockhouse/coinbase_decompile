package com.coinbase.android.idology;

import com.coinbase.api.internal.models.idology.Data;
import rx.subjects.PublishSubject;

public class IdologyErrorFormConnector {
    private final PublishSubject<Data> mSubject;

    public IdologyErrorFormConnector() {
        this(PublishSubject.create());
    }

    public IdologyErrorFormConnector(PublishSubject<Data> subject) {
        this.mSubject = subject;
    }

    public PublishSubject<Data> get() {
        return this.mSubject;
    }
}
