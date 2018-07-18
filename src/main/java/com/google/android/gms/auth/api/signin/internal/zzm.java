package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbcl;

public final class zzm implements Creator<zzn> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        int zzd = zzbcl.zzd(parcel);
        Bundle bundle = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i2 = zzbcl.zzg(parcel, readInt);
                    break;
                case 2:
                    i = zzbcl.zzg(parcel, readInt);
                    break;
                case 3:
                    bundle = zzbcl.zzs(parcel, readInt);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new zzn(i2, i, bundle);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzn[i];
    }
}
