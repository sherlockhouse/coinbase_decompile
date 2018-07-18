package com.google.android.gms.common.api.internal;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzam;
import com.google.android.gms.common.internal.zzj;
import java.util.Set;

final class zzbv implements zzcy, zzj {
    private Set<Scope> zzecm = null;
    private final zzh<?> zzfgs;
    private final zze zzfkh;
    private zzam zzflt = null;
    final /* synthetic */ zzbp zzfnu;
    private boolean zzfof = false;

    public zzbv(zzbp com_google_android_gms_common_api_internal_zzbp, zze com_google_android_gms_common_api_Api_zze, zzh<?> com_google_android_gms_common_api_internal_zzh_) {
        this.zzfnu = com_google_android_gms_common_api_internal_zzbp;
        this.zzfkh = com_google_android_gms_common_api_Api_zze;
        this.zzfgs = com_google_android_gms_common_api_internal_zzh_;
    }

    private final void zzaid() {
        if (this.zzfof && this.zzflt != null) {
            this.zzfkh.zza(this.zzflt, this.zzecm);
        }
    }

    public final void zzb(zzam com_google_android_gms_common_internal_zzam, Set<Scope> set) {
        if (com_google_android_gms_common_internal_zzam == null || set == null) {
            Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
            zzh(new ConnectionResult(4));
            return;
        }
        this.zzflt = com_google_android_gms_common_internal_zzam;
        this.zzecm = set;
        zzaid();
    }

    public final void zzf(ConnectionResult connectionResult) {
        this.zzfnu.mHandler.post(new zzbw(this, connectionResult));
    }

    public final void zzh(ConnectionResult connectionResult) {
        ((zzbr) this.zzfnu.zzfkk.get(this.zzfgs)).zzh(connectionResult);
    }
}
