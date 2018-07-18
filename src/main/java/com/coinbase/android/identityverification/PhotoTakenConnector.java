package com.coinbase.android.identityverification;

import org.apache.commons.lang3.mutable.MutableBoolean;
import rx.subjects.BehaviorSubject;

public class PhotoTakenConnector {
    private final BehaviorSubject<MutableBoolean> mSubject = BehaviorSubject.create(new MutableBoolean(false));

    public BehaviorSubject<MutableBoolean> get() {
        return this.mSubject;
    }
}
