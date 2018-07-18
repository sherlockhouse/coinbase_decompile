package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbp;

public final class zzcbk extends zzbck {
    public static final Creator<zzcbk> CREATOR = new zzcbl();
    public final String name;
    public final String zzimg;
    public final zzcbh zzinr;
    public final long zzins;

    zzcbk(zzcbk com_google_android_gms_internal_zzcbk, long j) {
        zzbp.zzu(com_google_android_gms_internal_zzcbk);
        this.name = com_google_android_gms_internal_zzcbk.name;
        this.zzinr = com_google_android_gms_internal_zzcbk.zzinr;
        this.zzimg = com_google_android_gms_internal_zzcbk.zzimg;
        this.zzins = j;
    }

    public zzcbk(String str, zzcbh com_google_android_gms_internal_zzcbh, String str2, long j) {
        this.name = str;
        this.zzinr = com_google_android_gms_internal_zzcbh;
        this.zzimg = str2;
        this.zzins = j;
    }

    public final String toString() {
        String str = this.zzimg;
        String str2 = this.name;
        String valueOf = String.valueOf(this.zzinr);
        return new StringBuilder(((String.valueOf(str).length() + 21) + String.valueOf(str2).length()) + String.valueOf(valueOf).length()).append("origin=").append(str).append(",name=").append(str2).append(",params=").append(valueOf).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zza(parcel, 2, this.name, false);
        zzbcn.zza(parcel, 3, this.zzinr, i, false);
        zzbcn.zza(parcel, 4, this.zzimg, false);
        zzbcn.zza(parcel, 5, this.zzins);
        zzbcn.zzai(parcel, zze);
    }
}
