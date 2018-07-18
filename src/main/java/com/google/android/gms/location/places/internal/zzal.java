package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbcl;
import java.util.List;

public final class zzal implements Creator<zzak> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        List list = null;
        int zzd = zzbcl.zzd(parcel);
        String str = null;
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
                case 5:
                    list = zzbcl.zzac(parcel, readInt);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new zzak(str4, str3, str2, str, list);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzak[i];
    }
}
