package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbcl;

public final class zzm implements Creator<StreetViewPanoramaCamera> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        float f = 0.0f;
        int zzd = zzbcl.zzd(parcel);
        float f2 = 0.0f;
        float f3 = 0.0f;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    f3 = zzbcl.zzl(parcel, readInt);
                    break;
                case 3:
                    f2 = zzbcl.zzl(parcel, readInt);
                    break;
                case 4:
                    f = zzbcl.zzl(parcel, readInt);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new StreetViewPanoramaCamera(f3, f2, f);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new StreetViewPanoramaCamera[i];
    }
}
