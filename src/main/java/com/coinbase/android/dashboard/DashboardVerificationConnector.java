package com.coinbase.android.dashboard;

import com.coinbase.android.ApplicationSignOutListener;
import rx.subjects.BehaviorSubject;

public class DashboardVerificationConnector implements ApplicationSignOutListener {
    private BehaviorSubject<String> mSubject;

    public DashboardVerificationConnector() {
        this(BehaviorSubject.create());
    }

    public DashboardVerificationConnector(BehaviorSubject<String> subject) {
        this.mSubject = subject;
    }

    public BehaviorSubject<String> get() {
        return this.mSubject;
    }

    public void onApplicationSignOut() {
        this.mSubject = BehaviorSubject.create();
    }
}
