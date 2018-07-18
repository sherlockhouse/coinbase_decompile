package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzq;

public final class zzd extends zzaa<zzt> {
    private final GoogleSignInOptions zzecs;

    public zzd(Context context, Looper looper, zzq com_google_android_gms_common_internal_zzq, GoogleSignInOptions googleSignInOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 91, com_google_android_gms_common_internal_zzq, connectionCallbacks, onConnectionFailedListener);
        if (googleSignInOptions == null) {
            googleSignInOptions = new Builder().build();
        }
        if (!com_google_android_gms_common_internal_zzq.zzajs().isEmpty()) {
            Builder builder = new Builder(googleSignInOptions);
            for (Scope requestScopes : com_google_android_gms_common_internal_zzq.zzajs()) {
                builder.requestScopes(requestScopes, new Scope[0]);
            }
            googleSignInOptions = builder.build();
        }
        this.zzecs = googleSignInOptions;
    }

    public final boolean zzaal() {
        return true;
    }

    public final Intent zzaam() {
        return zze.zza(getContext(), this.zzecs);
    }

    public final GoogleSignInOptions zzaan() {
        return this.zzecs;
    }

    protected final /* synthetic */ IInterface zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.auth.api.signin.internal.ISignInService");
        return queryLocalInterface instanceof zzt ? (zzt) queryLocalInterface : new zzu(iBinder);
    }

    protected final String zzhc() {
        return "com.google.android.gms.auth.api.signin.service.START";
    }

    protected final String zzhd() {
        return "com.google.android.gms.auth.api.signin.internal.ISignInService";
    }
}
