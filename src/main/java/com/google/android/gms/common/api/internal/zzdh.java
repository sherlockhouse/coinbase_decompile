package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;

final class zzdh implements Runnable {
    private /* synthetic */ Result zzfpo;
    private /* synthetic */ zzdg zzfpp;

    zzdh(zzdg com_google_android_gms_common_api_internal_zzdg, Result result) {
        this.zzfpp = com_google_android_gms_common_api_internal_zzdg;
        this.zzfpo = result;
    }

    public final void run() {
        GoogleApiClient googleApiClient;
        try {
            zzs.zzfje.set(Boolean.valueOf(true));
            this.zzfpp.zzfpm.sendMessage(this.zzfpp.zzfpm.obtainMessage(0, this.zzfpp.zzfph.onSuccess(this.zzfpo)));
            zzs.zzfje.set(Boolean.valueOf(false));
            zzdg.zzd(this.zzfpo);
            googleApiClient = (GoogleApiClient) this.zzfpp.zzfjh.get();
            if (googleApiClient != null) {
                googleApiClient.zzb(this.zzfpp);
            }
        } catch (RuntimeException e) {
            this.zzfpp.zzfpm.sendMessage(this.zzfpp.zzfpm.obtainMessage(1, e));
            zzs.zzfje.set(Boolean.valueOf(false));
            zzdg.zzd(this.zzfpo);
            googleApiClient = (GoogleApiClient) this.zzfpp.zzfjh.get();
            if (googleApiClient != null) {
                googleApiClient.zzb(this.zzfpp);
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            zzs.zzfje.set(Boolean.valueOf(false));
            zzdg.zzd(this.zzfpo);
            googleApiClient = (GoogleApiClient) this.zzfpp.zzfjh.get();
            if (googleApiClient != null) {
                googleApiClient.zzb(this.zzfpp);
            }
        }
    }
}
