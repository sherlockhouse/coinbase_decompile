package com.google.android.gms.location.places.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbcl;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.List;

public final class zzae implements Creator<PlaceEntity> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzbcl.zzd(parcel);
        String str = null;
        List list = null;
        List list2 = null;
        Bundle bundle = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        List list3 = null;
        LatLng latLng = null;
        float f = 0.0f;
        LatLngBounds latLngBounds = null;
        String str6 = null;
        Uri uri = null;
        boolean z = false;
        float f2 = 0.0f;
        int i = 0;
        zzak com_google_android_gms_location_places_internal_zzak = null;
        zzam com_google_android_gms_location_places_internal_zzam = null;
        zzaf com_google_android_gms_location_places_internal_zzaf = null;
        String str7 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    str = zzbcl.zzq(parcel, readInt);
                    break;
                case 2:
                    bundle = zzbcl.zzs(parcel, readInt);
                    break;
                case 3:
                    com_google_android_gms_location_places_internal_zzak = (zzak) zzbcl.zza(parcel, readInt, zzak.CREATOR);
                    break;
                case 4:
                    latLng = (LatLng) zzbcl.zza(parcel, readInt, LatLng.CREATOR);
                    break;
                case 5:
                    f = zzbcl.zzl(parcel, readInt);
                    break;
                case 6:
                    latLngBounds = (LatLngBounds) zzbcl.zza(parcel, readInt, LatLngBounds.CREATOR);
                    break;
                case 7:
                    str6 = zzbcl.zzq(parcel, readInt);
                    break;
                case 8:
                    uri = (Uri) zzbcl.zza(parcel, readInt, Uri.CREATOR);
                    break;
                case 9:
                    z = zzbcl.zzc(parcel, readInt);
                    break;
                case 10:
                    f2 = zzbcl.zzl(parcel, readInt);
                    break;
                case 11:
                    i = zzbcl.zzg(parcel, readInt);
                    break;
                case 13:
                    list2 = zzbcl.zzab(parcel, readInt);
                    break;
                case 14:
                    str3 = zzbcl.zzq(parcel, readInt);
                    break;
                case 15:
                    str4 = zzbcl.zzq(parcel, readInt);
                    break;
                case 16:
                    str5 = zzbcl.zzq(parcel, readInt);
                    break;
                case 17:
                    list3 = zzbcl.zzac(parcel, readInt);
                    break;
                case 19:
                    str2 = zzbcl.zzq(parcel, readInt);
                    break;
                case 20:
                    list = zzbcl.zzab(parcel, readInt);
                    break;
                case 21:
                    com_google_android_gms_location_places_internal_zzam = (zzam) zzbcl.zza(parcel, readInt, zzam.CREATOR);
                    break;
                case 22:
                    com_google_android_gms_location_places_internal_zzaf = (zzaf) zzbcl.zza(parcel, readInt, zzaf.CREATOR);
                    break;
                case 23:
                    str7 = zzbcl.zzq(parcel, readInt);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new PlaceEntity(str, list, list2, bundle, str2, str3, str4, str5, list3, latLng, f, latLngBounds, str6, uri, z, f2, i, com_google_android_gms_location_places_internal_zzak, com_google_android_gms_location_places_internal_zzam, com_google_android_gms_location_places_internal_zzaf, str7);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new PlaceEntity[i];
    }
}
