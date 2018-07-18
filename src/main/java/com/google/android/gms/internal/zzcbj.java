package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzcbj implements Creator<zzcbh> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzbcl.zzd(parcel);
        Bundle bundle = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    bundle = zzbcl.zzs(parcel, readInt);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new zzcbh(bundle);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzcbh[i];
    }
}
