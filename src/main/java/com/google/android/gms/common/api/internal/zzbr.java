package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzby;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public final class zzbr<O extends ApiOptions> implements ConnectionCallbacks, OnConnectionFailedListener, zzx {
    private final zzh<O> zzfgs;
    private final zze zzfkh;
    private boolean zzfmi;
    private /* synthetic */ zzbp zzfnu;
    private final Queue<zza> zzfnv = new LinkedList();
    private final zzb zzfnw;
    private final zzah zzfnx;
    private final Set<zzj> zzfny = new HashSet();
    private final Map<zzcl<?>, zzcs> zzfnz = new HashMap();
    private final int zzfoa;
    private final zzcw zzfob;
    private ConnectionResult zzfoc = null;

    public zzbr(zzbp com_google_android_gms_common_api_internal_zzbp, GoogleApi<O> googleApi) {
        this.zzfnu = com_google_android_gms_common_api_internal_zzbp;
        this.zzfkh = googleApi.zza(com_google_android_gms_common_api_internal_zzbp.mHandler.getLooper(), this);
        if (this.zzfkh instanceof zzby) {
            this.zzfnw = zzby.zzako();
        } else {
            this.zzfnw = this.zzfkh;
        }
        this.zzfgs = googleApi.zzafk();
        this.zzfnx = new zzah();
        this.zzfoa = googleApi.getInstanceId();
        if (this.zzfkh.zzaac()) {
            this.zzfob = googleApi.zza(com_google_android_gms_common_api_internal_zzbp.mContext, com_google_android_gms_common_api_internal_zzbp.mHandler);
        } else {
            this.zzfob = null;
        }
    }

    private final void zzahu() {
        zzahx();
        zzi(ConnectionResult.zzfff);
        zzahz();
        for (zzcs com_google_android_gms_common_api_internal_zzcs : this.zzfnz.values()) {
            try {
                com_google_android_gms_common_api_internal_zzcs.zzfid.zzb(this.zzfnw, new TaskCompletionSource());
            } catch (DeadObjectException e) {
                onConnectionSuspended(1);
                this.zzfkh.disconnect();
            } catch (RemoteException e2) {
            }
        }
        while (this.zzfkh.isConnected() && !this.zzfnv.isEmpty()) {
            zzb((zza) this.zzfnv.remove());
        }
        zzaia();
    }

    private final void zzahv() {
        zzahx();
        this.zzfmi = true;
        this.zzfnx.zzagu();
        this.zzfnu.mHandler.sendMessageDelayed(Message.obtain(this.zzfnu.mHandler, 9, this.zzfgs), this.zzfnu.zzfmk);
        this.zzfnu.mHandler.sendMessageDelayed(Message.obtain(this.zzfnu.mHandler, 11, this.zzfgs), this.zzfnu.zzfmj);
        this.zzfnu.zzfno = -1;
    }

    private final void zzahz() {
        if (this.zzfmi) {
            this.zzfnu.mHandler.removeMessages(11, this.zzfgs);
            this.zzfnu.mHandler.removeMessages(9, this.zzfgs);
            this.zzfmi = false;
        }
    }

    private final void zzaia() {
        this.zzfnu.mHandler.removeMessages(12, this.zzfgs);
        this.zzfnu.mHandler.sendMessageDelayed(this.zzfnu.mHandler.obtainMessage(12, this.zzfgs), this.zzfnu.zzfnm);
    }

    private final void zzb(zza com_google_android_gms_common_api_internal_zza) {
        com_google_android_gms_common_api_internal_zza.zza(this.zzfnx, zzaac());
        try {
            com_google_android_gms_common_api_internal_zza.zza(this);
        } catch (DeadObjectException e) {
            onConnectionSuspended(1);
            this.zzfkh.disconnect();
        }
    }

    private final void zzi(ConnectionResult connectionResult) {
        for (zzj zza : this.zzfny) {
            zza.zza(this.zzfgs, connectionResult);
        }
        this.zzfny.clear();
    }

    public final void connect() {
        zzbp.zza(this.zzfnu.mHandler);
        if (!this.zzfkh.isConnected() && !this.zzfkh.isConnecting()) {
            if (this.zzfkh.zzaff() && this.zzfnu.zzfno != 0) {
                this.zzfnu.zzfno = this.zzfnu.zzfhl.isGooglePlayServicesAvailable(this.zzfnu.mContext);
                if (this.zzfnu.zzfno != 0) {
                    onConnectionFailed(new ConnectionResult(this.zzfnu.zzfno, null));
                    return;
                }
            }
            Object com_google_android_gms_common_api_internal_zzbv = new zzbv(this.zzfnu, this.zzfkh, this.zzfgs);
            if (this.zzfkh.zzaac()) {
                this.zzfob.zza(com_google_android_gms_common_api_internal_zzbv);
            }
            this.zzfkh.zza(com_google_android_gms_common_api_internal_zzbv);
        }
    }

    public final int getInstanceId() {
        return this.zzfoa;
    }

    final boolean isConnected() {
        return this.zzfkh.isConnected();
    }

    public final void onConnected(Bundle bundle) {
        if (Looper.myLooper() == this.zzfnu.mHandler.getLooper()) {
            zzahu();
        } else {
            this.zzfnu.mHandler.post(new zzbs(this));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        zzbp.zza(this.zzfnu.mHandler);
        if (this.zzfob != null) {
            this.zzfob.zzaim();
        }
        zzahx();
        this.zzfnu.zzfno = -1;
        zzi(connectionResult);
        if (connectionResult.getErrorCode() == 4) {
            zzv(zzbp.zzfnl);
        } else if (this.zzfnv.isEmpty()) {
            this.zzfoc = connectionResult;
        } else {
            synchronized (zzbp.zzaqd) {
                if (this.zzfnu.zzfnr != null && this.zzfnu.zzfns.contains(this.zzfgs)) {
                    this.zzfnu.zzfnr.zzb(connectionResult, this.zzfoa);
                }
            }
        }
    }

    public final void onConnectionSuspended(int i) {
        if (Looper.myLooper() == this.zzfnu.mHandler.getLooper()) {
            zzahv();
        } else {
            this.zzfnu.mHandler.post(new zzbt(this));
        }
    }

    public final void resume() {
        zzbp.zza(this.zzfnu.mHandler);
        if (this.zzfmi) {
            connect();
        }
    }

    public final void signOut() {
        zzbp.zza(this.zzfnu.mHandler);
        zzv(zzbp.zzfnk);
        this.zzfnx.zzagt();
        for (zzcl com_google_android_gms_common_api_internal_zzf : this.zzfnz.keySet()) {
            zza(new zzf(com_google_android_gms_common_api_internal_zzf, new TaskCompletionSource()));
        }
        zzi(new ConnectionResult(4));
        this.zzfkh.disconnect();
    }

    public final void zza(ConnectionResult connectionResult, Api<?> api, boolean z) {
        if (Looper.myLooper() == this.zzfnu.mHandler.getLooper()) {
            onConnectionFailed(connectionResult);
        } else {
            this.zzfnu.mHandler.post(new zzbu(this, connectionResult));
        }
    }

    public final void zza(zza com_google_android_gms_common_api_internal_zza) {
        zzbp.zza(this.zzfnu.mHandler);
        if (this.zzfkh.isConnected()) {
            zzb(com_google_android_gms_common_api_internal_zza);
            zzaia();
            return;
        }
        this.zzfnv.add(com_google_android_gms_common_api_internal_zza);
        if (this.zzfoc == null || !this.zzfoc.hasResolution()) {
            connect();
        } else {
            onConnectionFailed(this.zzfoc);
        }
    }

    public final void zza(zzj com_google_android_gms_common_api_internal_zzj) {
        zzbp.zza(this.zzfnu.mHandler);
        this.zzfny.add(com_google_android_gms_common_api_internal_zzj);
    }

    public final boolean zzaac() {
        return this.zzfkh.zzaac();
    }

    public final zze zzagn() {
        return this.zzfkh;
    }

    public final void zzahh() {
        zzbp.zza(this.zzfnu.mHandler);
        if (this.zzfmi) {
            zzahz();
            zzv(this.zzfnu.zzfhl.isGooglePlayServicesAvailable(this.zzfnu.mContext) == 18 ? new Status(8, "Connection timed out while waiting for Google Play services update to complete.") : new Status(8, "API failed to connect while resuming due to an unknown error."));
            this.zzfkh.disconnect();
        }
    }

    public final Map<zzcl<?>, zzcs> zzahw() {
        return this.zzfnz;
    }

    public final void zzahx() {
        zzbp.zza(this.zzfnu.mHandler);
        this.zzfoc = null;
    }

    public final ConnectionResult zzahy() {
        zzbp.zza(this.zzfnu.mHandler);
        return this.zzfoc;
    }

    public final void zzaib() {
        zzbp.zza(this.zzfnu.mHandler);
        if (!this.zzfkh.isConnected() || this.zzfnz.size() != 0) {
            return;
        }
        if (this.zzfnx.zzags()) {
            zzaia();
        } else {
            this.zzfkh.disconnect();
        }
    }

    final zzcps zzaic() {
        return this.zzfob == null ? null : this.zzfob.zzaic();
    }

    public final void zzh(ConnectionResult connectionResult) {
        zzbp.zza(this.zzfnu.mHandler);
        this.zzfkh.disconnect();
        onConnectionFailed(connectionResult);
    }

    public final void zzv(Status status) {
        zzbp.zza(this.zzfnu.mHandler);
        for (zza zzr : this.zzfnv) {
            zzr.zzr(status);
        }
        this.zzfnv.clear();
    }
}
