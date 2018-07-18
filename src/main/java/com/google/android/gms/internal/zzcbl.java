package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzcbl implements Creator<zzcbk> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        String str = null;
        int zzd = zzbcl.zzd(parcel);
        long j = 0;
        zzcbh com_google_android_gms_internal_zzcbh = null;
        String str2 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str2 = zzbcl.zzq(parcel, readInt);
                    break;
                case 3:
                    com_google_android_gms_internal_zzcbh = (zzcbh) zzbcl.zza(parcel, readInt, zzcbh.CREATOR);
                    break;
                case 4:
                    str = zzbcl.zzq(parcel, readInt);
                    break;
                case 5:
                    j = zzbcl.zzi(parcel, readInt);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new zzcbk(str2, com_google_android_gms_internal_zzcbh, str, j);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzcbk[i];
    }
}
