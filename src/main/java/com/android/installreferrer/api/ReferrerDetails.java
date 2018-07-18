package com.android.installreferrer.api;

import android.os.Bundle;

public class ReferrerDetails {
    private final Bundle mOriginalBundle;

    public ReferrerDetails(Bundle referrerBundle) {
        this.mOriginalBundle = referrerBundle;
    }

    public String getInstallReferrer() {
        return this.mOriginalBundle.getString("install_referrer");
    }

    public long getReferrerClickTimestampSeconds() {
        return this.mOriginalBundle.getLong("referrer_click_timestamp_seconds");
    }

    public long getInstallBeginTimestampSeconds() {
        return this.mOriginalBundle.getLong("install_begin_timestamp_seconds");
    }
}
