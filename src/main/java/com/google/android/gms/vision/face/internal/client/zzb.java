package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbcl;

public final class zzb implements Creator<FaceParcel> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzbcl.zzd(parcel);
        int i = 0;
        int i2 = 0;
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        LandmarkParcel[] landmarkParcelArr = null;
        float f7 = 0.0f;
        float f8 = 0.0f;
        float f9 = 0.0f;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = zzbcl.zzg(parcel, readInt);
                    break;
                case 2:
                    i2 = zzbcl.zzg(parcel, readInt);
                    break;
                case 3:
                    f = zzbcl.zzl(parcel, readInt);
                    break;
                case 4:
                    f2 = zzbcl.zzl(parcel, readInt);
                    break;
                case 5:
                    f3 = zzbcl.zzl(parcel, readInt);
                    break;
                case 6:
                    f4 = zzbcl.zzl(parcel, readInt);
                    break;
                case 7:
                    f5 = zzbcl.zzl(parcel, readInt);
                    break;
                case 8:
                    f6 = zzbcl.zzl(parcel, readInt);
                    break;
                case 9:
                    landmarkParcelArr = (LandmarkParcel[]) zzbcl.zzb(parcel, readInt, LandmarkParcel.CREATOR);
                    break;
                case 10:
                    f7 = zzbcl.zzl(parcel, readInt);
                    break;
                case 11:
                    f8 = zzbcl.zzl(parcel, readInt);
                    break;
                case 12:
                    f9 = zzbcl.zzl(parcel, readInt);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new FaceParcel(i, i2, f, f2, f3, f4, f5, f6, landmarkParcelArr, f7, f8, f9);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new FaceParcel[i];
    }
}
