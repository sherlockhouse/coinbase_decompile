package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbcl;

public final class zzi implements Creator<LandmarkParcel> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        float f = 0.0f;
        int zzd = zzbcl.zzd(parcel);
        float f2 = 0.0f;
        int i2 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i2 = zzbcl.zzg(parcel, readInt);
                    break;
                case 2:
                    f2 = zzbcl.zzl(parcel, readInt);
                    break;
                case 3:
                    f = zzbcl.zzl(parcel, readInt);
                    break;
                case 4:
                    i = zzbcl.zzg(parcel, readInt);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new LandmarkParcel(i2, f2, f, i);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new LandmarkParcel[i];
    }
}
