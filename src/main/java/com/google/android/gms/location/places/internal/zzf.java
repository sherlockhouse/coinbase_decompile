package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbcl;
import java.util.List;

public final class zzf implements Creator<zzao> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        int zzd = zzbcl.zzd(parcel);
        List list = null;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i6 = zzbcl.zzg(parcel, readInt);
                    break;
                case 2:
                    i5 = zzbcl.zzg(parcel, readInt);
                    break;
                case 3:
                    i4 = zzbcl.zzg(parcel, readInt);
                    break;
                case 4:
                    i3 = zzbcl.zzg(parcel, readInt);
                    break;
                case 5:
                    i2 = zzbcl.zzg(parcel, readInt);
                    break;
                case 6:
                    i = zzbcl.zzg(parcel, readInt);
                    break;
                case 7:
                    list = zzbcl.zzc(parcel, readInt, zzan.CREATOR);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new zzao(i6, i5, i4, i3, i2, i, list);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzao[i];
    }
}
