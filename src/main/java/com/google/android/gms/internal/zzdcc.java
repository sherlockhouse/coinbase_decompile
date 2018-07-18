package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzdcc extends zzbck {
    public static final Creator<zzdcc> CREATOR = new zzdcd();
    public int zzkil;

    public zzdcc(int i) {
        this.zzkil = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 2, this.zzkil);
        zzbcn.zzai(parcel, zze);
    }
}
