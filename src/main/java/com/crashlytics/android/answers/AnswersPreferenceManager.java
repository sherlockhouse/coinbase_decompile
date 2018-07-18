package com.crashlytics.android.answers;

import android.annotation.SuppressLint;
import android.content.Context;
import io.fabric.sdk.android.services.persistence.PreferenceStore;
import io.fabric.sdk.android.services.persistence.PreferenceStoreImpl;

class AnswersPreferenceManager {
    private final PreferenceStore prefStore;

    public static AnswersPreferenceManager build(Context context) {
        return new AnswersPreferenceManager(new PreferenceStoreImpl(context, "settings"));
    }

    AnswersPreferenceManager(PreferenceStore prefStore) {
        this.prefStore = prefStore;
    }

    @SuppressLint({"CommitPrefEdits"})
    public void setAnalyticsLaunched() {
        this.prefStore.save(this.prefStore.edit().putBoolean("analytics_launched", true));
    }

    @SuppressLint({"CommitPrefEdits"})
    public boolean hasAnalyticsLaunched() {
        return this.prefStore.get().getBoolean("analytics_launched", false);
    }
}
