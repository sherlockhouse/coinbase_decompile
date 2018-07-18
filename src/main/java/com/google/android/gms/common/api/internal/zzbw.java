package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import java.util.Collections;

final class zzbw implements Runnable {
    private /* synthetic */ ConnectionResult zzfoe;
    private /* synthetic */ zzbv zzfog;

    zzbw(zzbv com_google_android_gms_common_api_internal_zzbv, ConnectionResult connectionResult) {
        this.zzfog = com_google_android_gms_common_api_internal_zzbv;
        this.zzfoe = connectionResult;
    }

    public final void run() {
        if (this.zzfoe.isSuccess()) {
            this.zzfog.zzfof = true;
            if (this.zzfog.zzfkh.zzaac()) {
                this.zzfog.zzaid();
                return;
            } else {
                this.zzfog.zzfkh.zza(null, Collections.emptySet());
                return;
            }
        }
        ((zzbr) this.zzfog.zzfnu.zzfkk.get(this.zzfog.zzfgs)).onConnectionFailed(this.zzfoe);
    }
}
