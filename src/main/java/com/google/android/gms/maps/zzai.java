package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbcl;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

public final class zzai implements Creator<StreetViewPanoramaOptions> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Integer num = null;
        byte b = (byte) 0;
        int zzd = zzbcl.zzd(parcel);
        byte b2 = (byte) 0;
        byte b3 = (byte) 0;
        byte b4 = (byte) 0;
        byte b5 = (byte) 0;
        LatLng latLng = null;
        String str = null;
        StreetViewPanoramaCamera streetViewPanoramaCamera = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    streetViewPanoramaCamera = (StreetViewPanoramaCamera) zzbcl.zza(parcel, readInt, StreetViewPanoramaCamera.CREATOR);
                    break;
                case 3:
                    str = zzbcl.zzq(parcel, readInt);
                    break;
                case 4:
                    latLng = (LatLng) zzbcl.zza(parcel, readInt, LatLng.CREATOR);
                    break;
                case 5:
                    num = zzbcl.zzh(parcel, readInt);
                    break;
                case 6:
                    b5 = zzbcl.zze(parcel, readInt);
                    break;
                case 7:
                    b4 = zzbcl.zze(parcel, readInt);
                    break;
                case 8:
                    b3 = zzbcl.zze(parcel, readInt);
                    break;
                case 9:
                    b2 = zzbcl.zze(parcel, readInt);
                    break;
                case 10:
                    b = zzbcl.zze(parcel, readInt);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new StreetViewPanoramaOptions(streetViewPanoramaCamera, str, latLng, num, b5, b4, b3, b2, b);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new StreetViewPanoramaOptions[i];
    }
}
