package com.coinbase.android.dashboard;

import rx.subjects.PublishSubject;

public final class DashboardTabPeriodSelectionConnector {
    private final PublishSubject<TabPeriod> mSubject;

    public DashboardTabPeriodSelectionConnector() {
        this(PublishSubject.create());
    }

    DashboardTabPeriodSelectionConnector(PublishSubject<TabPeriod> subject) {
        this.mSubject = subject;
    }

    public PublishSubject<TabPeriod> get() {
        return this.mSubject;
    }
}
