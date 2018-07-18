package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public final class zzcpv extends zzbck implements Result {
    public static final Creator<zzcpv> CREATOR = new zzcpw();
    private int zzdxs;
    private int zzjnt;
    private Intent zzjnu;

    public zzcpv() {
        this(0, null);
    }

    zzcpv(int i, int i2, Intent intent) {
        this.zzdxs = i;
        this.zzjnt = i2;
        this.zzjnu = intent;
    }

    private zzcpv(int i, Intent intent) {
        this(2, 0, null);
    }

    public final Status getStatus() {
        return this.zzjnt == 0 ? Status.zzfhv : Status.zzfhz;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.zzdxs);
        zzbcn.zzc(parcel, 2, this.zzjnt);
        zzbcn.zza(parcel, 3, this.zzjnu, i, false);
        zzbcn.zzai(parcel, zze);
    }
}
