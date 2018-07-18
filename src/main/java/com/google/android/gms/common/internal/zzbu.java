package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public final class zzbu extends zzbck {
    public static final Creator<zzbu> CREATOR = new zzbv();
    private int zzdxs;
    private final int zzfwb;
    private final int zzfwc;
    @Deprecated
    private final Scope[] zzfwd;

    zzbu(int i, int i2, int i3, Scope[] scopeArr) {
        this.zzdxs = i;
        this.zzfwb = i2;
        this.zzfwc = i3;
        this.zzfwd = scopeArr;
    }

    public zzbu(int i, int i2, Scope[] scopeArr) {
        this(1, i, i2, null);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.zzdxs);
        zzbcn.zzc(parcel, 2, this.zzfwb);
        zzbcn.zzc(parcel, 3, this.zzfwc);
        zzbcn.zza(parcel, 4, this.zzfwd, i, false);
        zzbcn.zzai(parcel, zze);
    }
}
