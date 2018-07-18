package com.google.android.gms.common.api.internal;

final class zzz implements Runnable {
    private /* synthetic */ zzy zzfkg;

    zzz(zzy com_google_android_gms_common_api_internal_zzy) {
        this.zzfkg = com_google_android_gms_common_api_internal_zzy;
    }

    public final void run() {
        this.zzfkg.zzfke.lock();
        try {
            this.zzfkg.zzagj();
        } finally {
            this.zzfkg.zzfke.unlock();
        }
    }
}
