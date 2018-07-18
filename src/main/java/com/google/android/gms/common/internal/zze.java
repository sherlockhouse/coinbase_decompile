package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

abstract class zze extends zzi<Boolean> {
    private int statusCode;
    private Bundle zzftk;
    private /* synthetic */ zzd zzftl;

    protected zze(zzd com_google_android_gms_common_internal_zzd, int i, Bundle bundle) {
        this.zzftl = com_google_android_gms_common_internal_zzd;
        super(com_google_android_gms_common_internal_zzd, Boolean.valueOf(true));
        this.statusCode = i;
        this.zzftk = bundle;
    }

    protected abstract boolean zzajn();

    protected abstract void zzj(ConnectionResult connectionResult);

    protected final /* synthetic */ void zzs(Object obj) {
        PendingIntent pendingIntent = null;
        if (((Boolean) obj) == null) {
            this.zzftl.zza(1, null);
            return;
        }
        switch (this.statusCode) {
            case 0:
                if (!zzajn()) {
                    this.zzftl.zza(1, null);
                    zzj(new ConnectionResult(8, null));
                    return;
                }
                return;
            case 10:
                this.zzftl.zza(1, null);
                throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
            default:
                this.zzftl.zza(1, null);
                if (this.zzftk != null) {
                    pendingIntent = (PendingIntent) this.zzftk.getParcelable("pendingIntent");
                }
                zzj(new ConnectionResult(this.statusCode, pendingIntent));
                return;
        }
    }
}
