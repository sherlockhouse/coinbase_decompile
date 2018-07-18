package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbq;

public final class zzcqd extends zzbck {
    public static final Creator<zzcqd> CREATOR = new zzcqe();
    private int zzdxs;
    private zzbq zzjnx;

    zzcqd(int i, zzbq com_google_android_gms_common_internal_zzbq) {
        this.zzdxs = i;
        this.zzjnx = com_google_android_gms_common_internal_zzbq;
    }

    public zzcqd(zzbq com_google_android_gms_common_internal_zzbq) {
        this(1, com_google_android_gms_common_internal_zzbq);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.zzdxs);
        zzbcn.zza(parcel, 2, this.zzjnx, i, false);
        zzbcn.zzai(parcel, zze);
    }
}
