package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbcl;

public final class zzau implements Creator<zzat> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        String str = null;
        int zzd = zzbcl.zzd(parcel);
        int i2 = 0;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    str4 = zzbcl.zzq(parcel, readInt);
                    break;
                case 2:
                    str3 = zzbcl.zzq(parcel, readInt);
                    break;
                case 3:
                    str2 = zzbcl.zzq(parcel, readInt);
                    break;
                case 4:
                    str = zzbcl.zzq(parcel, readInt);
                    break;
                case 6:
                    i2 = zzbcl.zzg(parcel, readInt);
                    break;
                case 7:
                    i = zzbcl.zzg(parcel, readInt);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new zzat(str4, str3, str2, str, i2, i);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzat[i];
    }
}
