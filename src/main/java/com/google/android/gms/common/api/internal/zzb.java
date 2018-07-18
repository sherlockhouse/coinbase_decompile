package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zzb<T> extends zza {
    protected final TaskCompletionSource<T> zzdzc;

    public zzb(int i, TaskCompletionSource<T> taskCompletionSource) {
        super(i);
        this.zzdzc = taskCompletionSource;
    }

    public void zza(zzah com_google_android_gms_common_api_internal_zzah, boolean z) {
    }

    public final void zza(zzbr<?> com_google_android_gms_common_api_internal_zzbr_) throws DeadObjectException {
        try {
            zzb(com_google_android_gms_common_api_internal_zzbr_);
        } catch (RemoteException e) {
            zzr(zza.zza(e));
            throw e;
        } catch (RemoteException e2) {
            zzr(zza.zza(e2));
        }
    }

    protected abstract void zzb(zzbr<?> com_google_android_gms_common_api_internal_zzbr_) throws RemoteException;

    public void zzr(Status status) {
        this.zzdzc.trySetException(new ApiException(status));
    }
}
