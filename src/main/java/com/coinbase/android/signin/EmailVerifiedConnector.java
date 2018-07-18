package com.coinbase.android.signin;

import com.coinbase.android.event.ClassConsumableEvent;
import rx.subjects.BehaviorSubject;

public class EmailVerifiedConnector {
    private final BehaviorSubject<ClassConsumableEvent<Type>> mSubject;

    public enum Type {
        FAILED,
        CHANGE_REQUESTED
    }

    public EmailVerifiedConnector() {
        this(BehaviorSubject.create());
    }

    EmailVerifiedConnector(BehaviorSubject<ClassConsumableEvent<Type>> subject) {
        this.mSubject = subject;
    }

    public BehaviorSubject<ClassConsumableEvent<Type>> get() {
        return this.mSubject;
    }
}
