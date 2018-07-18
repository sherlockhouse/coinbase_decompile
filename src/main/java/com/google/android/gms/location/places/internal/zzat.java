package com.google.android.gms.location.places.internal;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Arrays;
import java.util.Locale;

public final class zzat extends zzbck {
    public static final Creator<zzat> CREATOR = new zzau();
    private static zzat zzieg = new zzat("com.google.android.gms", Locale.getDefault(), null);
    private String zzgcg;
    private String zzibu;
    private String zzieh;
    private String zziei;
    private int zziej;
    private int zziek;

    public zzat(String str, String str2, String str3, String str4, int i, int i2) {
        this.zzieh = str;
        this.zziei = str2;
        this.zzgcg = str3;
        this.zzibu = str4;
        this.zziej = i;
        this.zziek = i2;
    }

    private zzat(String str, Locale locale, String str2) {
        this(str, locale.toString(), null, null, GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE, 0);
    }

    public zzat(String str, Locale locale, String str2, String str3, int i) {
        this(str, locale.toString(), str2, str3, GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE, i);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzat)) {
            return false;
        }
        zzat com_google_android_gms_location_places_internal_zzat = (zzat) obj;
        return this.zziej == com_google_android_gms_location_places_internal_zzat.zziej && this.zziek == com_google_android_gms_location_places_internal_zzat.zziek && this.zziei.equals(com_google_android_gms_location_places_internal_zzat.zziei) && this.zzieh.equals(com_google_android_gms_location_places_internal_zzat.zzieh) && zzbf.equal(this.zzgcg, com_google_android_gms_location_places_internal_zzat.zzgcg) && zzbf.equal(this.zzibu, com_google_android_gms_location_places_internal_zzat.zzibu);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzieh, this.zziei, this.zzgcg, this.zzibu, Integer.valueOf(this.zziej), Integer.valueOf(this.zziek)});
    }

    @SuppressLint({"DefaultLocale"})
    public final String toString() {
        return zzbf.zzt(this).zzg("clientPackageName", this.zzieh).zzg("locale", this.zziei).zzg("accountName", this.zzgcg).zzg("gCoreClientName", this.zzibu).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zza(parcel, 1, this.zzieh, false);
        zzbcn.zza(parcel, 2, this.zziei, false);
        zzbcn.zza(parcel, 3, this.zzgcg, false);
        zzbcn.zza(parcel, 4, this.zzibu, false);
        zzbcn.zzc(parcel, 6, this.zziej);
        zzbcn.zzc(parcel, 7, this.zziek);
        zzbcn.zzai(parcel, zze);
    }
}
