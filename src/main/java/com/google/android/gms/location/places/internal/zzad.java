package com.google.android.gms.location.places.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.location.places.PlacesOptions;
import com.google.android.gms.location.places.PlacesOptions.Builder;

public final class zzad extends zza<zzab, PlacesOptions> {
    public final /* synthetic */ zze zza(Context context, Looper looper, zzq com_google_android_gms_common_internal_zzq, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        PlacesOptions placesOptions = (PlacesOptions) obj;
        return new zzab(context, looper, com_google_android_gms_common_internal_zzq, connectionCallbacks, onConnectionFailedListener, context.getPackageName(), placesOptions == null ? new Builder().build() : placesOptions);
    }
}
