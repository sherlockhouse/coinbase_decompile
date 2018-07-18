package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzcaw implements Creator<zzcav> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzbcl.zzd(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        zzcft com_google_android_gms_internal_zzcft = null;
        long j = 0;
        boolean z = false;
        String str3 = null;
        zzcbk com_google_android_gms_internal_zzcbk = null;
        long j2 = 0;
        zzcbk com_google_android_gms_internal_zzcbk2 = null;
        long j3 = 0;
        zzcbk com_google_android_gms_internal_zzcbk3 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = zzbcl.zzg(parcel, readInt);
                    break;
                case 2:
                    str = zzbcl.zzq(parcel, readInt);
                    break;
                case 3:
                    str2 = zzbcl.zzq(parcel, readInt);
                    break;
                case 4:
                    com_google_android_gms_internal_zzcft = (zzcft) zzbcl.zza(parcel, readInt, zzcft.CREATOR);
                    break;
                case 5:
                    j = zzbcl.zzi(parcel, readInt);
                    break;
                case 6:
                    z = zzbcl.zzc(parcel, readInt);
                    break;
                case 7:
                    str3 = zzbcl.zzq(parcel, readInt);
                    break;
                case 8:
                    com_google_android_gms_internal_zzcbk = (zzcbk) zzbcl.zza(parcel, readInt, zzcbk.CREATOR);
                    break;
                case 9:
                    j2 = zzbcl.zzi(parcel, readInt);
                    break;
                case 10:
                    com_google_android_gms_internal_zzcbk2 = (zzcbk) zzbcl.zza(parcel, readInt, zzcbk.CREATOR);
                    break;
                case 11:
                    j3 = zzbcl.zzi(parcel, readInt);
                    break;
                case 12:
                    com_google_android_gms_internal_zzcbk3 = (zzcbk) zzbcl.zza(parcel, readInt, zzcbk.CREATOR);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new zzcav(i, str, str2, com_google_android_gms_internal_zzcft, j, z, str3, com_google_android_gms_internal_zzcbk, j2, com_google_android_gms_internal_zzcbk2, j3, com_google_android_gms_internal_zzcbk3);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzcav[i];
    }
}
