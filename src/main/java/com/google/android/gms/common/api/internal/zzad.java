package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Looper;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzs;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzbdy;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.internal.zzcpt;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public final class zzad implements zzcd {
    private final Looper zzakg;
    private final zzbp zzfgv;
    private final Lock zzfke;
    private final zzq zzfkj;
    private final Map<zzc<?>, zzac<?>> zzfkk = new HashMap();
    private final Map<zzc<?>, zzac<?>> zzfkl = new HashMap();
    private final Map<Api<?>, Boolean> zzfkm;
    private final zzbd zzfkn;
    private final zze zzfko;
    private final Condition zzfkp;
    private final boolean zzfkq;
    private final boolean zzfkr;
    private final Queue<zzm<?, ?>> zzfks = new LinkedList();
    private boolean zzfkt;
    private Map<zzh<?>, ConnectionResult> zzfku;
    private Map<zzh<?>, ConnectionResult> zzfkv;
    private zzag zzfkw;
    private ConnectionResult zzfkx;

    public zzad(Context context, Lock lock, Looper looper, zze com_google_android_gms_common_zze, Map<zzc<?>, Api.zze> map, zzq com_google_android_gms_common_internal_zzq, Map<Api<?>, Boolean> map2, zza<? extends zzcps, zzcpt> com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcps__com_google_android_gms_internal_zzcpt, ArrayList<zzw> arrayList, zzbd com_google_android_gms_common_api_internal_zzbd, boolean z) {
        this.zzfke = lock;
        this.zzakg = looper;
        this.zzfkp = lock.newCondition();
        this.zzfko = com_google_android_gms_common_zze;
        this.zzfkn = com_google_android_gms_common_api_internal_zzbd;
        this.zzfkm = map2;
        this.zzfkj = com_google_android_gms_common_internal_zzq;
        this.zzfkq = z;
        Map hashMap = new HashMap();
        for (Api api : map2.keySet()) {
            hashMap.put(api.zzafe(), api);
        }
        Map hashMap2 = new HashMap();
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            zzw com_google_android_gms_common_api_internal_zzw = (zzw) obj;
            hashMap2.put(com_google_android_gms_common_api_internal_zzw.zzfdg, com_google_android_gms_common_api_internal_zzw);
        }
        Object obj2 = 1;
        Object obj3 = null;
        Object obj4 = null;
        for (Entry entry : map.entrySet()) {
            Object obj5;
            Object obj6;
            Object obj7;
            Api api2 = (Api) hashMap.get(entry.getKey());
            Api.zze com_google_android_gms_common_api_Api_zze = (Api.zze) entry.getValue();
            if (com_google_android_gms_common_api_Api_zze.zzaff()) {
                obj5 = 1;
                if (((Boolean) this.zzfkm.get(api2)).booleanValue()) {
                    obj6 = obj2;
                    obj7 = obj3;
                } else {
                    obj6 = obj2;
                    obj7 = 1;
                }
            } else {
                obj5 = obj4;
                obj6 = null;
                obj7 = obj3;
            }
            zzac com_google_android_gms_common_api_internal_zzac = new zzac(context, api2, looper, com_google_android_gms_common_api_Api_zze, (zzw) hashMap2.get(api2), com_google_android_gms_common_internal_zzq, com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcps__com_google_android_gms_internal_zzcpt);
            this.zzfkk.put((zzc) entry.getKey(), com_google_android_gms_common_api_internal_zzac);
            if (com_google_android_gms_common_api_Api_zze.zzaac()) {
                this.zzfkl.put((zzc) entry.getKey(), com_google_android_gms_common_api_internal_zzac);
            }
            obj4 = obj5;
            obj2 = obj6;
            obj3 = obj7;
        }
        boolean z2 = obj4 != null && obj2 == null && obj3 == null;
        this.zzfkr = z2;
        this.zzfgv = zzbp.zzaho();
    }

    private final boolean zza(zzac<?> com_google_android_gms_common_api_internal_zzac_, ConnectionResult connectionResult) {
        return !connectionResult.isSuccess() && !connectionResult.hasResolution() && ((Boolean) this.zzfkm.get(com_google_android_gms_common_api_internal_zzac_.zzafj())).booleanValue() && com_google_android_gms_common_api_internal_zzac_.zzagn().zzaff() && this.zzfko.isUserResolvableError(connectionResult.getErrorCode());
    }

    private final boolean zzago() {
        this.zzfke.lock();
        try {
            if (this.zzfkt && this.zzfkq) {
                for (zzc zzb : this.zzfkl.keySet()) {
                    ConnectionResult zzb2 = zzb(zzb);
                    if (zzb2 != null) {
                        if (!zzb2.isSuccess()) {
                        }
                    }
                    this.zzfke.unlock();
                    return false;
                }
                this.zzfke.unlock();
                return true;
            }
            this.zzfke.unlock();
            return false;
        } catch (Throwable th) {
            this.zzfke.unlock();
        }
    }

    private final void zzagp() {
        if (this.zzfkj == null) {
            this.zzfkn.zzfmo = Collections.emptySet();
            return;
        }
        Set hashSet = new HashSet(this.zzfkj.zzajr());
        Map zzajt = this.zzfkj.zzajt();
        for (Api api : zzajt.keySet()) {
            ConnectionResult connectionResult = getConnectionResult(api);
            if (connectionResult != null && connectionResult.isSuccess()) {
                hashSet.addAll(((zzs) zzajt.get(api)).zzecm);
            }
        }
        this.zzfkn.zzfmo = hashSet;
    }

    private final void zzagq() {
        while (!this.zzfks.isEmpty()) {
            zze((zzm) this.zzfks.remove());
        }
        this.zzfkn.zzj(null);
    }

    private final ConnectionResult zzagr() {
        int i = 0;
        ConnectionResult connectionResult = null;
        int i2 = 0;
        ConnectionResult connectionResult2 = null;
        for (zzac com_google_android_gms_common_api_internal_zzac : this.zzfkk.values()) {
            Api zzafj = com_google_android_gms_common_api_internal_zzac.zzafj();
            ConnectionResult connectionResult3 = (ConnectionResult) this.zzfku.get(com_google_android_gms_common_api_internal_zzac.zzafk());
            if (!connectionResult3.isSuccess() && (!((Boolean) this.zzfkm.get(zzafj)).booleanValue() || connectionResult3.hasResolution() || this.zzfko.isUserResolvableError(connectionResult3.getErrorCode()))) {
                int priority;
                if (connectionResult3.getErrorCode() == 4 && this.zzfkq) {
                    priority = zzafj.zzafc().getPriority();
                    if (connectionResult == null || i > priority) {
                        i = priority;
                        connectionResult = connectionResult3;
                    }
                } else {
                    ConnectionResult connectionResult4;
                    int i3;
                    priority = zzafj.zzafc().getPriority();
                    if (connectionResult2 == null || i2 > priority) {
                        int i4 = priority;
                        connectionResult4 = connectionResult3;
                        i3 = i4;
                    } else {
                        i3 = i2;
                        connectionResult4 = connectionResult2;
                    }
                    i2 = i3;
                    connectionResult2 = connectionResult4;
                }
            }
        }
        return (connectionResult2 == null || connectionResult == null || i2 <= i) ? connectionResult2 : connectionResult;
    }

    private final ConnectionResult zzb(zzc<?> com_google_android_gms_common_api_Api_zzc_) {
        this.zzfke.lock();
        try {
            zzac com_google_android_gms_common_api_internal_zzac = (zzac) this.zzfkk.get(com_google_android_gms_common_api_Api_zzc_);
            if (this.zzfku == null || com_google_android_gms_common_api_internal_zzac == null) {
                this.zzfke.unlock();
                return null;
            }
            ConnectionResult connectionResult = (ConnectionResult) this.zzfku.get(com_google_android_gms_common_api_internal_zzac.zzafk());
            return connectionResult;
        } finally {
            this.zzfke.unlock();
        }
    }

    private final <T extends zzm<? extends Result, ? extends zzb>> boolean zzg(T t) {
        zzc zzafe = t.zzafe();
        ConnectionResult zzb = zzb(zzafe);
        if (zzb == null || zzb.getErrorCode() != 4) {
            return false;
        }
        t.zzt(new Status(4, null, this.zzfgv.zza(((zzac) this.zzfkk.get(zzafe)).zzafk(), System.identityHashCode(this.zzfkn))));
        return true;
    }

    public final ConnectionResult blockingConnect() {
        connect();
        while (isConnecting()) {
            try {
                this.zzfkp.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
        }
        return isConnected() ? ConnectionResult.zzfff : this.zzfkx != null ? this.zzfkx : new ConnectionResult(13, null);
    }

    public final void connect() {
        this.zzfke.lock();
        try {
            if (!this.zzfkt) {
                this.zzfkt = true;
                this.zzfku = null;
                this.zzfkv = null;
                this.zzfkw = null;
                this.zzfkx = null;
                this.zzfgv.zzafw();
                this.zzfgv.zza(this.zzfkk.values()).addOnCompleteListener(new zzbdy(this.zzakg), new zzaf());
                this.zzfke.unlock();
            }
        } finally {
            this.zzfke.unlock();
        }
    }

    public final void disconnect() {
        this.zzfke.lock();
        try {
            this.zzfkt = false;
            this.zzfku = null;
            this.zzfkv = null;
            if (this.zzfkw != null) {
                this.zzfkw.cancel();
                this.zzfkw = null;
            }
            this.zzfkx = null;
            while (!this.zzfks.isEmpty()) {
                zzm com_google_android_gms_common_api_internal_zzm = (zzm) this.zzfks.remove();
                com_google_android_gms_common_api_internal_zzm.zza(null);
                com_google_android_gms_common_api_internal_zzm.cancel();
            }
            this.zzfkp.signalAll();
        } finally {
            this.zzfke.unlock();
        }
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public final ConnectionResult getConnectionResult(Api<?> api) {
        return zzb(api.zzafe());
    }

    public final boolean isConnected() {
        this.zzfke.lock();
        try {
            boolean z = this.zzfku != null && this.zzfkx == null;
            this.zzfke.unlock();
            return z;
        } catch (Throwable th) {
            this.zzfke.unlock();
        }
    }

    public final boolean isConnecting() {
        this.zzfke.lock();
        try {
            boolean z = this.zzfku == null && this.zzfkt;
            this.zzfke.unlock();
            return z;
        } catch (Throwable th) {
            this.zzfke.unlock();
        }
    }

    public final boolean zza(zzcv com_google_android_gms_common_api_internal_zzcv) {
        this.zzfke.lock();
        try {
            if (!this.zzfkt || zzago()) {
                this.zzfke.unlock();
                return false;
            }
            this.zzfgv.zzafw();
            this.zzfkw = new zzag(this, com_google_android_gms_common_api_internal_zzcv);
            this.zzfgv.zza(this.zzfkl.values()).addOnCompleteListener(new zzbdy(this.zzakg), this.zzfkw);
            return true;
        } finally {
            this.zzfke.unlock();
        }
    }

    public final void zzafp() {
        this.zzfke.lock();
        try {
            this.zzfgv.zzafp();
            if (this.zzfkw != null) {
                this.zzfkw.cancel();
                this.zzfkw = null;
            }
            if (this.zzfkv == null) {
                this.zzfkv = new ArrayMap(this.zzfkl.size());
            }
            ConnectionResult connectionResult = new ConnectionResult(4);
            for (zzac zzafk : this.zzfkl.values()) {
                this.zzfkv.put(zzafk.zzafk(), connectionResult);
            }
            if (this.zzfku != null) {
                this.zzfku.putAll(this.zzfkv);
            }
            this.zzfke.unlock();
        } catch (Throwable th) {
            this.zzfke.unlock();
        }
    }

    public final void zzagi() {
    }

    public final <A extends zzb, R extends Result, T extends zzm<R, A>> T zzd(T t) {
        if (this.zzfkq && zzg((zzm) t)) {
            return t;
        }
        if (isConnected()) {
            this.zzfkn.zzfmt.zzb(t);
            return ((zzac) this.zzfkk.get(t.zzafe())).zza(t);
        }
        this.zzfks.add(t);
        return t;
    }

    public final <A extends zzb, T extends zzm<? extends Result, A>> T zze(T t) {
        zzc zzafe = t.zzafe();
        if (this.zzfkq && zzg((zzm) t)) {
            return t;
        }
        this.zzfkn.zzfmt.zzb(t);
        return ((zzac) this.zzfkk.get(zzafe)).zzb(t);
    }
}
