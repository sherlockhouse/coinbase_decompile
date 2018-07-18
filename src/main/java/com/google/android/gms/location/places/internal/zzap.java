package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbcl;
import java.util.List;

public final class zzap implements Creator<zzam> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        List list = null;
        int zzd = zzbcl.zzd(parcel);
        List list2 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    list2 = zzbcl.zzc(parcel, readInt, zzan.CREATOR);
                    break;
                case 2:
                    list = zzbcl.zzc(parcel, readInt, zzao.CREATOR);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new zzam(list2, list);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzam[i];
    }
}
