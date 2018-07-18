package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.os.Binder;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.zzo;

public final class zzv extends zzq {
    private final Context mContext;

    public zzv(Context context) {
        this.mContext = context;
    }

    public final void zzaap() {
        if (zzo.zzf(this.mContext, Binder.getCallingUid())) {
            zzy zzbl = zzy.zzbl(this.mContext);
            GoogleSignInAccount zzaas = zzbl.zzaas();
            HasOptions hasOptions = GoogleSignInOptions.DEFAULT_SIGN_IN;
            if (zzaas != null) {
                hasOptions = zzbl.zzaat();
            }
            GoogleApiClient build = new Builder(this.mContext).addApi(Auth.GOOGLE_SIGN_IN_API, hasOptions).build();
            try {
                if (build.blockingConnect().isSuccess()) {
                    if (zzaas != null) {
                        Auth.GoogleSignInApi.revokeAccess(build);
                    } else {
                        build.clearDefaultAccountAndReconnect();
                    }
                }
                build.disconnect();
            } catch (Throwable th) {
                build.disconnect();
            }
        } else {
            throw new SecurityException("Calling UID " + Binder.getCallingUid() + " is not Google Play services.");
        }
    }
}
