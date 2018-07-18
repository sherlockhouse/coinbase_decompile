package com.google.android.gms.tasks;

import com.google.android.gms.common.internal.zzbp;
import java.util.concurrent.Executor;

final class zzn<TResult> extends Task<TResult> {
    private final Object mLock = new Object();
    private final zzl<TResult> zzkgj = new zzl();
    private boolean zzkgk;
    private TResult zzkgl;
    private Exception zzkgm;

    zzn() {
    }

    private final void zzbif() {
        zzbp.zza(!this.zzkgk, "Task is already complete");
    }

    private final void zzbig() {
        synchronized (this.mLock) {
            if (this.zzkgk) {
                this.zzkgj.zzb(this);
                return;
            }
        }
    }

    public final Task<TResult> addOnCompleteListener(Executor executor, OnCompleteListener<TResult> onCompleteListener) {
        this.zzkgj.zza(new zze(executor, onCompleteListener));
        zzbig();
        return this;
    }

    public final Exception getException() {
        Exception exception;
        synchronized (this.mLock) {
            exception = this.zzkgm;
        }
        return exception;
    }

    public final boolean isSuccessful() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzkgk && this.zzkgm == null;
        }
        return z;
    }

    public final void setException(Exception exception) {
        zzbp.zzb((Object) exception, (Object) "Exception must not be null");
        synchronized (this.mLock) {
            zzbif();
            this.zzkgk = true;
            this.zzkgm = exception;
        }
        this.zzkgj.zzb(this);
    }

    public final void setResult(TResult tResult) {
        synchronized (this.mLock) {
            zzbif();
            this.zzkgk = true;
            this.zzkgl = tResult;
        }
        this.zzkgj.zzb(this);
    }

    public final boolean trySetException(Exception exception) {
        boolean z = true;
        zzbp.zzb((Object) exception, (Object) "Exception must not be null");
        synchronized (this.mLock) {
            if (this.zzkgk) {
                z = false;
            } else {
                this.zzkgk = true;
                this.zzkgm = exception;
                this.zzkgj.zzb(this);
            }
        }
        return z;
    }

    public final boolean trySetResult(TResult tResult) {
        boolean z = true;
        synchronized (this.mLock) {
            if (this.zzkgk) {
                z = false;
            } else {
                this.zzkgk = true;
                this.zzkgl = tResult;
                this.zzkgj.zzb(this);
            }
        }
        return z;
    }
}
