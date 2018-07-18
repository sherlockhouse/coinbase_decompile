package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbcl;
import java.util.List;

public final class zzc implements Creator<AutocompleteFilter> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        String str = null;
        int zzd = zzbcl.zzd(parcel);
        boolean z = false;
        int i = 0;
        List list = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    z = zzbcl.zzc(parcel, readInt);
                    break;
                case 2:
                    list = zzbcl.zzab(parcel, readInt);
                    break;
                case 3:
                    str = zzbcl.zzq(parcel, readInt);
                    break;
                case 1000:
                    i = zzbcl.zzg(parcel, readInt);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new AutocompleteFilter(i, z, list, str);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new AutocompleteFilter[i];
    }
}
