package com.coinbase.android.signin;

import com.coinbase.android.event.ClassConsumableEvent;
import rx.subjects.BehaviorSubject;

public class LoginSignUpFinishedConnector {
    private final BehaviorSubject<ClassConsumableEvent> mSubject;

    public LoginSignUpFinishedConnector() {
        this(BehaviorSubject.create());
    }

    LoginSignUpFinishedConnector(BehaviorSubject<ClassConsumableEvent> subject) {
        this.mSubject = subject;
    }

    public BehaviorSubject<ClassConsumableEvent> get() {
        return this.mSubject;
    }
}
