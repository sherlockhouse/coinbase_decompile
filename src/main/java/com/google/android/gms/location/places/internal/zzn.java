package com.google.android.gms.location.places.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.location.places.PlacesOptions;
import java.util.Locale;

public final class zzn extends zzaa<zzs> {
    private final zzat zzict;

    private zzn(Context context, Looper looper, zzq com_google_android_gms_common_internal_zzq, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String str, PlacesOptions placesOptions) {
        super(context, looper, 65, com_google_android_gms_common_internal_zzq, connectionCallbacks, onConnectionFailedListener);
        this.zzict = new zzat(str, Locale.getDefault(), com_google_android_gms_common_internal_zzq.getAccount() != null ? com_google_android_gms_common_internal_zzq.getAccount().name : null, null, 0);
    }

    protected final /* synthetic */ IInterface zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        return queryLocalInterface instanceof zzs ? (zzs) queryLocalInterface : new zzt(iBinder);
    }

    protected final String zzhc() {
        return "com.google.android.gms.location.places.GeoDataApi";
    }

    protected final String zzhd() {
        return "com.google.android.gms.location.places.internal.IGooglePlacesService";
    }
}
