package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbq;

public final class zzcqe implements Creator<zzcqd> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzbcl.zzd(parcel);
        int i = 0;
        zzbq com_google_android_gms_common_internal_zzbq = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = zzbcl.zzg(parcel, readInt);
                    break;
                case 2:
                    com_google_android_gms_common_internal_zzbq = (zzbq) zzbcl.zza(parcel, readInt, zzbq.CREATOR);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new zzcqd(i, com_google_android_gms_common_internal_zzbq);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzcqd[i];
    }
}
