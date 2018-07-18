package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzcfu implements Creator<zzcft> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Double d = null;
        int zzd = zzbcl.zzd(parcel);
        int i = 0;
        long j = 0;
        String str = null;
        String str2 = null;
        Float f = null;
        Long l = null;
        String str3 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = zzbcl.zzg(parcel, readInt);
                    break;
                case 2:
                    str3 = zzbcl.zzq(parcel, readInt);
                    break;
                case 3:
                    j = zzbcl.zzi(parcel, readInt);
                    break;
                case 4:
                    l = zzbcl.zzj(parcel, readInt);
                    break;
                case 5:
                    f = zzbcl.zzm(parcel, readInt);
                    break;
                case 6:
                    str2 = zzbcl.zzq(parcel, readInt);
                    break;
                case 7:
                    str = zzbcl.zzq(parcel, readInt);
                    break;
                case 8:
                    d = zzbcl.zzo(parcel, readInt);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new zzcft(i, str3, j, l, f, str2, str, d);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzcft[i];
    }
}
