package com.coinbase.android.signin;

import java.util.HashMap;
import rx.subjects.PublishSubject;

public class StateListSelectorConnector {
    private final PublishSubject<HashMap<String, String>> mStateSelectorSubject;

    public StateListSelectorConnector() {
        this(PublishSubject.create());
    }

    public StateListSelectorConnector(PublishSubject<HashMap<String, String>> stateSelectorSubject) {
        this.mStateSelectorSubject = stateSelectorSubject;
    }

    public PublishSubject<HashMap<String, String>> get() {
        return this.mStateSelectorSubject;
    }
}
