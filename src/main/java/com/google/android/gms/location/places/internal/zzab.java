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

public final class zzab extends zzaa<zzq> {
    private final Locale zzibw;
    private final zzat zzict;

    private zzab(Context context, Looper looper, zzq com_google_android_gms_common_internal_zzq, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String str, PlacesOptions placesOptions) {
        super(context, looper, 67, com_google_android_gms_common_internal_zzq, connectionCallbacks, onConnectionFailedListener);
        this.zzibw = Locale.getDefault();
        this.zzict = new zzat(str, this.zzibw, com_google_android_gms_common_internal_zzq.getAccount() != null ? com_google_android_gms_common_internal_zzq.getAccount().name : null, null, 0);
    }

    protected final /* synthetic */ IInterface zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
        return queryLocalInterface instanceof zzq ? (zzq) queryLocalInterface : new zzr(iBinder);
    }

    protected final String zzhc() {
        return "com.google.android.gms.location.places.PlaceDetectionApi";
    }

    protected final String zzhd() {
        return "com.google.android.gms.location.places.internal.IGooglePlaceDetectionService";
    }
}
