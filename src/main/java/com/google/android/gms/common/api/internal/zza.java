package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.zzq;

public abstract class zza {
    private int zzecz;

    public zza(int i) {
        this.zzecz = i;
    }

    private static Status zza(RemoteException remoteException) {
        StringBuilder stringBuilder = new StringBuilder();
        if (zzq.zzald() && (remoteException instanceof TransactionTooLargeException)) {
            stringBuilder.append("TransactionTooLargeException: ");
        }
        stringBuilder.append(remoteException.getLocalizedMessage());
        return new Status(8, stringBuilder.toString());
    }

    public abstract void zza(zzah com_google_android_gms_common_api_internal_zzah, boolean z);

    public abstract void zza(zzbr<?> com_google_android_gms_common_api_internal_zzbr_) throws DeadObjectException;

    public abstract void zzr(Status status);
}
