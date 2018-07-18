package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Collections;
import java.util.List;

public final class zzao extends zzbck {
    public static final Creator<zzao> CREATOR = new zzf();
    private int zzidx;
    private int zzidy;
    private int zzidz;
    private int zziea;
    private int zzieb;
    private int zziec;
    private List<zzan> zzied;

    zzao(int i, int i2, int i3, int i4, int i5, int i6, List<zzan> list) {
        this.zzidx = i;
        this.zzidy = i2;
        this.zzidz = i3;
        this.zziea = i4;
        this.zzieb = i5;
        this.zziec = i6;
        this.zzied = Collections.unmodifiableList(list);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.zzidx);
        zzbcn.zzc(parcel, 2, this.zzidy);
        zzbcn.zzc(parcel, 3, this.zzidz);
        zzbcn.zzc(parcel, 4, this.zziea);
        zzbcn.zzc(parcel, 5, this.zzieb);
        zzbcn.zzc(parcel, 6, this.zziec);
        zzbcn.zzc(parcel, 7, this.zzied, false);
        zzbcn.zzai(parcel, zze);
    }
}
