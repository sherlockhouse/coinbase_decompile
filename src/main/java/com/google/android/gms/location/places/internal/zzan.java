package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public final class zzan extends zzbck {
    public static final Creator<zzan> CREATOR = new zze();
    private int zzidv;
    private int zzidw;

    zzan(int i, int i2) {
        this.zzidv = i;
        this.zzidw = i2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.zzidv);
        zzbcn.zzc(parcel, 2, this.zzidw);
        zzbcn.zzai(parcel, zze);
    }
}
