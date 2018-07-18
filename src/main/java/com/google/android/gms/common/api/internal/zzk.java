package com.google.android.gms.common.api.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import com.google.android.gms.common.util.zzq;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public final class zzk implements ActivityLifecycleCallbacks, ComponentCallbacks2 {
    private static final zzk zzfis = new zzk();
    private final ArrayList<zzl> mListeners = new ArrayList();
    private boolean zzdoe = false;
    private final AtomicBoolean zzfit = new AtomicBoolean();
    private final AtomicBoolean zzfiu = new AtomicBoolean();

    private zzk() {
    }

    public static void zza(Application application) {
        synchronized (zzfis) {
            if (!zzfis.zzdoe) {
                application.registerActivityLifecycleCallbacks(zzfis);
                application.registerComponentCallbacks(zzfis);
                zzfis.zzdoe = true;
            }
        }
    }

    public static zzk zzafz() {
        return zzfis;
    }

    private final void zzbe(boolean z) {
        synchronized (zzfis) {
            ArrayList arrayList = this.mListeners;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                ((zzl) obj).zzbe(z);
            }
        }
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        boolean compareAndSet = this.zzfit.compareAndSet(true, false);
        this.zzfiu.set(true);
        if (compareAndSet) {
            zzbe(false);
        }
    }

    public final void onActivityDestroyed(Activity activity) {
    }

    public final void onActivityPaused(Activity activity) {
    }

    public final void onActivityResumed(Activity activity) {
        boolean compareAndSet = this.zzfit.compareAndSet(true, false);
        this.zzfiu.set(true);
        if (compareAndSet) {
            zzbe(false);
        }
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void onConfigurationChanged(Configuration configuration) {
    }

    public final void onLowMemory() {
    }

    public final void onTrimMemory(int i) {
        if (i == 20 && this.zzfit.compareAndSet(false, true)) {
            this.zzfiu.set(true);
            zzbe(true);
        }
    }

    public final void zza(zzl com_google_android_gms_common_api_internal_zzl) {
        synchronized (zzfis) {
            this.mListeners.add(com_google_android_gms_common_api_internal_zzl);
        }
    }

    @TargetApi(16)
    public final boolean zzbd(boolean z) {
        if (!this.zzfiu.get()) {
            if (!zzq.zzale()) {
                return true;
            }
            RunningAppProcessInfo runningAppProcessInfo = new RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            if (!this.zzfiu.getAndSet(true) && runningAppProcessInfo.importance > 100) {
                this.zzfit.set(true);
            }
        }
        return this.zzfit.get();
    }
}
