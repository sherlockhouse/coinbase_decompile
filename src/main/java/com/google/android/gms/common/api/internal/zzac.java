package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.internal.zzcpt;

public final class zzac<O extends ApiOptions> extends GoogleApi<O> {
    private final zza<? extends zzcps, zzcpt> zzfhm;
    private final zze zzfkh;
    private final zzw zzfki;
    private final zzq zzfkj;

    public zzac(Context context, Api<O> api, Looper looper, zze com_google_android_gms_common_api_Api_zze, zzw com_google_android_gms_common_api_internal_zzw, zzq com_google_android_gms_common_internal_zzq, zza<? extends zzcps, zzcpt> com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcps__com_google_android_gms_internal_zzcpt) {
        super(context, api, looper);
        this.zzfkh = com_google_android_gms_common_api_Api_zze;
        this.zzfki = com_google_android_gms_common_api_internal_zzw;
        this.zzfkj = com_google_android_gms_common_internal_zzq;
        this.zzfhm = com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcps__com_google_android_gms_internal_zzcpt;
        this.zzfgv.zza((GoogleApi) this);
    }

    public final zze zza(Looper looper, zzbr<O> com_google_android_gms_common_api_internal_zzbr_O) {
        this.zzfki.zza(com_google_android_gms_common_api_internal_zzbr_O);
        return this.zzfkh;
    }

    public final zzcw zza(Context context, Handler handler) {
        return new zzcw(context, handler, this.zzfkj, this.zzfhm);
    }

    public final zze zzagn() {
        return this.zzfkh;
    }
}
