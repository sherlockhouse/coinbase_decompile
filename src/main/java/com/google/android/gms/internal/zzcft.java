package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbp;

public final class zzcft extends zzbck {
    public static final Creator<zzcft> CREATOR = new zzcfu();
    public final String name;
    private int versionCode;
    private String zzfwo;
    public final String zzimg;
    public final long zziwz;
    private Long zzixa;
    private Float zzixb;
    private Double zzixc;

    zzcft(int i, String str, long j, Long l, Float f, String str2, String str3, Double d) {
        Double d2 = null;
        this.versionCode = i;
        this.name = str;
        this.zziwz = j;
        this.zzixa = l;
        this.zzixb = null;
        if (i == 1) {
            if (f != null) {
                d2 = Double.valueOf(f.doubleValue());
            }
            this.zzixc = d2;
        } else {
            this.zzixc = d;
        }
        this.zzfwo = str2;
        this.zzimg = str3;
    }

    zzcft(zzcfv com_google_android_gms_internal_zzcfv) {
        this(com_google_android_gms_internal_zzcfv.mName, com_google_android_gms_internal_zzcfv.zzixd, com_google_android_gms_internal_zzcfv.mValue, com_google_android_gms_internal_zzcfv.mOrigin);
    }

    zzcft(String str, long j, Object obj, String str2) {
        zzbp.zzgg(str);
        this.versionCode = 2;
        this.name = str;
        this.zziwz = j;
        this.zzimg = str2;
        if (obj == null) {
            this.zzixa = null;
            this.zzixb = null;
            this.zzixc = null;
            this.zzfwo = null;
        } else if (obj instanceof Long) {
            this.zzixa = (Long) obj;
            this.zzixb = null;
            this.zzixc = null;
            this.zzfwo = null;
        } else if (obj instanceof String) {
            this.zzixa = null;
            this.zzixb = null;
            this.zzixc = null;
            this.zzfwo = (String) obj;
        } else if (obj instanceof Double) {
            this.zzixa = null;
            this.zzixb = null;
            this.zzixc = (Double) obj;
            this.zzfwo = null;
        } else {
            throw new IllegalArgumentException("User attribute given of un-supported type");
        }
    }

    public final Object getValue() {
        return this.zzixa != null ? this.zzixa : this.zzixc != null ? this.zzixc : this.zzfwo != null ? this.zzfwo : null;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.versionCode);
        zzbcn.zza(parcel, 2, this.name, false);
        zzbcn.zza(parcel, 3, this.zziwz);
        zzbcn.zza(parcel, 4, this.zzixa, false);
        zzbcn.zza(parcel, 5, null, false);
        zzbcn.zza(parcel, 6, this.zzfwo, false);
        zzbcn.zza(parcel, 7, this.zzimg, false);
        zzbcn.zza(parcel, 8, this.zzixc, false);
        zzbcn.zzai(parcel, zze);
    }
}
