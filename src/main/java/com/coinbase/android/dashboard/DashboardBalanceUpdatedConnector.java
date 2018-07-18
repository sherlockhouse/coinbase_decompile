package com.coinbase.android.dashboard;

import com.coinbase.android.ApplicationSignOutListener;
import rx.subjects.BehaviorSubject;

public class DashboardBalanceUpdatedConnector implements ApplicationSignOutListener {
    private volatile BehaviorSubject<String> mSubject;

    public DashboardBalanceUpdatedConnector() {
        this(BehaviorSubject.create(""));
    }

    public DashboardBalanceUpdatedConnector(BehaviorSubject<String> subject) {
        this.mSubject = subject;
    }

    public BehaviorSubject<String> get() {
        return this.mSubject;
    }

    public void onApplicationSignOut() {
        this.mSubject = BehaviorSubject.create("");
    }
}
