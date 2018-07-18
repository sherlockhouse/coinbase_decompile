package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

@TargetApi(14)
final class zzcej implements ActivityLifecycleCallbacks {
    private /* synthetic */ zzcdw zziuy;

    private zzcej(zzcdw com_google_android_gms_internal_zzcdw) {
        this.zziuy = com_google_android_gms_internal_zzcdw;
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        try {
            this.zziuy.zzaul().zzayj().log("onActivityCreated");
            Intent intent = activity.getIntent();
            if (intent != null) {
                Uri data = intent.getData();
                if (data != null && data.isHierarchical()) {
                    if (bundle == null) {
                        Bundle zzp = this.zziuy.zzauh().zzp(data);
                        this.zziuy.zzauh();
                        String str = zzcfw.zzl(intent) ? "gs" : "auto";
                        if (zzp != null) {
                            this.zziuy.zzc(str, "_cmp", zzp);
                        }
                    }
                    CharSequence queryParameter = data.getQueryParameter("referrer");
                    if (!TextUtils.isEmpty(queryParameter)) {
                        Object obj = (queryParameter.contains("gclid") && (queryParameter.contains("utm_campaign") || queryParameter.contains("utm_source") || queryParameter.contains("utm_medium") || queryParameter.contains("utm_term") || queryParameter.contains("utm_content"))) ? 1 : null;
                        if (obj == null) {
                            this.zziuy.zzaul().zzayi().log("Activity created with data 'referrer' param without gclid and at least one utm field");
                            return;
                        }
                        this.zziuy.zzaul().zzayi().zzj("Activity created with referrer", queryParameter);
                        if (!TextUtils.isEmpty(queryParameter)) {
                            this.zziuy.zzb("auto", "_ldl", queryParameter);
                        }
                    } else {
                        return;
                    }
                }
            }
        } catch (Throwable th) {
            this.zziuy.zzaul().zzayd().zzj("Throwable caught in onActivityCreated", th);
        }
        zzcek zzaud = this.zziuy.zzaud();
        if (bundle != null) {
            Bundle bundle2 = bundle.getBundle("com.google.firebase.analytics.screen_service");
            if (bundle2 != null) {
                zzcen zzq = zzaud.zzq(activity);
                zzq.zzikp = bundle2.getLong("id");
                zzq.zzikn = bundle2.getString("name");
                zzq.zziko = bundle2.getString("referrer_name");
            }
        }
    }

    public final void onActivityDestroyed(Activity activity) {
        this.zziuy.zzaud().onActivityDestroyed(activity);
    }

    public final void onActivityPaused(Activity activity) {
        this.zziuy.zzaud().onActivityPaused(activity);
        zzcdt zzauj = this.zziuy.zzauj();
        zzauj.zzauk().zzg(new zzcfp(zzauj, zzauj.zzvx().elapsedRealtime()));
    }

    public final void onActivityResumed(Activity activity) {
        this.zziuy.zzaud().onActivityResumed(activity);
        zzcdt zzauj = this.zziuy.zzauj();
        zzauj.zzauk().zzg(new zzcfo(zzauj, zzauj.zzvx().elapsedRealtime()));
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.zziuy.zzaud().onActivitySaveInstanceState(activity, bundle);
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }
}
