package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public final class Scope extends zzbck implements ReflectedParcelable {
    public static final Creator<Scope> CREATOR = new zzf();
    private int zzdxs;
    private final String zzfhu;

    Scope(int i, String str) {
        zzbp.zzh(str, "scopeUri must not be null or empty");
        this.zzdxs = i;
        this.zzfhu = str;
    }

    public Scope(String str) {
        this(1, str);
    }

    public final boolean equals(Object obj) {
        return this == obj ? true : !(obj instanceof Scope) ? false : this.zzfhu.equals(((Scope) obj).zzfhu);
    }

    public final int hashCode() {
        return this.zzfhu.hashCode();
    }

    public final String toString() {
        return this.zzfhu;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.zzdxs);
        zzbcn.zza(parcel, 2, this.zzfhu, false);
        zzbcn.zzai(parcel, zze);
    }

    public final String zzaft() {
        return this.zzfhu;
    }
}
