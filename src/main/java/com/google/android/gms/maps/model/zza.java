package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbcl;

public final class zza implements Creator<CameraPosition> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzbcl.zzd(parcel);
        float f = 0.0f;
        float f2 = 0.0f;
        LatLng latLng = null;
        float f3 = 0.0f;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    latLng = (LatLng) zzbcl.zza(parcel, readInt, LatLng.CREATOR);
                    break;
                case 3:
                    f2 = zzbcl.zzl(parcel, readInt);
                    break;
                case 4:
                    f = zzbcl.zzl(parcel, readInt);
                    break;
                case 5:
                    f3 = zzbcl.zzl(parcel, readInt);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new CameraPosition(latLng, f2, f, f3);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new CameraPosition[i];
    }
}
