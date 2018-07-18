package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.coinbase.android.utils.CryptoUri;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.internal.zzcpt;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;

final class zzy implements zzcd {
    private final Context mContext;
    private final Looper zzakg;
    private final zzbd zzfju;
    private final zzbl zzfjv;
    private final zzbl zzfjw;
    private final Map<zzc<?>, zzbl> zzfjx;
    private final Set<zzcv> zzfjy = Collections.newSetFromMap(new WeakHashMap());
    private final zze zzfjz;
    private Bundle zzfka;
    private ConnectionResult zzfkb = null;
    private ConnectionResult zzfkc = null;
    private boolean zzfkd = false;
    private final Lock zzfke;
    private int zzfkf = 0;

    private zzy(Context context, zzbd com_google_android_gms_common_api_internal_zzbd, Lock lock, Looper looper, com.google.android.gms.common.zze com_google_android_gms_common_zze, Map<zzc<?>, zze> map, Map<zzc<?>, zze> map2, zzq com_google_android_gms_common_internal_zzq, zza<? extends zzcps, zzcpt> com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcps__com_google_android_gms_internal_zzcpt, zze com_google_android_gms_common_api_Api_zze, ArrayList<zzw> arrayList, ArrayList<zzw> arrayList2, Map<Api<?>, Boolean> map3, Map<Api<?>, Boolean> map4) {
        this.mContext = context;
        this.zzfju = com_google_android_gms_common_api_internal_zzbd;
        this.zzfke = lock;
        this.zzakg = looper;
        this.zzfjz = com_google_android_gms_common_api_Api_zze;
        this.zzfjv = new zzbl(context, this.zzfju, lock, looper, com_google_android_gms_common_zze, map2, null, map4, null, arrayList2, new zzaa());
        this.zzfjw = new zzbl(context, this.zzfju, lock, looper, com_google_android_gms_common_zze, map, com_google_android_gms_common_internal_zzq, map3, com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcps__com_google_android_gms_internal_zzcpt, arrayList, new zzab());
        Map arrayMap = new ArrayMap();
        for (zzc put : map2.keySet()) {
            arrayMap.put(put, this.zzfjv);
        }
        for (zzc put2 : map.keySet()) {
            arrayMap.put(put2, this.zzfjw);
        }
        this.zzfjx = Collections.unmodifiableMap(arrayMap);
    }

    public static zzy zza(Context context, zzbd com_google_android_gms_common_api_internal_zzbd, Lock lock, Looper looper, com.google.android.gms.common.zze com_google_android_gms_common_zze, Map<zzc<?>, zze> map, zzq com_google_android_gms_common_internal_zzq, Map<Api<?>, Boolean> map2, zza<? extends zzcps, zzcpt> com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcps__com_google_android_gms_internal_zzcpt, ArrayList<zzw> arrayList) {
        zze com_google_android_gms_common_api_Api_zze = null;
        Map arrayMap = new ArrayMap();
        Map arrayMap2 = new ArrayMap();
        for (Entry entry : map.entrySet()) {
            zze com_google_android_gms_common_api_Api_zze2 = (zze) entry.getValue();
            if (com_google_android_gms_common_api_Api_zze2.zzaal()) {
                com_google_android_gms_common_api_Api_zze = com_google_android_gms_common_api_Api_zze2;
            }
            if (com_google_android_gms_common_api_Api_zze2.zzaac()) {
                arrayMap.put((zzc) entry.getKey(), com_google_android_gms_common_api_Api_zze2);
            } else {
                arrayMap2.put((zzc) entry.getKey(), com_google_android_gms_common_api_Api_zze2);
            }
        }
        zzbp.zza(!arrayMap.isEmpty(), "CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
        Map arrayMap3 = new ArrayMap();
        Map arrayMap4 = new ArrayMap();
        for (Api api : map2.keySet()) {
            zzc zzafe = api.zzafe();
            if (arrayMap.containsKey(zzafe)) {
                arrayMap3.put(api, (Boolean) map2.get(api));
            } else if (arrayMap2.containsKey(zzafe)) {
                arrayMap4.put(api, (Boolean) map2.get(api));
            } else {
                throw new IllegalStateException("Each API in the isOptionalMap must have a corresponding client in the clients map.");
            }
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = arrayList;
        int size = arrayList4.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList4.get(i);
            i++;
            zzw com_google_android_gms_common_api_internal_zzw = (zzw) obj;
            if (arrayMap3.containsKey(com_google_android_gms_common_api_internal_zzw.zzfdg)) {
                arrayList2.add(com_google_android_gms_common_api_internal_zzw);
            } else if (arrayMap4.containsKey(com_google_android_gms_common_api_internal_zzw.zzfdg)) {
                arrayList3.add(com_google_android_gms_common_api_internal_zzw);
            } else {
                throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the isOptionalMap");
            }
        }
        return new zzy(context, com_google_android_gms_common_api_internal_zzbd, lock, looper, com_google_android_gms_common_zze, arrayMap, arrayMap2, com_google_android_gms_common_internal_zzq, com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcps__com_google_android_gms_internal_zzcpt, com_google_android_gms_common_api_Api_zze, arrayList2, arrayList3, arrayMap3, arrayMap4);
    }

    private final void zza(ConnectionResult connectionResult) {
        switch (this.zzfkf) {
            case 1:
                break;
            case 2:
                this.zzfju.zzc(connectionResult);
                break;
            default:
                Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
                break;
        }
        zzagk();
        this.zzfkf = 0;
    }

    private final void zzagj() {
        if (zzb(this.zzfkb)) {
            if (zzb(this.zzfkc) || zzagl()) {
                switch (this.zzfkf) {
                    case 1:
                        break;
                    case 2:
                        this.zzfju.zzj(this.zzfka);
                        break;
                    default:
                        Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new AssertionError());
                        break;
                }
                zzagk();
                this.zzfkf = 0;
            } else if (this.zzfkc == null) {
            } else {
                if (this.zzfkf == 1) {
                    zzagk();
                    return;
                }
                zza(this.zzfkc);
                this.zzfjv.disconnect();
            }
        } else if (this.zzfkb != null && zzb(this.zzfkc)) {
            this.zzfjw.disconnect();
            zza(this.zzfkb);
        } else if (this.zzfkb != null && this.zzfkc != null) {
            ConnectionResult connectionResult = this.zzfkb;
            if (this.zzfjw.zzfnf < this.zzfjv.zzfnf) {
                connectionResult = this.zzfkc;
            }
            zza(connectionResult);
        }
    }

    private final void zzagk() {
        for (zzcv zzaak : this.zzfjy) {
            zzaak.zzaak();
        }
        this.zzfjy.clear();
    }

    private final boolean zzagl() {
        return this.zzfkc != null && this.zzfkc.getErrorCode() == 4;
    }

    private final PendingIntent zzagm() {
        return this.zzfjz == null ? null : PendingIntent.getActivity(this.mContext, System.identityHashCode(this.zzfju), this.zzfjz.zzaam(), 134217728);
    }

    private static boolean zzb(ConnectionResult connectionResult) {
        return connectionResult != null && connectionResult.isSuccess();
    }

    private final void zze(int i, boolean z) {
        this.zzfju.zzf(i, z);
        this.zzfkc = null;
        this.zzfkb = null;
    }

    private final boolean zzf(zzm<? extends Result, ? extends zzb> com_google_android_gms_common_api_internal_zzm__extends_com_google_android_gms_common_api_Result___extends_com_google_android_gms_common_api_Api_zzb) {
        zzc zzafe = com_google_android_gms_common_api_internal_zzm__extends_com_google_android_gms_common_api_Result___extends_com_google_android_gms_common_api_Api_zzb.zzafe();
        zzbp.zzb(this.zzfjx.containsKey(zzafe), (Object) "GoogleApiClient is not configured to use the API required for this call.");
        return ((zzbl) this.zzfjx.get(zzafe)).equals(this.zzfjw);
    }

    private final void zzi(Bundle bundle) {
        if (this.zzfka == null) {
            this.zzfka = bundle;
        } else if (bundle != null) {
            this.zzfka.putAll(bundle);
        }
    }

    public final ConnectionResult blockingConnect() {
        throw new UnsupportedOperationException();
    }

    public final void connect() {
        this.zzfkf = 2;
        this.zzfkd = false;
        this.zzfkc = null;
        this.zzfkb = null;
        this.zzfjv.connect();
        this.zzfjw.connect();
    }

    public final void disconnect() {
        this.zzfkc = null;
        this.zzfkb = null;
        this.zzfkf = 0;
        this.zzfjv.disconnect();
        this.zzfjw.disconnect();
        zzagk();
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append(str).append("authClient").println(CryptoUri.URI_SCHEME_DELIMETER);
        this.zzfjw.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
        printWriter.append(str).append("anonClient").println(CryptoUri.URI_SCHEME_DELIMETER);
        this.zzfjv.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
    }

    public final boolean isConnected() {
        boolean z = true;
        this.zzfke.lock();
        try {
            if (!(this.zzfjv.isConnected() && (this.zzfjw.isConnected() || zzagl() || this.zzfkf == 1))) {
                z = false;
            }
            this.zzfke.unlock();
            return z;
        } catch (Throwable th) {
            this.zzfke.unlock();
        }
    }

    public final boolean isConnecting() {
        this.zzfke.lock();
        try {
            boolean z = this.zzfkf == 2;
            this.zzfke.unlock();
            return z;
        } catch (Throwable th) {
            this.zzfke.unlock();
        }
    }

    public final boolean zza(zzcv com_google_android_gms_common_api_internal_zzcv) {
        this.zzfke.lock();
        try {
            if ((isConnecting() || isConnected()) && !this.zzfjw.isConnected()) {
                this.zzfjy.add(com_google_android_gms_common_api_internal_zzcv);
                if (this.zzfkf == 0) {
                    this.zzfkf = 1;
                }
                this.zzfkc = null;
                this.zzfjw.connect();
                return true;
            }
            this.zzfke.unlock();
            return false;
        } finally {
            this.zzfke.unlock();
        }
    }

    public final void zzafp() {
        this.zzfke.lock();
        try {
            boolean isConnecting = isConnecting();
            this.zzfjw.disconnect();
            this.zzfkc = new ConnectionResult(4);
            if (isConnecting) {
                new Handler(this.zzakg).post(new zzz(this));
            } else {
                zzagk();
            }
            this.zzfke.unlock();
        } catch (Throwable th) {
            this.zzfke.unlock();
        }
    }

    public final void zzagi() {
        this.zzfjv.zzagi();
        this.zzfjw.zzagi();
    }

    public final <A extends zzb, R extends Result, T extends zzm<R, A>> T zzd(T t) {
        if (!zzf((zzm) t)) {
            return this.zzfjv.zzd(t);
        }
        if (!zzagl()) {
            return this.zzfjw.zzd(t);
        }
        t.zzt(new Status(4, null, zzagm()));
        return t;
    }

    public final <A extends zzb, T extends zzm<? extends Result, A>> T zze(T t) {
        if (!zzf((zzm) t)) {
            return this.zzfjv.zze(t);
        }
        if (!zzagl()) {
            return this.zzfjw.zze(t);
        }
        t.zzt(new Status(4, null, zzagm()));
        return t;
    }
}
