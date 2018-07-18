package com.google.android.gms.common.api.internal;

final class zzdc implements Runnable {
    private /* synthetic */ String zzao;
    private /* synthetic */ LifecycleCallback zzfos;
    private /* synthetic */ zzdb zzfpg;

    zzdc(zzdb com_google_android_gms_common_api_internal_zzdb, LifecycleCallback lifecycleCallback, String str) {
        this.zzfpg = com_google_android_gms_common_api_internal_zzdb;
        this.zzfos = lifecycleCallback;
        this.zzao = str;
    }

    public final void run() {
        if (this.zzfpg.zzbyz > 0) {
            this.zzfos.onCreate(this.zzfpg.zzfor != null ? this.zzfpg.zzfor.getBundle(this.zzao) : null);
        }
        if (this.zzfpg.zzbyz >= 2) {
            this.zzfos.onStart();
        }
        if (this.zzfpg.zzbyz >= 3) {
            this.zzfos.onResume();
        }
        if (this.zzfpg.zzbyz >= 4) {
            this.zzfos.onStop();
        }
        if (this.zzfpg.zzbyz >= 5) {
            this.zzfos.onDestroy();
        }
    }
}
