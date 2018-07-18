package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzq;

public final class zzarl extends zzaa<zzarm> {
    public zzarl(Context context, Looper looper, zzq com_google_android_gms_common_internal_zzq, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 74, com_google_android_gms_common_internal_zzq, connectionCallbacks, onConnectionFailedListener);
    }

    protected final /* synthetic */ IInterface zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.auth.api.accountactivationstate.internal.IAccountActivationStateService");
        return queryLocalInterface instanceof zzarm ? (zzarm) queryLocalInterface : new zzarn(iBinder);
    }

    protected final String zzhc() {
        return "com.google.android.gms.auth.api.accountactivationstate.START";
    }

    protected final String zzhd() {
        return "com.google.android.gms.auth.api.accountactivationstate.internal.IAccountActivationStateService";
    }
}
