package com.coinbase.android.dashboard;

import com.coinbase.android.ApplicationSignOutListener;
import com.coinbase.api.internal.models.alerts.Alerts;
import rx.subjects.BehaviorSubject;

public class DashboardAlertsConnector implements ApplicationSignOutListener {
    private BehaviorSubject<Alerts> mSubject;

    public DashboardAlertsConnector() {
        this(BehaviorSubject.create());
    }

    public DashboardAlertsConnector(BehaviorSubject<Alerts> subject) {
        this.mSubject = subject;
    }

    public BehaviorSubject<Alerts> get() {
        return this.mSubject;
    }

    public void onApplicationSignOut() {
        this.mSubject = BehaviorSubject.create();
    }
}
