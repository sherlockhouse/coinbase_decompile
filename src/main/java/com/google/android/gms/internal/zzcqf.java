package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzbs;

public final class zzcqf extends zzbck {
    public static final Creator<zzcqf> CREATOR = new zzcqg();
    private int zzdxs;
    private final ConnectionResult zzfiz;
    private final zzbs zzjny;

    public zzcqf(int i) {
        this(new ConnectionResult(8, null), null);
    }

    zzcqf(int i, ConnectionResult connectionResult, zzbs com_google_android_gms_common_internal_zzbs) {
        this.zzdxs = i;
        this.zzfiz = connectionResult;
        this.zzjny = com_google_android_gms_common_internal_zzbs;
    }

    private zzcqf(ConnectionResult connectionResult, zzbs com_google_android_gms_common_internal_zzbs) {
        this(1, connectionResult, null);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.zzdxs);
        zzbcn.zza(parcel, 2, this.zzfiz, i, false);
        zzbcn.zza(parcel, 3, this.zzjny, i, false);
        zzbcn.zzai(parcel, zze);
    }

    public final ConnectionResult zzagd() {
        return this.zzfiz;
    }

    public final zzbs zzbcc() {
        return this.zzjny;
    }
}
