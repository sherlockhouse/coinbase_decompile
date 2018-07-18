package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.vision.Frame;

public final class zzdck extends zzbck {
    public static final Creator<zzdck> CREATOR = new zzdcl();
    public int height;
    private int id;
    public int rotation;
    public int width;
    private long zzhyv;

    public zzdck(int i, int i2, int i3, long j, int i4) {
        this.width = i;
        this.height = i2;
        this.id = i3;
        this.zzhyv = j;
        this.rotation = i4;
    }

    public static zzdck zzc(Frame frame) {
        zzdck com_google_android_gms_internal_zzdck = new zzdck();
        com_google_android_gms_internal_zzdck.width = frame.getMetadata().getWidth();
        com_google_android_gms_internal_zzdck.height = frame.getMetadata().getHeight();
        com_google_android_gms_internal_zzdck.rotation = frame.getMetadata().getRotation();
        com_google_android_gms_internal_zzdck.id = frame.getMetadata().getId();
        com_google_android_gms_internal_zzdck.zzhyv = frame.getMetadata().getTimestampMillis();
        return com_google_android_gms_internal_zzdck;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 2, this.width);
        zzbcn.zzc(parcel, 3, this.height);
        zzbcn.zzc(parcel, 4, this.id);
        zzbcn.zza(parcel, 5, this.zzhyv);
        zzbcn.zzc(parcel, 6, this.rotation);
        zzbcn.zzai(parcel, zze);
    }
}
