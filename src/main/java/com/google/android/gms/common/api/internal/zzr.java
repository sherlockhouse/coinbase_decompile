package com.google.android.gms.common.api.internal;

import android.app.Dialog;

final class zzr extends zzbz {
    private /* synthetic */ Dialog zzfjc;
    private /* synthetic */ zzq zzfjd;

    zzr(zzq com_google_android_gms_common_api_internal_zzq, Dialog dialog) {
        this.zzfjd = com_google_android_gms_common_api_internal_zzq;
        this.zzfjc = dialog;
    }

    public final void zzage() {
        this.zzfjd.zzfjb.zzagb();
        if (this.zzfjc.isShowing()) {
            this.zzfjc.dismiss();
        }
    }
}
