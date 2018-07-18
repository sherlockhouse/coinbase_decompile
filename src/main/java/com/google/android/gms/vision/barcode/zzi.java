package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbcl;
import com.google.android.gms.vision.barcode.Barcode.GeoPoint;

public final class zzi implements Creator<GeoPoint> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        double d = 0.0d;
        int zzd = zzbcl.zzd(parcel);
        double d2 = 0.0d;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    d2 = zzbcl.zzn(parcel, readInt);
                    break;
                case 3:
                    d = zzbcl.zzn(parcel, readInt);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new GeoPoint(d2, d);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new GeoPoint[i];
    }
}
