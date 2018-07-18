package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public final class zzc extends zzbck {
    public static final Creator<zzc> CREATOR = new zzd();
    private String name;
    private int version;

    public zzc(String str, int i) {
        this.name = str;
        this.version = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zza(parcel, 1, this.name, false);
        zzbcn.zzc(parcel, 2, this.version);
        zzbcn.zzai(parcel, zze);
    }
}
