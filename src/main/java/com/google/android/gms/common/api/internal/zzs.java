package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResult.zza;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzap;
import com.google.android.gms.common.internal.zzbp;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public abstract class zzs<R extends Result> extends PendingResult<R> {
    static final ThreadLocal<Boolean> zzfje = new zzt();
    private Status mStatus;
    private boolean zzaj;
    private final CountDownLatch zzaof;
    private R zzfhr;
    private final Object zzfjf;
    private zzu<R> zzfjg;
    private WeakReference<GoogleApiClient> zzfjh;
    private final ArrayList<zza> zzfji;
    private ResultCallback<? super R> zzfjj;
    private final AtomicReference<zzdm> zzfjk;
    private zzv zzfjl;
    private volatile boolean zzfjm;
    private boolean zzfjn;
    private zzap zzfjo;
    private volatile zzdg<R> zzfjp;
    private boolean zzfjq;

    @Deprecated
    zzs() {
        this.zzfjf = new Object();
        this.zzaof = new CountDownLatch(1);
        this.zzfji = new ArrayList();
        this.zzfjk = new AtomicReference();
        this.zzfjq = false;
        this.zzfjg = new zzu(Looper.getMainLooper());
        this.zzfjh = new WeakReference(null);
    }

    protected zzs(GoogleApiClient googleApiClient) {
        this.zzfjf = new Object();
        this.zzaof = new CountDownLatch(1);
        this.zzfji = new ArrayList();
        this.zzfjk = new AtomicReference();
        this.zzfjq = false;
        this.zzfjg = new zzu(googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
        this.zzfjh = new WeakReference(googleApiClient);
    }

    private final R get() {
        R r;
        boolean z = true;
        synchronized (this.zzfjf) {
            if (this.zzfjm) {
                z = false;
            }
            zzbp.zza(z, "Result has already been consumed.");
            zzbp.zza(isReady(), "Result is not ready.");
            r = this.zzfhr;
            this.zzfhr = null;
            this.zzfjj = null;
            this.zzfjm = true;
        }
        zzdm com_google_android_gms_common_api_internal_zzdm = (zzdm) this.zzfjk.getAndSet(null);
        if (com_google_android_gms_common_api_internal_zzdm != null) {
            com_google_android_gms_common_api_internal_zzdm.zzc(this);
        }
        return r;
    }

    private final void zzc(R r) {
        this.zzfhr = r;
        this.zzfjo = null;
        this.zzaof.countDown();
        this.mStatus = this.zzfhr.getStatus();
        if (this.zzaj) {
            this.zzfjj = null;
        } else if (this.zzfjj != null) {
            this.zzfjg.removeMessages(2);
            this.zzfjg.zza(this.zzfjj, get());
        } else if (this.zzfhr instanceof Releasable) {
            this.zzfjl = new zzv();
        }
        ArrayList arrayList = this.zzfji;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((zza) obj).zzq(this.mStatus);
        }
        this.zzfji.clear();
    }

    public static void zzd(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (Throwable e) {
                String valueOf = String.valueOf(result);
                Log.w("BasePendingResult", new StringBuilder(String.valueOf(valueOf).length() + 18).append("Unable to release ").append(valueOf).toString(), e);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void cancel() {
        synchronized (this.zzfjf) {
            if (this.zzaj || this.zzfjm) {
            } else {
                if (this.zzfjo != null) {
                    try {
                        this.zzfjo.cancel();
                    } catch (RemoteException e) {
                    }
                }
                zzd(this.zzfhr);
                this.zzaj = true;
                zzc(zzb(Status.zzfhz));
            }
        }
    }

    public boolean isCanceled() {
        boolean z;
        synchronized (this.zzfjf) {
            z = this.zzaj;
        }
        return z;
    }

    public final boolean isReady() {
        return this.zzaof.getCount() == 0;
    }

    public final void setResult(R r) {
        boolean z = true;
        synchronized (this.zzfjf) {
            if (this.zzfjn || this.zzaj) {
                zzd(r);
                return;
            }
            if (isReady()) {
            }
            zzbp.zza(!isReady(), "Results have already been set");
            if (this.zzfjm) {
                z = false;
            }
            zzbp.zza(z, "Result has already been consumed");
            zzc(r);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setResultCallback(ResultCallback<? super R> resultCallback) {
        boolean z = true;
        synchronized (this.zzfjf) {
            if (resultCallback == null) {
                this.zzfjj = null;
                return;
            }
            zzbp.zza(!this.zzfjm, "Result has already been consumed.");
            if (this.zzfjp != null) {
                z = false;
            }
            zzbp.zza(z, "Cannot set callbacks if then() has been called.");
            if (isCanceled()) {
            } else if (isReady()) {
                this.zzfjg.zza(resultCallback, get());
            } else {
                this.zzfjj = resultCallback;
            }
        }
    }

    public final void zza(zza com_google_android_gms_common_api_PendingResult_zza) {
        zzbp.zzb(com_google_android_gms_common_api_PendingResult_zza != null, (Object) "Callback cannot be null.");
        synchronized (this.zzfjf) {
            if (isReady()) {
                com_google_android_gms_common_api_PendingResult_zza.zzq(this.mStatus);
            } else {
                this.zzfji.add(com_google_android_gms_common_api_PendingResult_zza);
            }
        }
    }

    public final void zza(zzdm com_google_android_gms_common_api_internal_zzdm) {
        this.zzfjk.set(com_google_android_gms_common_api_internal_zzdm);
    }

    public final Integer zzafs() {
        return null;
    }

    public final boolean zzagf() {
        boolean isCanceled;
        synchronized (this.zzfjf) {
            if (((GoogleApiClient) this.zzfjh.get()) == null || !this.zzfjq) {
                cancel();
            }
            isCanceled = isCanceled();
        }
        return isCanceled;
    }

    public final void zzagg() {
        boolean z = this.zzfjq || ((Boolean) zzfje.get()).booleanValue();
        this.zzfjq = z;
    }

    protected abstract R zzb(Status status);

    public final void zzu(Status status) {
        synchronized (this.zzfjf) {
            if (!isReady()) {
                setResult(zzb(status));
                this.zzfjn = true;
            }
        }
    }
}
