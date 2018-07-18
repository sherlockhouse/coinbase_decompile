package com.google.android.gms.common.api.internal;

final class zzbq implements zzl {
    private /* synthetic */ zzbp zzfnu;

    zzbq(zzbp com_google_android_gms_common_api_internal_zzbp) {
        this.zzfnu = com_google_android_gms_common_api_internal_zzbp;
    }

    public final void zzbe(boolean z) {
        this.zzfnu.mHandler.sendMessage(this.zzfnu.mHandler.obtainMessage(1, Boolean.valueOf(z)));
    }
}
