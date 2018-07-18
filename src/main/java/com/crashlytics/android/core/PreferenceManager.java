package com.crashlytics.android.core;

import android.annotation.SuppressLint;
import io.fabric.sdk.android.services.persistence.PreferenceStore;
import io.fabric.sdk.android.services.persistence.PreferenceStoreImpl;

@SuppressLint({"CommitPrefEdits"})
class PreferenceManager {
    private final PreferenceStore preferenceStore;

    public static PreferenceManager create(PreferenceStore preferenceStore, CrashlyticsCore kit) {
        if (!preferenceStore.get().getBoolean("preferences_migration_complete", false)) {
            boolean migrationRequired;
            PreferenceStore oldStore = new PreferenceStoreImpl(kit);
            if (preferenceStore.get().contains("always_send_reports_opt_in") || !oldStore.get().contains("always_send_reports_opt_in")) {
                migrationRequired = false;
            } else {
                migrationRequired = true;
            }
            if (migrationRequired) {
                preferenceStore.save(preferenceStore.edit().putBoolean("always_send_reports_opt_in", oldStore.get().getBoolean("always_send_reports_opt_in", false)));
            }
            preferenceStore.save(preferenceStore.edit().putBoolean("preferences_migration_complete", true));
        }
        return new PreferenceManager(preferenceStore);
    }

    public PreferenceManager(PreferenceStore preferenceStore) {
        this.preferenceStore = preferenceStore;
    }

    void setShouldAlwaysSendReports(boolean send) {
        this.preferenceStore.save(this.preferenceStore.edit().putBoolean("always_send_reports_opt_in", send));
    }

    boolean shouldAlwaysSendReports() {
        return this.preferenceStore.get().getBoolean("always_send_reports_opt_in", false);
    }
}
