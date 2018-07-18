package com.coinbase.android.dashboard;

import rx.subjects.PublishSubject;

public class DashboardRefreshConnector {
    private final PublishSubject<Void> mSubject;

    public DashboardRefreshConnector() {
        this(PublishSubject.create());
    }

    public DashboardRefreshConnector(PublishSubject<Void> subject) {
        this.mSubject = subject;
    }

    public PublishSubject<Void> get() {
        return this.mSubject;
    }
}
