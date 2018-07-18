package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbp;

public final class zzcav extends zzbck {
    public static final Creator<zzcav> CREATOR = new zzcaw();
    public String packageName;
    private int versionCode;
    public String zzimg;
    public zzcft zzimh;
    public long zzimi;
    public boolean zzimj;
    public String zzimk;
    public zzcbk zziml;
    public long zzimm;
    public zzcbk zzimn;
    public long zzimo;
    public zzcbk zzimp;

    zzcav(int i, String str, String str2, zzcft com_google_android_gms_internal_zzcft, long j, boolean z, String str3, zzcbk com_google_android_gms_internal_zzcbk, long j2, zzcbk com_google_android_gms_internal_zzcbk2, long j3, zzcbk com_google_android_gms_internal_zzcbk3) {
        this.versionCode = i;
        this.packageName = str;
        this.zzimg = str2;
        this.zzimh = com_google_android_gms_internal_zzcft;
        this.zzimi = j;
        this.zzimj = z;
        this.zzimk = str3;
        this.zziml = com_google_android_gms_internal_zzcbk;
        this.zzimm = j2;
        this.zzimn = com_google_android_gms_internal_zzcbk2;
        this.zzimo = j3;
        this.zzimp = com_google_android_gms_internal_zzcbk3;
    }

    zzcav(zzcav com_google_android_gms_internal_zzcav) {
        this.versionCode = 1;
        zzbp.zzu(com_google_android_gms_internal_zzcav);
        this.packageName = com_google_android_gms_internal_zzcav.packageName;
        this.zzimg = com_google_android_gms_internal_zzcav.zzimg;
        this.zzimh = com_google_android_gms_internal_zzcav.zzimh;
        this.zzimi = com_google_android_gms_internal_zzcav.zzimi;
        this.zzimj = com_google_android_gms_internal_zzcav.zzimj;
        this.zzimk = com_google_android_gms_internal_zzcav.zzimk;
        this.zziml = com_google_android_gms_internal_zzcav.zziml;
        this.zzimm = com_google_android_gms_internal_zzcav.zzimm;
        this.zzimn = com_google_android_gms_internal_zzcav.zzimn;
        this.zzimo = com_google_android_gms_internal_zzcav.zzimo;
        this.zzimp = com_google_android_gms_internal_zzcav.zzimp;
    }

    zzcav(String str, String str2, zzcft com_google_android_gms_internal_zzcft, long j, boolean z, String str3, zzcbk com_google_android_gms_internal_zzcbk, long j2, zzcbk com_google_android_gms_internal_zzcbk2, long j3, zzcbk com_google_android_gms_internal_zzcbk3) {
        this.versionCode = 1;
        this.packageName = str;
        this.zzimg = str2;
        this.zzimh = com_google_android_gms_internal_zzcft;
        this.zzimi = j;
        this.zzimj = z;
        this.zzimk = str3;
        this.zziml = com_google_android_gms_internal_zzcbk;
        this.zzimm = j2;
        this.zzimn = com_google_android_gms_internal_zzcbk2;
        this.zzimo = j3;
        this.zzimp = com_google_android_gms_internal_zzcbk3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.versionCode);
        zzbcn.zza(parcel, 2, this.packageName, false);
        zzbcn.zza(parcel, 3, this.zzimg, false);
        zzbcn.zza(parcel, 4, this.zzimh, i, false);
        zzbcn.zza(parcel, 5, this.zzimi);
        zzbcn.zza(parcel, 6, this.zzimj);
        zzbcn.zza(parcel, 7, this.zzimk, false);
        zzbcn.zza(parcel, 8, this.zziml, i, false);
        zzbcn.zza(parcel, 9, this.zzimm);
        zzbcn.zza(parcel, 10, this.zzimn, i, false);
        zzbcn.zza(parcel, 11, this.zzimo);
        zzbcn.zza(parcel, 12, this.zzimp, i, false);
        zzbcn.zzai(parcel, zze);
    }
}
