package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbp;

public final class zzcas extends zzbck {
    public static final Creator<zzcas> CREATOR = new zzcat();
    public final String packageName;
    public final String zzhtt;
    public final String zzilu;
    public final String zzilv;
    public final long zzilw;
    public final long zzilx;
    public final String zzily;
    public final boolean zzilz;
    public final boolean zzima;
    public final long zzimb;
    public final String zzimc;
    public final long zzimd;
    public final long zzime;
    public final int zzimf;

    zzcas(String str, String str2, String str3, long j, String str4, long j2, long j3, String str5, boolean z, boolean z2, String str6, long j4, long j5, int i) {
        zzbp.zzgg(str);
        this.packageName = str;
        if (TextUtils.isEmpty(str2)) {
            str2 = null;
        }
        this.zzilu = str2;
        this.zzhtt = str3;
        this.zzimb = j;
        this.zzilv = str4;
        this.zzilw = j2;
        this.zzilx = j3;
        this.zzily = str5;
        this.zzilz = z;
        this.zzima = z2;
        this.zzimc = str6;
        this.zzimd = j4;
        this.zzime = j5;
        this.zzimf = i;
    }

    zzcas(String str, String str2, String str3, String str4, long j, long j2, String str5, boolean z, boolean z2, long j3, String str6, long j4, long j5, int i) {
        this.packageName = str;
        this.zzilu = str2;
        this.zzhtt = str3;
        this.zzimb = j3;
        this.zzilv = str4;
        this.zzilw = j;
        this.zzilx = j2;
        this.zzily = str5;
        this.zzilz = z;
        this.zzima = z2;
        this.zzimc = str6;
        this.zzimd = j4;
        this.zzime = j5;
        this.zzimf = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zza(parcel, 2, this.packageName, false);
        zzbcn.zza(parcel, 3, this.zzilu, false);
        zzbcn.zza(parcel, 4, this.zzhtt, false);
        zzbcn.zza(parcel, 5, this.zzilv, false);
        zzbcn.zza(parcel, 6, this.zzilw);
        zzbcn.zza(parcel, 7, this.zzilx);
        zzbcn.zza(parcel, 8, this.zzily, false);
        zzbcn.zza(parcel, 9, this.zzilz);
        zzbcn.zza(parcel, 10, this.zzima);
        zzbcn.zza(parcel, 11, this.zzimb);
        zzbcn.zza(parcel, 12, this.zzimc, false);
        zzbcn.zza(parcel, 13, this.zzimd);
        zzbcn.zza(parcel, 14, this.zzime);
        zzbcn.zzc(parcel, 15, this.zzimf);
        zzbcn.zzai(parcel, zze);
    }
}
