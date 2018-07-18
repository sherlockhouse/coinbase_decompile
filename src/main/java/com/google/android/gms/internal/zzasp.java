package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.auth.api.Auth.AuthCredentialsOptions;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzq;

public final class zzasp extends zzaa<zzasu> {
    private final AuthCredentialsOptions zzebl;

    public zzasp(Context context, Looper looper, zzq com_google_android_gms_common_internal_zzq, AuthCredentialsOptions authCredentialsOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 68, com_google_android_gms_common_internal_zzq, connectionCallbacks, onConnectionFailedListener);
        this.zzebl = authCredentialsOptions;
    }

    protected final /* synthetic */ IInterface zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
        return queryLocalInterface instanceof zzasu ? (zzasu) queryLocalInterface : new zzasv(iBinder);
    }

    protected final String zzhc() {
        return "com.google.android.gms.auth.api.credentials.service.START";
    }

    protected final String zzhd() {
        return "com.google.android.gms.auth.api.credentials.internal.ICredentialsService";
    }

    protected final Bundle zzzu() {
        return this.zzebl == null ? new Bundle() : this.zzebl.zzzu();
    }
}
