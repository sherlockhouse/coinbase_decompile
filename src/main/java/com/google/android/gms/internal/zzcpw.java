package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzcpw implements Creator<zzcpv> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        int zzd = zzbcl.zzd(parcel);
        Intent intent = null;
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
                    intent = (Intent) zzbcl.zza(parcel, readInt, Intent.CREATOR);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new zzcpv(i2, i, intent);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzcpv[i];
    }
}
