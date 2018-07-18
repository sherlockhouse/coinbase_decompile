package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.zzat;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public final class zzm extends zzbck {
    public static final Creator<zzm> CREATOR = new zzn();
    private final String zzfft;
    private final zzg zzffu;
    private final boolean zzffv;

    zzm(String str, IBinder iBinder, boolean z) {
        this.zzfft = str;
        this.zzffu = zzai(iBinder);
        this.zzffv = z;
    }

    zzm(String str, zzg com_google_android_gms_common_zzg, boolean z) {
        this.zzfft = str;
        this.zzffu = com_google_android_gms_common_zzg;
        this.zzffv = z;
    }

    private static zzg zzai(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        try {
            zzg com_google_android_gms_common_zzh;
            IObjectWrapper zzaez = zzat.zzak(iBinder).zzaez();
            byte[] bArr = zzaez == null ? null : (byte[]) zzn.zzx(zzaez);
            if (bArr != null) {
                com_google_android_gms_common_zzh = new zzh(bArr);
            } else {
                Log.e("GoogleCertificatesQuery", "Could not unwrap certificate");
                com_google_android_gms_common_zzh = null;
            }
            return com_google_android_gms_common_zzh;
        } catch (Throwable e) {
            Log.e("GoogleCertificatesQuery", "Could not unwrap certificate", e);
            return null;
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        IBinder iBinder;
        int zze = zzbcn.zze(parcel);
        zzbcn.zza(parcel, 1, this.zzfft, false);
        if (this.zzffu == null) {
            Log.w("GoogleCertificatesQuery", "certificate binder is null");
            iBinder = null;
        } else {
            iBinder = this.zzffu.asBinder();
        }
        zzbcn.zza(parcel, 2, iBinder, false);
        zzbcn.zza(parcel, 3, this.zzffv);
        zzbcn.zzai(parcel, zze);
    }
}
