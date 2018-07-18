package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzbs;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.internal.zzcpp;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.internal.zzcpt;
import com.google.android.gms.internal.zzcpx;
import com.google.android.gms.internal.zzcqf;
import java.util.Set;

public final class zzcw extends zzcpx implements ConnectionCallbacks, OnConnectionFailedListener {
    private static zza<? extends zzcps, zzcpt> zzfpd = zzcpp.zzdwq;
    private final Context mContext;
    private final Handler mHandler;
    private Set<Scope> zzecm;
    private final zza<? extends zzcps, zzcpt> zzfgf;
    private zzq zzfkj;
    private zzcps zzflp;
    private zzcy zzfpe;

    public zzcw(Context context, Handler handler, zzq com_google_android_gms_common_internal_zzq) {
        this(context, handler, com_google_android_gms_common_internal_zzq, zzfpd);
    }

    public zzcw(Context context, Handler handler, zzq com_google_android_gms_common_internal_zzq, zza<? extends zzcps, zzcpt> com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcps__com_google_android_gms_internal_zzcpt) {
        this.mContext = context;
        this.mHandler = handler;
        this.zzfkj = (zzq) zzbp.zzb((Object) com_google_android_gms_common_internal_zzq, (Object) "ClientSettings must not be null");
        this.zzecm = com_google_android_gms_common_internal_zzq.zzajr();
        this.zzfgf = com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcps__com_google_android_gms_internal_zzcpt;
    }

    private final void zzc(zzcqf com_google_android_gms_internal_zzcqf) {
        ConnectionResult zzagd = com_google_android_gms_internal_zzcqf.zzagd();
        if (zzagd.isSuccess()) {
            zzbs zzbcc = com_google_android_gms_internal_zzcqf.zzbcc();
            ConnectionResult zzagd2 = zzbcc.zzagd();
            if (zzagd2.isSuccess()) {
                this.zzfpe.zzb(zzbcc.zzakl(), this.zzecm);
            } else {
                String valueOf = String.valueOf(zzagd2);
                Log.wtf("SignInCoordinator", new StringBuilder(String.valueOf(valueOf).length() + 48).append("Sign-in succeeded with resolve account failure: ").append(valueOf).toString(), new Exception());
                this.zzfpe.zzh(zzagd2);
                this.zzflp.disconnect();
                return;
            }
        }
        this.zzfpe.zzh(zzagd);
        this.zzflp.disconnect();
    }

    public final void onConnected(Bundle bundle) {
        this.zzflp.zza(this);
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        this.zzfpe.zzh(connectionResult);
    }

    public final void onConnectionSuspended(int i) {
        this.zzflp.disconnect();
    }

    public final void zza(zzcy com_google_android_gms_common_api_internal_zzcy) {
        if (this.zzflp != null) {
            this.zzflp.disconnect();
        }
        this.zzfkj.zzc(Integer.valueOf(System.identityHashCode(this)));
        this.zzflp = (zzcps) this.zzfgf.zza(this.mContext, this.mHandler.getLooper(), this.zzfkj, this.zzfkj.zzajx(), this, this);
        this.zzfpe = com_google_android_gms_common_api_internal_zzcy;
        this.zzflp.connect();
    }

    public final zzcps zzaic() {
        return this.zzflp;
    }

    public final void zzaim() {
        if (this.zzflp != null) {
            this.zzflp.disconnect();
        }
    }

    public final void zzb(zzcqf com_google_android_gms_internal_zzcqf) {
        this.mHandler.post(new zzcx(this, com_google_android_gms_internal_zzcqf));
    }
}
