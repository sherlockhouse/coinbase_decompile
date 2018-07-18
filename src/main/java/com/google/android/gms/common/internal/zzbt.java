package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.internal.zzbcl;

public final class zzbt implements Creator<zzbs> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        ConnectionResult connectionResult = null;
        boolean z = false;
        int zzd = zzbcl.zzd(parcel);
        boolean z2 = false;
        IBinder iBinder = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = zzbcl.zzg(parcel, readInt);
                    break;
                case 2:
                    iBinder = zzbcl.zzr(parcel, readInt);
                    break;
                case 3:
                    connectionResult = (ConnectionResult) zzbcl.zza(parcel, readInt, ConnectionResult.CREATOR);
                    break;
                case 4:
                    z2 = zzbcl.zzc(parcel, readInt);
                    break;
                case 5:
                    z = zzbcl.zzc(parcel, readInt);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new zzbs(i, iBinder, connectionResult, z2, z);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzbs[i];
    }
}
