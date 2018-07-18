package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbcl;
import java.util.List;

public final class zzi implements Creator<PasswordSpecification> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        List list = null;
        int zzd = zzbcl.zzd(parcel);
        int i2 = 0;
        List list2 = null;
        String str = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    str = zzbcl.zzq(parcel, readInt);
                    break;
                case 2:
                    list2 = zzbcl.zzac(parcel, readInt);
                    break;
                case 3:
                    list = zzbcl.zzab(parcel, readInt);
                    break;
                case 4:
                    i2 = zzbcl.zzg(parcel, readInt);
                    break;
                case 5:
                    i = zzbcl.zzg(parcel, readInt);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new PasswordSpecification(str, list2, list, i2, i);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new PasswordSpecification[i];
    }
}
