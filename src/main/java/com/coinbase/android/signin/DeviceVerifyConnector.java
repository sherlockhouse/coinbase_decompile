package com.coinbase.android.signin;

import com.coinbase.android.event.ClassConsumableEvent;
import rx.subjects.BehaviorSubject;

public final class DeviceVerifyConnector {
    private final BehaviorSubject<ClassConsumableEvent<Exception>> mFailedSubject;

    public DeviceVerifyConnector() {
        this(BehaviorSubject.create());
    }

    DeviceVerifyConnector(BehaviorSubject<ClassConsumableEvent<Exception>> failedSubject) {
        this.mFailedSubject = failedSubject;
    }

    public BehaviorSubject<ClassConsumableEvent<Exception>> getFailed() {
        return this.mFailedSubject;
    }
}
