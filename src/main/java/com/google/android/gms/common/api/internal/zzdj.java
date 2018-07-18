package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public final class zzdj {
    public static final Status zzfpq = new Status(8, "The connection to Google Play services was lost");
    private static final zzs<?>[] zzfpr = new zzs[0];
    private final Map<zzc<?>, zze> zzfmn;
    final Set<zzs<?>> zzfps = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
    private final zzdm zzfpt = new zzdk(this);

    public zzdj(Map<zzc<?>, zze> map) {
        this.zzfmn = map;
    }

    public final void release() {
        zzdm com_google_android_gms_common_api_internal_zzdm = null;
        for (PendingResult pendingResult : (zzs[]) this.zzfps.toArray(zzfpr)) {
            pendingResult.zza(com_google_android_gms_common_api_internal_zzdm);
            if (pendingResult.zzafs() != null) {
                pendingResult.setResultCallback(com_google_android_gms_common_api_internal_zzdm);
                IBinder zzafg = ((zze) this.zzfmn.get(((zzm) pendingResult).zzafe())).zzafg();
                if (pendingResult.isReady()) {
                    pendingResult.zza(new zzdl(pendingResult, com_google_android_gms_common_api_internal_zzdm, zzafg, com_google_android_gms_common_api_internal_zzdm));
                } else if (zzafg == null || !zzafg.isBinderAlive()) {
                    pendingResult.zza(com_google_android_gms_common_api_internal_zzdm);
                    pendingResult.cancel();
                    com_google_android_gms_common_api_internal_zzdm.remove(pendingResult.zzafs().intValue());
                } else {
                    zzdm com_google_android_gms_common_api_internal_zzdl = new zzdl(pendingResult, com_google_android_gms_common_api_internal_zzdm, zzafg, com_google_android_gms_common_api_internal_zzdm);
                    pendingResult.zza(com_google_android_gms_common_api_internal_zzdl);
                    try {
                        zzafg.linkToDeath(com_google_android_gms_common_api_internal_zzdl, 0);
                    } catch (RemoteException e) {
                        pendingResult.cancel();
                        com_google_android_gms_common_api_internal_zzdm.remove(pendingResult.zzafs().intValue());
                    }
                }
                this.zzfps.remove(pendingResult);
            } else if (pendingResult.zzagf()) {
                this.zzfps.remove(pendingResult);
            }
        }
    }

    public final void zzaiq() {
        for (zzs zzu : (zzs[]) this.zzfps.toArray(zzfpr)) {
            zzu.zzu(zzfpq);
        }
    }

    final void zzb(zzs<? extends Result> com_google_android_gms_common_api_internal_zzs__extends_com_google_android_gms_common_api_Result) {
        this.zzfps.add(com_google_android_gms_common_api_internal_zzs__extends_com_google_android_gms_common_api_Result);
        com_google_android_gms_common_api_internal_zzs__extends_com_google_android_gms_common_api_Result.zza(this.zzfpt);
    }
}
