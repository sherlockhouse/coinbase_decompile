package com.google.android.gms.common.api.internal;

abstract class zzbb implements Runnable {
    private /* synthetic */ zzar zzflx;

    private zzbb(zzar com_google_android_gms_common_api_internal_zzar) {
        this.zzflx = com_google_android_gms_common_api_internal_zzar;
    }

    public void run() {
        this.zzflx.zzfke.lock();
        try {
            if (!Thread.interrupted()) {
                zzagz();
                this.zzflx.zzfke.unlock();
            }
        } catch (RuntimeException e) {
            this.zzflx.zzflh.zza(e);
        } finally {
            this.zzflx.zzfke.unlock();
        }
    }

    protected abstract void zzagz();
}
