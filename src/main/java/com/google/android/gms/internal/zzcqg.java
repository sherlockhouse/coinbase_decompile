package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzbs;

public final class zzcqg implements Creator<zzcqf> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzbcl.zzd(parcel);
        ConnectionResult connectionResult = null;
        int i = 0;
        zzbs com_google_android_gms_common_internal_zzbs = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = zzbcl.zzg(parcel, readInt);
                    break;
                case 2:
                    connectionResult = (ConnectionResult) zzbcl.zza(parcel, readInt, ConnectionResult.CREATOR);
                    break;
                case 3:
                    com_google_android_gms_common_internal_zzbs = (zzbs) zzbcl.zza(parcel, readInt, zzbs.CREATOR);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new zzcqf(i, connectionResult, com_google_android_gms_common_internal_zzbs);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzcqf[i];
    }
}
