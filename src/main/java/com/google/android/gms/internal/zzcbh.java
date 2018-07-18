package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.Iterator;

public final class zzcbh extends zzbck implements Iterable<String> {
    public static final Creator<zzcbh> CREATOR = new zzcbj();
    private final Bundle zzino;

    zzcbh(Bundle bundle) {
        this.zzino = bundle;
    }

    final Object get(String str) {
        return this.zzino.get(str);
    }

    public final Iterator<String> iterator() {
        return new zzcbi(this);
    }

    public final int size() {
        return this.zzino.size();
    }

    public final String toString() {
        return this.zzino.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zza(parcel, 2, zzaxz(), false);
        zzbcn.zzai(parcel, zze);
    }

    public final Bundle zzaxz() {
        return new Bundle(this.zzino);
    }
}
