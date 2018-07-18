package com.coinbase.android.settings;

import com.coinbase.v2.models.user.Data;
import rx.subjects.BehaviorSubject;

public class UserUpdatedConnector {
    private final BehaviorSubject<Data> mSubject;

    public UserUpdatedConnector() {
        this(BehaviorSubject.create());
    }

    UserUpdatedConnector(BehaviorSubject<Data> subject) {
        this.mSubject = subject;
    }

    public BehaviorSubject<Data> get() {
        return this.mSubject;
    }
}
