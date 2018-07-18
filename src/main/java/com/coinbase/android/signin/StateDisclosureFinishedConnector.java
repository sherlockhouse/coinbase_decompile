package com.coinbase.android.signin;

import com.coinbase.android.event.ClassConsumableEvent;
import java.util.HashMap;
import rx.subjects.BehaviorSubject;

public final class StateDisclosureFinishedConnector {
    private final BehaviorSubject<ClassConsumableEvent<HashMap<String, String>>> mSubject;

    public StateDisclosureFinishedConnector() {
        this(BehaviorSubject.create());
    }

    StateDisclosureFinishedConnector(BehaviorSubject<ClassConsumableEvent<HashMap<String, String>>> subject) {
        this.mSubject = subject;
    }

    public BehaviorSubject<ClassConsumableEvent<HashMap<String, String>>> get() {
        return this.mSubject;
    }
}
