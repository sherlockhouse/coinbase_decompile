package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;

final class zzbcw extends zzbcq {
    private final zzn<Status> zzfwi;

    public zzbcw(zzn<Status> com_google_android_gms_common_api_internal_zzn_com_google_android_gms_common_api_Status) {
        this.zzfwi = com_google_android_gms_common_api_internal_zzn_com_google_android_gms_common_api_Status;
    }

    public final void zzcg(int i) throws RemoteException {
        this.zzfwi.setResult(new Status(i));
    }
}
