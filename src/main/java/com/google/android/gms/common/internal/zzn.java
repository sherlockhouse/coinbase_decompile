package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;

public final class zzn extends zze {
    private /* synthetic */ zzd zzftl;
    private IBinder zzftp;

    public zzn(zzd com_google_android_gms_common_internal_zzd, int i, IBinder iBinder, Bundle bundle) {
        this.zzftl = com_google_android_gms_common_internal_zzd;
        super(com_google_android_gms_common_internal_zzd, i, bundle);
        this.zzftp = iBinder;
    }

    protected final boolean zzajn() {
        try {
            String interfaceDescriptor = this.zzftp.getInterfaceDescriptor();
            if (this.zzftl.zzhd().equals(interfaceDescriptor)) {
                IInterface zzd = this.zzftl.zzd(this.zzftp);
                if (zzd == null) {
                    return false;
                }
                if (!this.zzftl.zza(2, 4, zzd) && !this.zzftl.zza(3, 4, zzd)) {
                    return false;
                }
                this.zzftl.zzftg = null;
                Bundle zzaeh = this.zzftl.zzaeh();
                if (this.zzftl.zzftc != null) {
                    this.zzftl.zzftc.onConnected(zzaeh);
                }
                return true;
            }
            String zzhd = this.zzftl.zzhd();
            Log.e("GmsClient", new StringBuilder((String.valueOf(zzhd).length() + 34) + String.valueOf(interfaceDescriptor).length()).append("service descriptor mismatch: ").append(zzhd).append(" vs. ").append(interfaceDescriptor).toString());
            return false;
        } catch (RemoteException e) {
            Log.w("GmsClient", "service probably died");
            return false;
        }
    }

    protected final void zzj(ConnectionResult connectionResult) {
        if (this.zzftl.zzftd != null) {
            this.zzftl.zzftd.onConnectionFailed(connectionResult);
        }
        this.zzftl.onConnectionFailed(connectionResult);
    }
}
