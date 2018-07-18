package com.google.android.gms.location.places.internal;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public final class PlaceEntity extends zzbck implements ReflectedParcelable, Place {
    public static final Creator<PlaceEntity> CREATOR = new zzae();
    private final String mName;
    private final String zzbsx;
    private final String zzgsd;
    private final LatLng zziai;
    private final List<Integer> zziaj;
    private final String zziak;
    private final Uri zzial;
    private Locale zzibw;
    private final Bundle zzicw;
    @Deprecated
    private final zzak zzicx;
    private final float zzicy;
    private final LatLngBounds zzicz;
    private final String zzida;
    private final boolean zzidb;
    private final float zzidc;
    private final int zzidd;
    private final List<Integer> zzide;
    private final String zzidf;
    private final List<String> zzidg;
    private final zzam zzidh;
    private final zzaf zzidi;
    private final String zzidj;
    private final Map<Integer, String> zzidk;
    private final TimeZone zzidl;

    PlaceEntity(String str, List<Integer> list, List<Integer> list2, Bundle bundle, String str2, String str3, String str4, String str5, List<String> list3, LatLng latLng, float f, LatLngBounds latLngBounds, String str6, Uri uri, boolean z, float f2, int i, zzak com_google_android_gms_location_places_internal_zzak, zzam com_google_android_gms_location_places_internal_zzam, zzaf com_google_android_gms_location_places_internal_zzaf, String str7) {
        List emptyList;
        this.zzbsx = str;
        this.zziaj = Collections.unmodifiableList(list);
        this.zzide = list2;
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.zzicw = bundle;
        this.mName = str2;
        this.zzgsd = str3;
        this.zziak = str4;
        this.zzidf = str5;
        if (list3 == null) {
            emptyList = Collections.emptyList();
        }
        this.zzidg = emptyList;
        this.zziai = latLng;
        this.zzicy = f;
        this.zzicz = latLngBounds;
        if (str6 == null) {
            str6 = "UTC";
        }
        this.zzida = str6;
        this.zzial = uri;
        this.zzidb = z;
        this.zzidc = f2;
        this.zzidd = i;
        this.zzidk = Collections.unmodifiableMap(new HashMap());
        this.zzidl = null;
        this.zzibw = null;
        this.zzicx = com_google_android_gms_location_places_internal_zzak;
        this.zzidh = com_google_android_gms_location_places_internal_zzam;
        this.zzidi = com_google_android_gms_location_places_internal_zzaf;
        this.zzidj = str7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaceEntity)) {
            return false;
        }
        PlaceEntity placeEntity = (PlaceEntity) obj;
        return this.zzbsx.equals(placeEntity.zzbsx) && zzbf.equal(this.zzibw, placeEntity.zzibw);
    }

    public final /* synthetic */ CharSequence getAddress() {
        return this.zzgsd;
    }

    public final String getId() {
        return this.zzbsx;
    }

    public final LatLng getLatLng() {
        return this.zziai;
    }

    public final /* synthetic */ CharSequence getName() {
        return this.mName;
    }

    public final /* synthetic */ CharSequence getPhoneNumber() {
        return this.zziak;
    }

    public final List<Integer> getPlaceTypes() {
        return this.zziaj;
    }

    public final int getPriceLevel() {
        return this.zzidd;
    }

    public final float getRating() {
        return this.zzidc;
    }

    public final LatLngBounds getViewport() {
        return this.zzicz;
    }

    public final Uri getWebsiteUri() {
        return this.zzial;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzbsx, this.zzibw});
    }

    @SuppressLint({"DefaultLocale"})
    public final String toString() {
        return zzbf.zzt(this).zzg("id", this.zzbsx).zzg("placeTypes", this.zziaj).zzg("locale", this.zzibw).zzg("name", this.mName).zzg("address", this.zzgsd).zzg("phoneNumber", this.zziak).zzg("latlng", this.zziai).zzg("viewport", this.zzicz).zzg("websiteUri", this.zzial).zzg("isPermanentlyClosed", Boolean.valueOf(this.zzidb)).zzg("priceLevel", Integer.valueOf(this.zzidd)).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zza(parcel, 1, getId(), false);
        zzbcn.zza(parcel, 2, this.zzicw, false);
        zzbcn.zza(parcel, 3, this.zzicx, i, false);
        zzbcn.zza(parcel, 4, getLatLng(), i, false);
        zzbcn.zza(parcel, 5, this.zzicy);
        zzbcn.zza(parcel, 6, getViewport(), i, false);
        zzbcn.zza(parcel, 7, this.zzida, false);
        zzbcn.zza(parcel, 8, getWebsiteUri(), i, false);
        zzbcn.zza(parcel, 9, this.zzidb);
        zzbcn.zza(parcel, 10, getRating());
        zzbcn.zzc(parcel, 11, getPriceLevel());
        zzbcn.zza(parcel, 13, this.zzide, false);
        zzbcn.zza(parcel, 14, (String) getAddress(), false);
        zzbcn.zza(parcel, 15, (String) getPhoneNumber(), false);
        zzbcn.zza(parcel, 16, this.zzidf, false);
        zzbcn.zzb(parcel, 17, this.zzidg, false);
        zzbcn.zza(parcel, 19, (String) getName(), false);
        zzbcn.zza(parcel, 20, getPlaceTypes(), false);
        zzbcn.zza(parcel, 21, this.zzidh, i, false);
        zzbcn.zza(parcel, 22, this.zzidi, i, false);
        zzbcn.zza(parcel, 23, this.zzidj, false);
        zzbcn.zzai(parcel, zze);
    }
}
