package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import com.google.android.gms.maps.internal.zza;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

public final class StreetViewPanoramaOptions extends zzbck implements ReflectedParcelable {
    public static final Creator<StreetViewPanoramaOptions> CREATOR = new zzai();
    private Boolean zzige;
    private Boolean zzigk = Boolean.valueOf(true);
    private StreetViewPanoramaCamera zzihp;
    private String zzihq;
    private LatLng zzihr;
    private Integer zzihs;
    private Boolean zziht = Boolean.valueOf(true);
    private Boolean zzihu = Boolean.valueOf(true);
    private Boolean zzihv = Boolean.valueOf(true);

    StreetViewPanoramaOptions(StreetViewPanoramaCamera streetViewPanoramaCamera, String str, LatLng latLng, Integer num, byte b, byte b2, byte b3, byte b4, byte b5) {
        this.zzihp = streetViewPanoramaCamera;
        this.zzihr = latLng;
        this.zzihs = num;
        this.zzihq = str;
        this.zziht = zza.zza(b);
        this.zzigk = zza.zza(b2);
        this.zzihu = zza.zza(b3);
        this.zzihv = zza.zza(b4);
        this.zzige = zza.zza(b5);
    }

    public final String getPanoramaId() {
        return this.zzihq;
    }

    public final LatLng getPosition() {
        return this.zzihr;
    }

    public final Integer getRadius() {
        return this.zzihs;
    }

    public final StreetViewPanoramaCamera getStreetViewPanoramaCamera() {
        return this.zzihp;
    }

    public final String toString() {
        return zzbf.zzt(this).zzg("PanoramaId", this.zzihq).zzg("Position", this.zzihr).zzg("Radius", this.zzihs).zzg("StreetViewPanoramaCamera", this.zzihp).zzg("UserNavigationEnabled", this.zziht).zzg("ZoomGesturesEnabled", this.zzigk).zzg("PanningGesturesEnabled", this.zzihu).zzg("StreetNamesEnabled", this.zzihv).zzg("UseViewLifecycleInFragment", this.zzige).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zza(parcel, 2, getStreetViewPanoramaCamera(), i, false);
        zzbcn.zza(parcel, 3, getPanoramaId(), false);
        zzbcn.zza(parcel, 4, getPosition(), i, false);
        zzbcn.zza(parcel, 5, getRadius(), false);
        zzbcn.zza(parcel, 6, zza.zzb(this.zziht));
        zzbcn.zza(parcel, 7, zza.zzb(this.zzigk));
        zzbcn.zza(parcel, 8, zza.zzb(this.zzihu));
        zzbcn.zza(parcel, 9, zza.zzb(this.zzihv));
        zzbcn.zza(parcel, 10, zza.zzb(this.zzige));
        zzbcn.zzai(parcel, zze);
    }
}
