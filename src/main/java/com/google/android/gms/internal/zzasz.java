package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.auth.api.zzd;
import com.google.android.gms.auth.api.zzf;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzq;

public final class zzasz extends zzaa<zzatc> {
    private final Bundle zzdzx;

    public zzasz(Context context, Looper looper, zzq com_google_android_gms_common_internal_zzq, zzf com_google_android_gms_auth_api_zzf, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 16, com_google_android_gms_common_internal_zzq, connectionCallbacks, onConnectionFailedListener);
        if (com_google_android_gms_auth_api_zzf == null) {
            this.zzdzx = new Bundle();
            return;
        }
        throw new NoSuchMethodError();
    }

    public final boolean zzaac() {
        zzq zzakd = zzakd();
        return (TextUtils.isEmpty(zzakd.getAccountName()) || zzakd.zzc(zzd.API).isEmpty()) ? false : true;
    }

    protected final /* synthetic */ IInterface zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.auth.api.internal.IAuthService");
        return queryLocalInterface instanceof zzatc ? (zzatc) queryLocalInterface : new zzatd(iBinder);
    }

    protected final String zzhc() {
        return "com.google.android.gms.auth.service.START";
    }

    protected final String zzhd() {
        return "com.google.android.gms.auth.api.internal.IAuthService";
    }

    protected final Bundle zzzu() {
        return this.zzdzx;
    }
}
