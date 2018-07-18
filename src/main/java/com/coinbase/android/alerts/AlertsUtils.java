package com.coinbase.android.alerts;

import android.content.SharedPreferences;
import com.coinbase.android.ApplicationScope;
import com.coinbase.api.internal.models.alerts.Data;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;

@ApplicationScope
public class AlertsUtils {
    private static final String DISMISSED_ALERT_IDS = "dismissed_alert_ids";
    private final SharedPreferences mSharedPrefs;

    @Inject
    public AlertsUtils(SharedPreferences sharedPrefs) {
        this.mSharedPrefs = sharedPrefs;
    }

    public void updateDismissed(Data alertData) {
        if (alertData != null && alertData.getId() != null) {
            Set<String> dismissedIds = new HashSet();
            dismissedIds.addAll(this.mSharedPrefs.getStringSet(DISMISSED_ALERT_IDS, new HashSet()));
            dismissedIds.add(alertData.getId());
            this.mSharedPrefs.edit().putStringSet(DISMISSED_ALERT_IDS, dismissedIds).apply();
        }
    }

    public boolean isDismissed(Data alertData) {
        if (alertData == null || alertData.getDismissable() == null || !alertData.getDismissable().booleanValue()) {
            return false;
        }
        return this.mSharedPrefs.getStringSet(DISMISSED_ALERT_IDS, new HashSet()).contains(alertData.getId());
    }

    public void clear() {
        this.mSharedPrefs.edit().remove(DISMISSED_ALERT_IDS).apply();
    }
}
