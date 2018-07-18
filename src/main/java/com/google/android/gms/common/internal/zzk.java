package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public final class zzk extends zzaw {
    private zzd zzftn;
    private final int zzfto;

    public zzk(zzd com_google_android_gms_common_internal_zzd, int i) {
        this.zzftn = com_google_android_gms_common_internal_zzd;
        this.zzfto = i;
    }

    public final void zza(int i, Bundle bundle) {
        Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
    }

    public final void zza(int i, IBinder iBinder, Bundle bundle) {
        zzbp.zzb(this.zzftn, (Object) "onPostInitComplete can be called only once per call to getRemoteService");
        this.zzftn.zza(i, iBinder, bundle, this.zzfto);
        this.zzftn = null;
    }
}
