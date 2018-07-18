package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.coinbase.android.utils.CryptoUri;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.internal.zzcpt;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public final class zzbl implements zzcd, zzx {
    private final Context mContext;
    private zza<? extends zzcps, zzcpt> zzfhm;
    final zzbd zzfju;
    private final Lock zzfke;
    private zzq zzfkj;
    private Map<Api<?>, Boolean> zzfkm;
    private final zze zzfko;
    final Map<zzc<?>, Api.zze> zzfmn;
    private final Condition zzfna;
    private final zzbn zzfnb;
    final Map<zzc<?>, ConnectionResult> zzfnc = new HashMap();
    private volatile zzbk zzfnd;
    private ConnectionResult zzfne = null;
    int zzfnf;
    final zzce zzfng;

    public zzbl(Context context, zzbd com_google_android_gms_common_api_internal_zzbd, Lock lock, Looper looper, zze com_google_android_gms_common_zze, Map<zzc<?>, Api.zze> map, zzq com_google_android_gms_common_internal_zzq, Map<Api<?>, Boolean> map2, zza<? extends zzcps, zzcpt> com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcps__com_google_android_gms_internal_zzcpt, ArrayList<zzw> arrayList, zzce com_google_android_gms_common_api_internal_zzce) {
        this.mContext = context;
        this.zzfke = lock;
        this.zzfko = com_google_android_gms_common_zze;
        this.zzfmn = map;
        this.zzfkj = com_google_android_gms_common_internal_zzq;
        this.zzfkm = map2;
        this.zzfhm = com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcps__com_google_android_gms_internal_zzcpt;
        this.zzfju = com_google_android_gms_common_api_internal_zzbd;
        this.zzfng = com_google_android_gms_common_api_internal_zzce;
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            ((zzw) obj).zza(this);
        }
        this.zzfnb = new zzbn(this, looper);
        this.zzfna = lock.newCondition();
        this.zzfnd = new zzbc(this);
    }

    public final ConnectionResult blockingConnect() {
        connect();
        while (isConnecting()) {
            try {
                this.zzfna.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
        }
        return isConnected() ? ConnectionResult.zzfff : this.zzfne != null ? this.zzfne : new ConnectionResult(13, null);
    }

    public final void connect() {
        this.zzfnd.connect();
    }

    public final void disconnect() {
        if (this.zzfnd.disconnect()) {
            this.zzfnc.clear();
        }
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String concat = String.valueOf(str).concat("  ");
        printWriter.append(str).append("mState=").println(this.zzfnd);
        for (Api api : this.zzfkm.keySet()) {
            printWriter.append(str).append(api.getName()).println(CryptoUri.URI_SCHEME_DELIMETER);
            ((Api.zze) this.zzfmn.get(api.zzafe())).dump(concat, fileDescriptor, printWriter, strArr);
        }
    }

    public final boolean isConnected() {
        return this.zzfnd instanceof zzao;
    }

    public final boolean isConnecting() {
        return this.zzfnd instanceof zzar;
    }

    public final void onConnected(Bundle bundle) {
        this.zzfke.lock();
        try {
            this.zzfnd.onConnected(bundle);
        } finally {
            this.zzfke.unlock();
        }
    }

    public final void onConnectionSuspended(int i) {
        this.zzfke.lock();
        try {
            this.zzfnd.onConnectionSuspended(i);
        } finally {
            this.zzfke.unlock();
        }
    }

    public final void zza(ConnectionResult connectionResult, Api<?> api, boolean z) {
        this.zzfke.lock();
        try {
            this.zzfnd.zza(connectionResult, api, z);
        } finally {
            this.zzfke.unlock();
        }
    }

    final void zza(zzbm com_google_android_gms_common_api_internal_zzbm) {
        this.zzfnb.sendMessage(this.zzfnb.obtainMessage(1, com_google_android_gms_common_api_internal_zzbm));
    }

    final void zza(RuntimeException runtimeException) {
        this.zzfnb.sendMessage(this.zzfnb.obtainMessage(2, runtimeException));
    }

    public final boolean zza(zzcv com_google_android_gms_common_api_internal_zzcv) {
        return false;
    }

    public final void zzafp() {
    }

    public final void zzagi() {
        if (isConnected()) {
            ((zzao) this.zzfnd).zzagy();
        }
    }

    final void zzahl() {
        this.zzfke.lock();
        try {
            this.zzfnd = new zzar(this, this.zzfkj, this.zzfkm, this.zzfko, this.zzfhm, this.zzfke, this.mContext);
            this.zzfnd.begin();
            this.zzfna.signalAll();
        } finally {
            this.zzfke.unlock();
        }
    }

    final void zzahm() {
        this.zzfke.lock();
        try {
            this.zzfju.zzahi();
            this.zzfnd = new zzao(this);
            this.zzfnd.begin();
            this.zzfna.signalAll();
        } finally {
            this.zzfke.unlock();
        }
    }

    public final <A extends zzb, R extends Result, T extends zzm<R, A>> T zzd(T t) {
        t.zzagg();
        return this.zzfnd.zzd(t);
    }

    public final <A extends zzb, T extends zzm<? extends Result, A>> T zze(T t) {
        t.zzagg();
        return this.zzfnd.zze(t);
    }

    final void zzg(ConnectionResult connectionResult) {
        this.zzfke.lock();
        try {
            this.zzfne = connectionResult;
            this.zzfnd = new zzbc(this);
            this.zzfnd.begin();
            this.zzfna.signalAll();
        } finally {
            this.zzfke.unlock();
        }
    }
}
