package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzq;

public final class zzbcz extends zzaa<zzbdc> {
    public zzbcz(Context context, Looper looper, zzq com_google_android_gms_common_internal_zzq, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 39, com_google_android_gms_common_internal_zzq, connectionCallbacks, onConnectionFailedListener);
    }

    protected final /* synthetic */ IInterface zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.service.ICommonService");
        return queryLocalInterface instanceof zzbdc ? (zzbdc) queryLocalInterface : new zzbdd(iBinder);
    }

    public final String zzhc() {
        return "com.google.android.gms.common.service.START";
    }

    protected final String zzhd() {
        return "com.google.android.gms.common.internal.service.ICommonService";
    }
}
