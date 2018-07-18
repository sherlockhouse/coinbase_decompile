package com.coinbase.android.alerts;

import com.coinbase.android.ApplicationScope;
import com.coinbase.android.ApplicationSignOutListener;
import javax.inject.Inject;

@ApplicationScope
public class AlertsSignOutListener implements ApplicationSignOutListener {
    private final AlertsUtils mAlertsUtils;

    @Inject
    public AlertsSignOutListener(AlertsUtils alertsUtils) {
        this.mAlertsUtils = alertsUtils;
    }

    public void onApplicationSignOut() {
        this.mAlertsUtils.clear();
    }
}
