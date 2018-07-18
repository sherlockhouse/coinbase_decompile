package com.coinbase.android.settings;

import com.coinbase.android.event.ClassConsumableEvent;
import rx.subjects.BehaviorSubject;

public class GoToSettingsConnector {
    private final BehaviorSubject<ClassConsumableEvent> mSubject;

    public GoToSettingsConnector() {
        this(BehaviorSubject.create());
    }

    GoToSettingsConnector(BehaviorSubject<ClassConsumableEvent> subject) {
        this.mSubject = subject;
    }

    public BehaviorSubject<ClassConsumableEvent> get() {
        return this.mSubject;
    }
}
