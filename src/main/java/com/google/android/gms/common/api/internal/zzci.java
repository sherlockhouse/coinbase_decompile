package com.google.android.gms.common.api.internal;

final class zzci implements Runnable {
    private /* synthetic */ String zzao;
    private /* synthetic */ LifecycleCallback zzfos;
    private /* synthetic */ zzch zzfot;

    zzci(zzch com_google_android_gms_common_api_internal_zzch, LifecycleCallback lifecycleCallback, String str) {
        this.zzfot = com_google_android_gms_common_api_internal_zzch;
        this.zzfos = lifecycleCallback;
        this.zzao = str;
    }

    public final void run() {
        if (this.zzfot.zzbyz > 0) {
            this.zzfos.onCreate(this.zzfot.zzfor != null ? this.zzfot.zzfor.getBundle(this.zzao) : null);
        }
        if (this.zzfot.zzbyz >= 2) {
            this.zzfos.onStart();
        }
        if (this.zzfot.zzbyz >= 3) {
            this.zzfos.onResume();
        }
        if (this.zzfot.zzbyz >= 4) {
            this.zzfos.onStop();
        }
        if (this.zzfot.zzbyz >= 5) {
            this.zzfos.onDestroy();
        }
    }
}
