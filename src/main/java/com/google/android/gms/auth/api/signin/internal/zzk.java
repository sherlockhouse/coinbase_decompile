package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

final class zzk extends zza {
    private /* synthetic */ zzj zzecy;

    zzk(zzj com_google_android_gms_auth_api_signin_internal_zzj) {
        this.zzecy = com_google_android_gms_auth_api_signin_internal_zzj;
    }

    public final void zzj(Status status) throws RemoteException {
        this.zzecy.setResult(status);
    }
}
