package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzad;
import com.google.android.gms.common.internal.zzae;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.internal.zzbcr;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.internal.zzcpt;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;

public final class zzbd extends GoogleApiClient implements zzce {
    private final Context mContext;
    private final Looper zzakg;
    private final int zzfhj;
    private final GoogleApiAvailability zzfhl;
    private zza<? extends zzcps, zzcpt> zzfhm;
    private boolean zzfhp;
    private final Lock zzfke;
    private zzq zzfkj;
    private Map<Api<?>, Boolean> zzfkm;
    final Queue<zzm<?, ?>> zzfks = new LinkedList();
    private final zzad zzfmg;
    private zzcd zzfmh = null;
    private volatile boolean zzfmi;
    private long zzfmj = 120000;
    private long zzfmk = 5000;
    private final zzbi zzfml;
    private zzby zzfmm;
    final Map<zzc<?>, zze> zzfmn;
    Set<Scope> zzfmo = new HashSet();
    private final zzcn zzfmp = new zzcn();
    private final ArrayList<zzw> zzfmq;
    private Integer zzfmr = null;
    Set<zzdg> zzfms = null;
    final zzdj zzfmt;
    private final zzae zzfmu = new zzbe(this);

    public zzbd(Context context, Lock lock, Looper looper, zzq com_google_android_gms_common_internal_zzq, GoogleApiAvailability googleApiAvailability, zza<? extends zzcps, zzcpt> com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcps__com_google_android_gms_internal_zzcpt, Map<Api<?>, Boolean> map, List<ConnectionCallbacks> list, List<OnConnectionFailedListener> list2, Map<zzc<?>, zze> map2, int i, int i2, ArrayList<zzw> arrayList, boolean z) {
        this.mContext = context;
        this.zzfke = lock;
        this.zzfhp = false;
        this.zzfmg = new zzad(looper, this.zzfmu);
        this.zzakg = looper;
        this.zzfml = new zzbi(this, looper);
        this.zzfhl = googleApiAvailability;
        this.zzfhj = i;
        if (this.zzfhj >= 0) {
            this.zzfmr = Integer.valueOf(i2);
        }
        this.zzfkm = map;
        this.zzfmn = map2;
        this.zzfmq = arrayList;
        this.zzfmt = new zzdj(this.zzfmn);
        for (ConnectionCallbacks registerConnectionCallbacks : list) {
            this.zzfmg.registerConnectionCallbacks(registerConnectionCallbacks);
        }
        for (OnConnectionFailedListener registerConnectionFailedListener : list2) {
            this.zzfmg.registerConnectionFailedListener(registerConnectionFailedListener);
        }
        this.zzfkj = com_google_android_gms_common_internal_zzq;
        this.zzfhm = com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcps__com_google_android_gms_internal_zzcpt;
    }

    private final void resume() {
        this.zzfke.lock();
        try {
            if (this.zzfmi) {
                zzahg();
            }
            this.zzfke.unlock();
        } catch (Throwable th) {
            this.zzfke.unlock();
        }
    }

    public static int zza(Iterable<zze> iterable, boolean z) {
        int i = 0;
        int i2 = 0;
        for (zze com_google_android_gms_common_api_Api_zze : iterable) {
            if (com_google_android_gms_common_api_Api_zze.zzaac()) {
                i2 = 1;
            }
            i = com_google_android_gms_common_api_Api_zze.zzaal() ? 1 : i;
        }
        return i2 != 0 ? (i == 0 || !z) ? 1 : 2 : 3;
    }

    private final void zza(GoogleApiClient googleApiClient, zzda com_google_android_gms_common_api_internal_zzda, boolean z) {
        zzbcr.zzfwh.zzd(googleApiClient).setResultCallback(new zzbh(this, com_google_android_gms_common_api_internal_zzda, z, googleApiClient));
    }

    private final void zzahg() {
        this.zzfmg.zzakf();
        this.zzfmh.connect();
    }

    private final void zzahh() {
        this.zzfke.lock();
        try {
            if (zzahi()) {
                zzahg();
            }
            this.zzfke.unlock();
        } catch (Throwable th) {
            this.zzfke.unlock();
        }
    }

    private final void zzbt(int i) {
        if (this.zzfmr == null) {
            this.zzfmr = Integer.valueOf(i);
        } else if (this.zzfmr.intValue() != i) {
            String zzbu = zzbu(i);
            String zzbu2 = zzbu(this.zzfmr.intValue());
            throw new IllegalStateException(new StringBuilder((String.valueOf(zzbu).length() + 51) + String.valueOf(zzbu2).length()).append("Cannot use sign-in mode: ").append(zzbu).append(". Mode was already set to ").append(zzbu2).toString());
        }
        if (this.zzfmh == null) {
            boolean z = false;
            boolean z2 = false;
            for (zze com_google_android_gms_common_api_Api_zze : this.zzfmn.values()) {
                if (com_google_android_gms_common_api_Api_zze.zzaac()) {
                    z2 = true;
                }
                z = com_google_android_gms_common_api_Api_zze.zzaal() ? true : z;
            }
            switch (this.zzfmr.intValue()) {
                case 1:
                    if (!z2) {
                        throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
                    } else if (z) {
                        throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead.");
                    }
                    break;
                case 2:
                    if (z2) {
                        if (this.zzfhp) {
                            this.zzfmh = new zzad(this.mContext, this.zzfke, this.zzakg, this.zzfhl, this.zzfmn, this.zzfkj, this.zzfkm, this.zzfhm, this.zzfmq, this, true);
                            return;
                        } else {
                            this.zzfmh = zzy.zza(this.mContext, this, this.zzfke, this.zzakg, this.zzfhl, this.zzfmn, this.zzfkj, this.zzfkm, this.zzfhm, this.zzfmq);
                            return;
                        }
                    }
                    break;
            }
            if (!this.zzfhp || z) {
                this.zzfmh = new zzbl(this.mContext, this, this.zzfke, this.zzakg, this.zzfhl, this.zzfmn, this.zzfkj, this.zzfkm, this.zzfhm, this.zzfmq, this);
            } else {
                this.zzfmh = new zzad(this.mContext, this.zzfke, this.zzakg, this.zzfhl, this.zzfmn, this.zzfkj, this.zzfkm, this.zzfhm, this.zzfmq, this, false);
            }
        }
    }

    private static String zzbu(int i) {
        switch (i) {
            case 1:
                return "SIGN_IN_MODE_REQUIRED";
            case 2:
                return "SIGN_IN_MODE_OPTIONAL";
            case 3:
                return "SIGN_IN_MODE_NONE";
            default:
                return "UNKNOWN";
        }
    }

    public final ConnectionResult blockingConnect() {
        boolean z = true;
        zzbp.zza(Looper.myLooper() != Looper.getMainLooper(), "blockingConnect must not be called on the UI thread");
        this.zzfke.lock();
        try {
            if (this.zzfhj >= 0) {
                if (this.zzfmr == null) {
                    z = false;
                }
                zzbp.zza(z, "Sign-in mode should have been set explicitly by auto-manage.");
            } else if (this.zzfmr == null) {
                this.zzfmr = Integer.valueOf(zza(this.zzfmn.values(), false));
            } else if (this.zzfmr.intValue() == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            zzbt(this.zzfmr.intValue());
            this.zzfmg.zzakf();
            ConnectionResult blockingConnect = this.zzfmh.blockingConnect();
            return blockingConnect;
        } finally {
            this.zzfke.unlock();
        }
    }

    public final PendingResult<Status> clearDefaultAccountAndReconnect() {
        zzbp.zza(isConnected(), "GoogleApiClient is not connected yet.");
        zzbp.zza(this.zzfmr.intValue() != 2, "Cannot use clearDefaultAccountAndReconnect with GOOGLE_SIGN_IN_API");
        PendingResult com_google_android_gms_common_api_internal_zzda = new zzda(this);
        if (this.zzfmn.containsKey(zzbcr.zzdwp)) {
            zza(this, com_google_android_gms_common_api_internal_zzda, false);
        } else {
            AtomicReference atomicReference = new AtomicReference();
            GoogleApiClient build = new Builder(this.mContext).addApi(zzbcr.API).addConnectionCallbacks(new zzbf(this, atomicReference, com_google_android_gms_common_api_internal_zzda)).addOnConnectionFailedListener(new zzbg(this, com_google_android_gms_common_api_internal_zzda)).setHandler(this.zzfml).build();
            atomicReference.set(build);
            build.connect();
        }
        return com_google_android_gms_common_api_internal_zzda;
    }

    public final void connect() {
        boolean z = false;
        this.zzfke.lock();
        try {
            if (this.zzfhj >= 0) {
                if (this.zzfmr != null) {
                    z = true;
                }
                zzbp.zza(z, "Sign-in mode should have been set explicitly by auto-manage.");
            } else if (this.zzfmr == null) {
                this.zzfmr = Integer.valueOf(zza(this.zzfmn.values(), false));
            } else if (this.zzfmr.intValue() == 2) {
                throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            connect(this.zzfmr.intValue());
        } finally {
            this.zzfke.unlock();
        }
    }

    public final void connect(int i) {
        boolean z = true;
        this.zzfke.lock();
        if (!(i == 3 || i == 1 || i == 2)) {
            z = false;
        }
        try {
            zzbp.zzb(z, "Illegal sign-in mode: " + i);
            zzbt(i);
            zzahg();
        } finally {
            this.zzfke.unlock();
        }
    }

    public final void disconnect() {
        this.zzfke.lock();
        try {
            this.zzfmt.release();
            if (this.zzfmh != null) {
                this.zzfmh.disconnect();
            }
            this.zzfmp.release();
            for (zzm com_google_android_gms_common_api_internal_zzm : this.zzfks) {
                com_google_android_gms_common_api_internal_zzm.zza(null);
                com_google_android_gms_common_api_internal_zzm.cancel();
            }
            this.zzfks.clear();
            if (this.zzfmh != null) {
                zzahi();
                this.zzfmg.zzake();
                this.zzfke.unlock();
            }
        } finally {
            this.zzfke.unlock();
        }
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append(str).append("mContext=").println(this.mContext);
        printWriter.append(str).append("mResuming=").print(this.zzfmi);
        printWriter.append(" mWorkQueue.size()=").print(this.zzfks.size());
        printWriter.append(" mUnconsumedApiCalls.size()=").println(this.zzfmt.zzfps.size());
        if (this.zzfmh != null) {
            this.zzfmh.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final Looper getLooper() {
        return this.zzakg;
    }

    public final boolean isConnected() {
        return this.zzfmh != null && this.zzfmh.isConnected();
    }

    public final void reconnect() {
        disconnect();
        connect();
    }

    public final void registerConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
        this.zzfmg.registerConnectionFailedListener(onConnectionFailedListener);
    }

    public final void unregisterConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
        this.zzfmg.unregisterConnectionFailedListener(onConnectionFailedListener);
    }

    public final void zza(zzdg com_google_android_gms_common_api_internal_zzdg) {
        this.zzfke.lock();
        try {
            if (this.zzfms == null) {
                this.zzfms = new HashSet();
            }
            this.zzfms.add(com_google_android_gms_common_api_internal_zzdg);
        } finally {
            this.zzfke.unlock();
        }
    }

    public final boolean zza(zzcv com_google_android_gms_common_api_internal_zzcv) {
        return this.zzfmh != null && this.zzfmh.zza(com_google_android_gms_common_api_internal_zzcv);
    }

    public final void zzafp() {
        if (this.zzfmh != null) {
            this.zzfmh.zzafp();
        }
    }

    final boolean zzahi() {
        if (!this.zzfmi) {
            return false;
        }
        this.zzfmi = false;
        this.zzfml.removeMessages(2);
        this.zzfml.removeMessages(1);
        if (this.zzfmm != null) {
            this.zzfmm.unregister();
            this.zzfmm = null;
        }
        return true;
    }

    final boolean zzahj() {
        boolean z = false;
        this.zzfke.lock();
        try {
            if (this.zzfms != null) {
                if (!this.zzfms.isEmpty()) {
                    z = true;
                }
                this.zzfke.unlock();
            }
            return z;
        } finally {
            this.zzfke.unlock();
        }
    }

    final String zzahk() {
        Writer stringWriter = new StringWriter();
        dump("", null, new PrintWriter(stringWriter), null);
        return stringWriter.toString();
    }

    public final void zzb(zzdg com_google_android_gms_common_api_internal_zzdg) {
        this.zzfke.lock();
        try {
            if (this.zzfms == null) {
                Log.wtf("GoogleApiClientImpl", "Attempted to remove pending transform when no transforms are registered.", new Exception());
            } else if (!this.zzfms.remove(com_google_android_gms_common_api_internal_zzdg)) {
                Log.wtf("GoogleApiClientImpl", "Failed to remove pending transform - this may lead to memory leaks!", new Exception());
            } else if (!zzahj()) {
                this.zzfmh.zzagi();
            }
            this.zzfke.unlock();
        } catch (Throwable th) {
            this.zzfke.unlock();
        }
    }

    public final void zzc(ConnectionResult connectionResult) {
        if (!com.google.android.gms.common.zze.zze(this.mContext, connectionResult.getErrorCode())) {
            zzahi();
        }
        if (!this.zzfmi) {
            this.zzfmg.zzk(connectionResult);
            this.zzfmg.zzake();
        }
    }

    public final <A extends zzb, R extends Result, T extends zzm<R, A>> T zzd(T t) {
        zzbp.zzb(t.zzafe() != null, (Object) "This task can not be enqueued (it's probably a Batch or malformed)");
        boolean containsKey = this.zzfmn.containsKey(t.zzafe());
        String name = t.zzafj() != null ? t.zzafj().getName() : "the API";
        zzbp.zzb(containsKey, new StringBuilder(String.valueOf(name).length() + 65).append("GoogleApiClient is not configured to use ").append(name).append(" required for this call.").toString());
        this.zzfke.lock();
        try {
            if (this.zzfmh == null) {
                this.zzfks.add(t);
            } else {
                t = this.zzfmh.zzd(t);
                this.zzfke.unlock();
            }
            return t;
        } finally {
            this.zzfke.unlock();
        }
    }

    public final <A extends zzb, T extends zzm<? extends Result, A>> T zze(T t) {
        zzbp.zzb(t.zzafe() != null, (Object) "This task can not be executed (it's probably a Batch or malformed)");
        boolean containsKey = this.zzfmn.containsKey(t.zzafe());
        String name = t.zzafj() != null ? t.zzafj().getName() : "the API";
        zzbp.zzb(containsKey, new StringBuilder(String.valueOf(name).length() + 65).append("GoogleApiClient is not configured to use ").append(name).append(" required for this call.").toString());
        this.zzfke.lock();
        try {
            if (this.zzfmh == null) {
                throw new IllegalStateException("GoogleApiClient is not connected yet.");
            }
            if (this.zzfmi) {
                this.zzfks.add(t);
                while (!this.zzfks.isEmpty()) {
                    zzm com_google_android_gms_common_api_internal_zzm = (zzm) this.zzfks.remove();
                    this.zzfmt.zzb(com_google_android_gms_common_api_internal_zzm);
                    com_google_android_gms_common_api_internal_zzm.zzt(Status.zzfhx);
                }
            } else {
                t = this.zzfmh.zze(t);
                this.zzfke.unlock();
            }
            return t;
        } finally {
            this.zzfke.unlock();
        }
    }

    public final void zzf(int i, boolean z) {
        if (!(i != 1 || z || this.zzfmi)) {
            this.zzfmi = true;
            if (this.zzfmm == null) {
                this.zzfmm = GoogleApiAvailability.zza(this.mContext.getApplicationContext(), new zzbj(this));
            }
            this.zzfml.sendMessageDelayed(this.zzfml.obtainMessage(1), this.zzfmj);
            this.zzfml.sendMessageDelayed(this.zzfml.obtainMessage(2), this.zzfmk);
        }
        this.zzfmt.zzaiq();
        this.zzfmg.zzce(i);
        this.zzfmg.zzake();
        if (i == 2) {
            zzahg();
        }
    }

    public final void zzj(Bundle bundle) {
        while (!this.zzfks.isEmpty()) {
            zze((zzm) this.zzfks.remove());
        }
        this.zzfmg.zzk(bundle);
    }
}
