package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzam;
import com.google.android.gms.common.internal.zzbs;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzs;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.internal.zzcpt;
import com.google.android.gms.internal.zzcqf;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

public final class zzar implements zzbk {
    private final Context mContext;
    private final zza<? extends zzcps, zzcpt> zzfhm;
    private final Lock zzfke;
    private final zzq zzfkj;
    private final Map<Api<?>, Boolean> zzfkm;
    private final zze zzfko;
    private ConnectionResult zzfkx;
    private final zzbl zzflh;
    private int zzflk;
    private int zzfll = 0;
    private int zzflm;
    private final Bundle zzfln = new Bundle();
    private final Set<zzc> zzflo = new HashSet();
    private zzcps zzflp;
    private boolean zzflq;
    private boolean zzflr;
    private boolean zzfls;
    private zzam zzflt;
    private boolean zzflu;
    private boolean zzflv;
    private ArrayList<Future<?>> zzflw = new ArrayList();

    public zzar(zzbl com_google_android_gms_common_api_internal_zzbl, zzq com_google_android_gms_common_internal_zzq, Map<Api<?>, Boolean> map, zze com_google_android_gms_common_zze, zza<? extends zzcps, zzcpt> com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcps__com_google_android_gms_internal_zzcpt, Lock lock, Context context) {
        this.zzflh = com_google_android_gms_common_api_internal_zzbl;
        this.zzfkj = com_google_android_gms_common_internal_zzq;
        this.zzfkm = map;
        this.zzfko = com_google_android_gms_common_zze;
        this.zzfhm = com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcps__com_google_android_gms_internal_zzcpt;
        this.zzfke = lock;
        this.mContext = context;
    }

    private final void zza(zzcqf com_google_android_gms_internal_zzcqf) {
        if (zzbr(0)) {
            ConnectionResult zzagd = com_google_android_gms_internal_zzcqf.zzagd();
            if (zzagd.isSuccess()) {
                zzbs zzbcc = com_google_android_gms_internal_zzcqf.zzbcc();
                ConnectionResult zzagd2 = zzbcc.zzagd();
                if (zzagd2.isSuccess()) {
                    this.zzfls = true;
                    this.zzflt = zzbcc.zzakl();
                    this.zzflu = zzbcc.zzakm();
                    this.zzflv = zzbcc.zzakn();
                    zzahb();
                    return;
                }
                String valueOf = String.valueOf(zzagd2);
                Log.wtf("GoogleApiClientConnecting", new StringBuilder(String.valueOf(valueOf).length() + 48).append("Sign-in succeeded with resolve account failure: ").append(valueOf).toString(), new Exception());
                zze(zzagd2);
            } else if (zzd(zzagd)) {
                zzahd();
                zzahb();
            } else {
                zze(zzagd);
            }
        }
    }

    private final boolean zzaha() {
        this.zzflm--;
        if (this.zzflm > 0) {
            return false;
        }
        if (this.zzflm < 0) {
            Log.w("GoogleApiClientConnecting", this.zzflh.zzfju.zzahk());
            Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
            zze(new ConnectionResult(8, null));
            return false;
        } else if (this.zzfkx == null) {
            return true;
        } else {
            this.zzflh.zzfnf = this.zzflk;
            zze(this.zzfkx);
            return false;
        }
    }

    private final void zzahb() {
        if (this.zzflm == 0) {
            if (!this.zzflr || this.zzfls) {
                ArrayList arrayList = new ArrayList();
                this.zzfll = 1;
                this.zzflm = this.zzflh.zzfmn.size();
                for (zzc com_google_android_gms_common_api_Api_zzc : this.zzflh.zzfmn.keySet()) {
                    if (!this.zzflh.zzfnc.containsKey(com_google_android_gms_common_api_Api_zzc)) {
                        arrayList.add((Api.zze) this.zzflh.zzfmn.get(com_google_android_gms_common_api_Api_zzc));
                    } else if (zzaha()) {
                        zzahc();
                    }
                }
                if (!arrayList.isEmpty()) {
                    this.zzflw.add(zzbo.zzahn().submit(new zzax(this, arrayList)));
                }
            }
        }
    }

    private final void zzahc() {
        this.zzflh.zzahm();
        zzbo.zzahn().execute(new zzas(this));
        if (this.zzflp != null) {
            if (this.zzflu) {
                this.zzflp.zza(this.zzflt, this.zzflv);
            }
            zzbf(false);
        }
        for (zzc com_google_android_gms_common_api_Api_zzc : this.zzflh.zzfnc.keySet()) {
            ((Api.zze) this.zzflh.zzfmn.get(com_google_android_gms_common_api_Api_zzc)).disconnect();
        }
        this.zzflh.zzfng.zzj(this.zzfln.isEmpty() ? null : this.zzfln);
    }

    private final void zzahd() {
        this.zzflr = false;
        this.zzflh.zzfju.zzfmo = Collections.emptySet();
        for (zzc com_google_android_gms_common_api_Api_zzc : this.zzflo) {
            if (!this.zzflh.zzfnc.containsKey(com_google_android_gms_common_api_Api_zzc)) {
                this.zzflh.zzfnc.put(com_google_android_gms_common_api_Api_zzc, new ConnectionResult(17, null));
            }
        }
    }

    private final void zzahe() {
        ArrayList arrayList = this.zzflw;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((Future) obj).cancel(true);
        }
        this.zzflw.clear();
    }

    private final Set<Scope> zzahf() {
        if (this.zzfkj == null) {
            return Collections.emptySet();
        }
        Set<Scope> hashSet = new HashSet(this.zzfkj.zzajr());
        Map zzajt = this.zzfkj.zzajt();
        for (Api api : zzajt.keySet()) {
            if (!this.zzflh.zzfnc.containsKey(api.zzafe())) {
                hashSet.addAll(((zzs) zzajt.get(api)).zzecm);
            }
        }
        return hashSet;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzb(ConnectionResult connectionResult, Api<?> api, boolean z) {
        Object obj = 1;
        int priority = api.zzafc().getPriority();
        if (z) {
            Object obj2;
            if (connectionResult.hasResolution()) {
                obj2 = 1;
            } else if (this.zzfko.zzbn(connectionResult.getErrorCode()) != null) {
                int i = 1;
            } else {
                obj2 = null;
            }
        }
        if (this.zzfkx != null) {
        }
        if (obj != null) {
            this.zzfkx = connectionResult;
            this.zzflk = priority;
        }
        this.zzflh.zzfnc.put(api.zzafe(), connectionResult);
    }

    private final void zzbf(boolean z) {
        if (this.zzflp != null) {
            if (this.zzflp.isConnected() && z) {
                this.zzflp.zzbbv();
            }
            this.zzflp.disconnect();
            this.zzflt = null;
        }
    }

    private final boolean zzbr(int i) {
        if (this.zzfll == i) {
            return true;
        }
        Log.w("GoogleApiClientConnecting", this.zzflh.zzfju.zzahk());
        String valueOf = String.valueOf(this);
        Log.w("GoogleApiClientConnecting", new StringBuilder(String.valueOf(valueOf).length() + 23).append("Unexpected callback in ").append(valueOf).toString());
        Log.w("GoogleApiClientConnecting", "mRemainingConnections=" + this.zzflm);
        valueOf = zzbs(this.zzfll);
        String zzbs = zzbs(i);
        Log.wtf("GoogleApiClientConnecting", new StringBuilder((String.valueOf(valueOf).length() + 70) + String.valueOf(zzbs).length()).append("GoogleApiClient connecting is in step ").append(valueOf).append(" but received callback for step ").append(zzbs).toString(), new Exception());
        zze(new ConnectionResult(8, null));
        return false;
    }

    private static String zzbs(int i) {
        switch (i) {
            case 0:
                return "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
            case 1:
                return "STEP_GETTING_REMOTE_SERVICE";
            default:
                return "UNKNOWN";
        }
    }

    private final boolean zzd(ConnectionResult connectionResult) {
        return this.zzflq && !connectionResult.hasResolution();
    }

    private final void zze(ConnectionResult connectionResult) {
        zzahe();
        zzbf(!connectionResult.hasResolution());
        this.zzflh.zzg(connectionResult);
        this.zzflh.zzfng.zzc(connectionResult);
    }

    public final void begin() {
        this.zzflh.zzfnc.clear();
        this.zzflr = false;
        this.zzfkx = null;
        this.zzfll = 0;
        this.zzflq = true;
        this.zzfls = false;
        this.zzflu = false;
        Map hashMap = new HashMap();
        int i = 0;
        for (Api api : this.zzfkm.keySet()) {
            Api.zze com_google_android_gms_common_api_Api_zze = (Api.zze) this.zzflh.zzfmn.get(api.zzafe());
            int i2 = (api.zzafc().getPriority() == 1 ? 1 : 0) | i;
            boolean booleanValue = ((Boolean) this.zzfkm.get(api)).booleanValue();
            if (com_google_android_gms_common_api_Api_zze.zzaac()) {
                this.zzflr = true;
                if (booleanValue) {
                    this.zzflo.add(api.zzafe());
                } else {
                    this.zzflq = false;
                }
            }
            hashMap.put(com_google_android_gms_common_api_Api_zze, new zzat(this, api, booleanValue));
            i = i2;
        }
        if (i != 0) {
            this.zzflr = false;
        }
        if (this.zzflr) {
            this.zzfkj.zzc(Integer.valueOf(System.identityHashCode(this.zzflh.zzfju)));
            ConnectionCallbacks com_google_android_gms_common_api_internal_zzba = new zzba();
            this.zzflp = (zzcps) this.zzfhm.zza(this.mContext, this.zzflh.zzfju.getLooper(), this.zzfkj, this.zzfkj.zzajx(), com_google_android_gms_common_api_internal_zzba, com_google_android_gms_common_api_internal_zzba);
        }
        this.zzflm = this.zzflh.zzfmn.size();
        this.zzflw.add(zzbo.zzahn().submit(new zzau(this, hashMap)));
    }

    public final void connect() {
    }

    public final boolean disconnect() {
        zzahe();
        zzbf(true);
        this.zzflh.zzg(null);
        return true;
    }

    public final void onConnected(Bundle bundle) {
        if (zzbr(1)) {
            if (bundle != null) {
                this.zzfln.putAll(bundle);
            }
            if (zzaha()) {
                zzahc();
            }
        }
    }

    public final void onConnectionSuspended(int i) {
        zze(new ConnectionResult(8, null));
    }

    public final void zza(ConnectionResult connectionResult, Api<?> api, boolean z) {
        if (zzbr(1)) {
            zzb(connectionResult, api, z);
            if (zzaha()) {
                zzahc();
            }
        }
    }

    public final <A extends zzb, R extends Result, T extends zzm<R, A>> T zzd(T t) {
        this.zzflh.zzfju.zzfks.add(t);
        return t;
    }

    public final <A extends zzb, T extends zzm<? extends Result, A>> T zze(T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
}
