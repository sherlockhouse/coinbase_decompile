package com.google.android.gms.common.api.internal;

import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Set;

public final class zzj {
    private final ArrayMap<zzh<?>, ConnectionResult> zzfgj = new ArrayMap();
    private final TaskCompletionSource<Void> zzfip = new TaskCompletionSource();
    private int zzfiq;
    private boolean zzfir = false;

    public zzj(Iterable<? extends GoogleApi<?>> iterable) {
        for (GoogleApi zzafk : iterable) {
            this.zzfgj.put(zzafk.zzafk(), null);
        }
        this.zzfiq = this.zzfgj.keySet().size();
    }

    public final Task<Void> getTask() {
        return this.zzfip.getTask();
    }

    public final void zza(zzh<?> com_google_android_gms_common_api_internal_zzh_, ConnectionResult connectionResult) {
        this.zzfgj.put(com_google_android_gms_common_api_internal_zzh_, connectionResult);
        this.zzfiq--;
        if (!connectionResult.isSuccess()) {
            this.zzfir = true;
        }
        if (this.zzfiq != 0) {
            return;
        }
        if (this.zzfir) {
            this.zzfip.setException(new AvailabilityException(this.zzfgj));
            return;
        }
        this.zzfip.setResult(null);
    }

    public final Set<zzh<?>> zzafx() {
        return this.zzfgj.keySet();
    }

    public final void zzafy() {
        this.zzfip.setResult(null);
    }
}
