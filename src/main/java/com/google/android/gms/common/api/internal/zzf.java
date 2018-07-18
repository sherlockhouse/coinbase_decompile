package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzf extends zzb<Boolean> {
    private zzcl<?> zzfih;

    public zzf(zzcl<?> com_google_android_gms_common_api_internal_zzcl_, TaskCompletionSource<Boolean> taskCompletionSource) {
        super(4, taskCompletionSource);
        this.zzfih = com_google_android_gms_common_api_internal_zzcl_;
    }

    public final /* bridge */ /* synthetic */ void zza(zzah com_google_android_gms_common_api_internal_zzah, boolean z) {
    }

    public final void zzb(zzbr<?> com_google_android_gms_common_api_internal_zzbr_) throws RemoteException {
        zzcs com_google_android_gms_common_api_internal_zzcs = (zzcs) com_google_android_gms_common_api_internal_zzbr_.zzahw().remove(this.zzfih);
        if (com_google_android_gms_common_api_internal_zzcs != null) {
            com_google_android_gms_common_api_internal_zzcs.zzfie.zzc(com_google_android_gms_common_api_internal_zzbr_.zzagn(), this.zzdzc);
            com_google_android_gms_common_api_internal_zzcs.zzfid.zzail();
            return;
        }
        this.zzdzc.trySetResult(Boolean.valueOf(false));
    }

    public final /* bridge */ /* synthetic */ void zzr(Status status) {
        super.zzr(status);
    }
}
