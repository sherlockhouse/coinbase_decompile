package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.credentials.Credential;

public final class zzasw extends zzbck {
    public static final Creator<zzasw> CREATOR = new zzasx();
    private final Credential zzebg;

    public zzasw(Credential credential) {
        this.zzebg = credential;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zza(parcel, 1, this.zzebg, i, false);
        zzbcn.zzai(parcel, zze);
    }
}
