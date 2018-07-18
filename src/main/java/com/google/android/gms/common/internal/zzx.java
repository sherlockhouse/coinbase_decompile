package com.google.android.gms.common.internal;

import android.content.Intent;
import com.google.android.gms.common.api.internal.zzcg;

final class zzx extends zzu {
    private /* synthetic */ Intent val$intent;
    private /* synthetic */ int val$requestCode;
    private /* synthetic */ zzcg zzftx;

    zzx(Intent intent, zzcg com_google_android_gms_common_api_internal_zzcg, int i) {
        this.val$intent = intent;
        this.zzftx = com_google_android_gms_common_api_internal_zzcg;
        this.val$requestCode = i;
    }

    public final void zzaka() {
        if (this.val$intent != null) {
            this.zzftx.startActivityForResult(this.val$intent, this.val$requestCode);
        }
    }
}
