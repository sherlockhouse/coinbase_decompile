package com.google.android.gms.maps;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import com.google.android.gms.R;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import com.google.android.gms.maps.internal.zza;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLngBounds;

public final class GoogleMapOptions extends zzbck implements ReflectedParcelable {
    public static final Creator<GoogleMapOptions> CREATOR = new zzaa();
    private Boolean zzigd;
    private Boolean zzige;
    private int zzigf = -1;
    private CameraPosition zzigg;
    private Boolean zzigh;
    private Boolean zzigi;
    private Boolean zzigj;
    private Boolean zzigk;
    private Boolean zzigl;
    private Boolean zzigm;
    private Boolean zzign;
    private Boolean zzigo;
    private Boolean zzigp;
    private Float zzigq = null;
    private Float zzigr = null;
    private LatLngBounds zzigs = null;

    GoogleMapOptions(byte b, byte b2, int i, CameraPosition cameraPosition, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, byte b9, byte b10, byte b11, Float f, Float f2, LatLngBounds latLngBounds) {
        this.zzigd = zza.zza(b);
        this.zzige = zza.zza(b2);
        this.zzigf = i;
        this.zzigg = cameraPosition;
        this.zzigh = zza.zza(b3);
        this.zzigi = zza.zza(b4);
        this.zzigj = zza.zza(b5);
        this.zzigk = zza.zza(b6);
        this.zzigl = zza.zza(b7);
        this.zzigm = zza.zza(b8);
        this.zzign = zza.zza(b9);
        this.zzigo = zza.zza(b10);
        this.zzigp = zza.zza(b11);
        this.zzigq = f;
        this.zzigr = f2;
        this.zzigs = latLngBounds;
    }

    public static GoogleMapOptions createFromAttributes(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, R.styleable.MapAttrs);
        GoogleMapOptions googleMapOptions = new GoogleMapOptions();
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_mapType)) {
            googleMapOptions.mapType(obtainAttributes.getInt(R.styleable.MapAttrs_mapType, -1));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_zOrderOnTop)) {
            googleMapOptions.zOrderOnTop(obtainAttributes.getBoolean(R.styleable.MapAttrs_zOrderOnTop, false));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_useViewLifecycle)) {
            googleMapOptions.useViewLifecycleInFragment(obtainAttributes.getBoolean(R.styleable.MapAttrs_useViewLifecycle, false));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_uiCompass)) {
            googleMapOptions.compassEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_uiCompass, true));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_uiRotateGestures)) {
            googleMapOptions.rotateGesturesEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_uiRotateGestures, true));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_uiScrollGestures)) {
            googleMapOptions.scrollGesturesEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_uiScrollGestures, true));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_uiTiltGestures)) {
            googleMapOptions.tiltGesturesEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_uiTiltGestures, true));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_uiZoomGestures)) {
            googleMapOptions.zoomGesturesEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_uiZoomGestures, true));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_uiZoomControls)) {
            googleMapOptions.zoomControlsEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_uiZoomControls, true));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_liteMode)) {
            googleMapOptions.liteMode(obtainAttributes.getBoolean(R.styleable.MapAttrs_liteMode, false));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_uiMapToolbar)) {
            googleMapOptions.mapToolbarEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_uiMapToolbar, true));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_ambientEnabled)) {
            googleMapOptions.ambientEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_ambientEnabled, false));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_cameraMinZoomPreference)) {
            googleMapOptions.minZoomPreference(obtainAttributes.getFloat(R.styleable.MapAttrs_cameraMinZoomPreference, Float.NEGATIVE_INFINITY));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_cameraMinZoomPreference)) {
            googleMapOptions.maxZoomPreference(obtainAttributes.getFloat(R.styleable.MapAttrs_cameraMaxZoomPreference, Float.POSITIVE_INFINITY));
        }
        googleMapOptions.latLngBoundsForCameraTarget(LatLngBounds.createFromAttributes(context, attributeSet));
        googleMapOptions.camera(CameraPosition.createFromAttributes(context, attributeSet));
        obtainAttributes.recycle();
        return googleMapOptions;
    }

    public final GoogleMapOptions ambientEnabled(boolean z) {
        this.zzigp = Boolean.valueOf(z);
        return this;
    }

    public final GoogleMapOptions camera(CameraPosition cameraPosition) {
        this.zzigg = cameraPosition;
        return this;
    }

    public final GoogleMapOptions compassEnabled(boolean z) {
        this.zzigi = Boolean.valueOf(z);
        return this;
    }

    public final CameraPosition getCamera() {
        return this.zzigg;
    }

    public final LatLngBounds getLatLngBoundsForCameraTarget() {
        return this.zzigs;
    }

    public final int getMapType() {
        return this.zzigf;
    }

    public final Float getMaxZoomPreference() {
        return this.zzigr;
    }

    public final Float getMinZoomPreference() {
        return this.zzigq;
    }

    public final GoogleMapOptions latLngBoundsForCameraTarget(LatLngBounds latLngBounds) {
        this.zzigs = latLngBounds;
        return this;
    }

    public final GoogleMapOptions liteMode(boolean z) {
        this.zzign = Boolean.valueOf(z);
        return this;
    }

    public final GoogleMapOptions mapToolbarEnabled(boolean z) {
        this.zzigo = Boolean.valueOf(z);
        return this;
    }

    public final GoogleMapOptions mapType(int i) {
        this.zzigf = i;
        return this;
    }

    public final GoogleMapOptions maxZoomPreference(float f) {
        this.zzigr = Float.valueOf(f);
        return this;
    }

    public final GoogleMapOptions minZoomPreference(float f) {
        this.zzigq = Float.valueOf(f);
        return this;
    }

    public final GoogleMapOptions rotateGesturesEnabled(boolean z) {
        this.zzigm = Boolean.valueOf(z);
        return this;
    }

    public final GoogleMapOptions scrollGesturesEnabled(boolean z) {
        this.zzigj = Boolean.valueOf(z);
        return this;
    }

    public final GoogleMapOptions tiltGesturesEnabled(boolean z) {
        this.zzigl = Boolean.valueOf(z);
        return this;
    }

    public final String toString() {
        return zzbf.zzt(this).zzg("MapType", Integer.valueOf(this.zzigf)).zzg("LiteMode", this.zzign).zzg("Camera", this.zzigg).zzg("CompassEnabled", this.zzigi).zzg("ZoomControlsEnabled", this.zzigh).zzg("ScrollGesturesEnabled", this.zzigj).zzg("ZoomGesturesEnabled", this.zzigk).zzg("TiltGesturesEnabled", this.zzigl).zzg("RotateGesturesEnabled", this.zzigm).zzg("MapToolbarEnabled", this.zzigo).zzg("AmbientEnabled", this.zzigp).zzg("MinZoomPreference", this.zzigq).zzg("MaxZoomPreference", this.zzigr).zzg("LatLngBoundsForCameraTarget", this.zzigs).zzg("ZOrderOnTop", this.zzigd).zzg("UseViewLifecycleInFragment", this.zzige).toString();
    }

    public final GoogleMapOptions useViewLifecycleInFragment(boolean z) {
        this.zzige = Boolean.valueOf(z);
        return this;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zza(parcel, 2, zza.zzb(this.zzigd));
        zzbcn.zza(parcel, 3, zza.zzb(this.zzige));
        zzbcn.zzc(parcel, 4, getMapType());
        zzbcn.zza(parcel, 5, getCamera(), i, false);
        zzbcn.zza(parcel, 6, zza.zzb(this.zzigh));
        zzbcn.zza(parcel, 7, zza.zzb(this.zzigi));
        zzbcn.zza(parcel, 8, zza.zzb(this.zzigj));
        zzbcn.zza(parcel, 9, zza.zzb(this.zzigk));
        zzbcn.zza(parcel, 10, zza.zzb(this.zzigl));
        zzbcn.zza(parcel, 11, zza.zzb(this.zzigm));
        zzbcn.zza(parcel, 12, zza.zzb(this.zzign));
        zzbcn.zza(parcel, 14, zza.zzb(this.zzigo));
        zzbcn.zza(parcel, 15, zza.zzb(this.zzigp));
        zzbcn.zza(parcel, 16, getMinZoomPreference(), false);
        zzbcn.zza(parcel, 17, getMaxZoomPreference(), false);
        zzbcn.zza(parcel, 18, getLatLngBoundsForCameraTarget(), i, false);
        zzbcn.zzai(parcel, zze);
    }

    public final GoogleMapOptions zOrderOnTop(boolean z) {
        this.zzigd = Boolean.valueOf(z);
        return this;
    }

    public final GoogleMapOptions zoomControlsEnabled(boolean z) {
        this.zzigh = Boolean.valueOf(z);
        return this;
    }

    public final GoogleMapOptions zoomGesturesEnabled(boolean z) {
        this.zzigk = Boolean.valueOf(z);
        return this;
    }
}
