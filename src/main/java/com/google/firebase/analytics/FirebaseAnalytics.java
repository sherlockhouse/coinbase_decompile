package com.google.firebase.analytics;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Keep;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzccw;
import com.google.android.gms.tasks.Task;

@Keep
public final class FirebaseAnalytics {
    private final zzccw zziki;

    public static class Event {
    }

    public static class Param {
    }

    public static class UserProperty {
    }

    public FirebaseAnalytics(zzccw com_google_android_gms_internal_zzccw) {
        zzbp.zzu(com_google_android_gms_internal_zzccw);
        this.zziki = com_google_android_gms_internal_zzccw;
    }

    @Keep
    public static FirebaseAnalytics getInstance(Context context) {
        return zzccw.zzdn(context).zzayz();
    }

    public final Task<String> getAppInstanceId() {
        return this.zziki.zzatz().getAppInstanceId();
    }

    public final void logEvent(String str, Bundle bundle) {
        this.zziki.zzayy().logEvent(str, bundle);
    }

    public final void setAnalyticsCollectionEnabled(boolean z) {
        this.zziki.zzayy().setMeasurementEnabled(z);
    }

    @Keep
    public final void setCurrentScreen(Activity activity, String str, String str2) {
        this.zziki.zzaud().setCurrentScreen(activity, str, str2);
    }

    public final void setMinimumSessionDuration(long j) {
        this.zziki.zzayy().setMinimumSessionDuration(j);
    }

    public final void setSessionTimeoutDuration(long j) {
        this.zziki.zzayy().setSessionTimeoutDuration(j);
    }

    public final void setUserId(String str) {
        this.zziki.zzayy().setUserPropertyInternal("app", "_id", str);
    }

    public final void setUserProperty(String str, String str2) {
        this.zziki.zzayy().setUserProperty(str, str2);
    }
}
