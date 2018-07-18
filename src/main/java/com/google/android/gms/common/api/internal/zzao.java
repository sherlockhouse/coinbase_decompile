package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzby;

public final class zzao implements zzbk {
    private final zzbl zzflh;
    private boolean zzfli = false;

    public zzao(zzbl com_google_android_gms_common_api_internal_zzbl) {
        this.zzflh = com_google_android_gms_common_api_internal_zzbl;
    }

    public final void begin() {
    }

    public final void connect() {
        if (this.zzfli) {
            this.zzfli = false;
            this.zzflh.zza(new zzaq(this, this));
        }
    }

    public final boolean disconnect() {
        if (this.zzfli) {
            return false;
        }
        if (this.zzflh.zzfju.zzahj()) {
            this.zzfli = true;
            for (zzdg zzaio : this.zzflh.zzfju.zzfms) {
                zzaio.zzaio();
            }
            return false;
        }
        this.zzflh.zzg(null);
        return true;
    }

    public final void onConnected(Bundle bundle) {
    }

    public final void onConnectionSuspended(int i) {
        this.zzflh.zzg(null);
        this.zzflh.zzfng.zzf(i, this.zzfli);
    }

    public final void zza(ConnectionResult connectionResult, Api<?> api, boolean z) {
    }

    final void zzagy() {
        if (this.zzfli) {
            this.zzfli = false;
            this.zzflh.zzfju.zzfmt.release();
            disconnect();
        }
    }

    public final <A extends zzb, R extends Result, T extends zzm<R, A>> T zzd(T t) {
        return zze(t);
    }

    public final <A extends zzb, T extends zzm<? extends Result, A>> T zze(T t) {
        try {
            this.zzflh.zzfju.zzfmt.zzb(t);
            zzbd com_google_android_gms_common_api_internal_zzbd = this.zzflh.zzfju;
            zzb com_google_android_gms_common_api_Api_zzb = (zze) com_google_android_gms_common_api_internal_zzbd.zzfmn.get(t.zzafe());
            zzbp.zzb((Object) com_google_android_gms_common_api_Api_zzb, (Object) "Appropriate Api was not requested.");
            if (com_google_android_gms_common_api_Api_zzb.isConnected() || !this.zzflh.zzfnc.containsKey(t.zzafe())) {
                if (com_google_android_gms_common_api_Api_zzb instanceof zzby) {
                    com_google_android_gms_common_api_Api_zzb = zzby.zzako();
                }
                t.zzb(com_google_android_gms_common_api_Api_zzb);
                return t;
            }
            t.zzt(new Status(17));
            return t;
        } catch (DeadObjectException e) {
            this.zzflh.zza(new zzap(this, this));
        }
    }
}
