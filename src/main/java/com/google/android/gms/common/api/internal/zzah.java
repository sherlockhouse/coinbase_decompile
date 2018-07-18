package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

public final class zzah {
    private final Map<zzs<?>, Boolean> zzfla = Collections.synchronizedMap(new WeakHashMap());
    private final Map<TaskCompletionSource<?>, Boolean> zzflb = Collections.synchronizedMap(new WeakHashMap());

    private final void zza(boolean z, Status status) {
        synchronized (this.zzfla) {
            Map hashMap = new HashMap(this.zzfla);
        }
        synchronized (this.zzflb) {
            Map hashMap2 = new HashMap(this.zzflb);
        }
        for (Entry entry : hashMap.entrySet()) {
            if (z || ((Boolean) entry.getValue()).booleanValue()) {
                ((zzs) entry.getKey()).zzu(status);
            }
        }
        for (Entry entry2 : hashMap2.entrySet()) {
            if (z || ((Boolean) entry2.getValue()).booleanValue()) {
                ((TaskCompletionSource) entry2.getKey()).trySetException(new ApiException(status));
            }
        }
    }

    final void zza(zzs<? extends Result> com_google_android_gms_common_api_internal_zzs__extends_com_google_android_gms_common_api_Result, boolean z) {
        this.zzfla.put(com_google_android_gms_common_api_internal_zzs__extends_com_google_android_gms_common_api_Result, Boolean.valueOf(z));
        com_google_android_gms_common_api_internal_zzs__extends_com_google_android_gms_common_api_Result.zza(new zzai(this, com_google_android_gms_common_api_internal_zzs__extends_com_google_android_gms_common_api_Result));
    }

    final boolean zzags() {
        return (this.zzfla.isEmpty() && this.zzflb.isEmpty()) ? false : true;
    }

    public final void zzagt() {
        zza(false, zzbp.zzfnk);
    }

    public final void zzagu() {
        zza(true, zzdj.zzfpq);
    }
}
