package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

final class zze<TResult> implements zzk<TResult> {
    private final Object mLock = new Object();
    private final Executor zzjqr;
    private OnCompleteListener<TResult> zzkfz;

    public zze(Executor executor, OnCompleteListener<TResult> onCompleteListener) {
        this.zzjqr = executor;
        this.zzkfz = onCompleteListener;
    }

    public final void onComplete(Task<TResult> task) {
        synchronized (this.mLock) {
            if (this.zzkfz == null) {
                return;
            }
            this.zzjqr.execute(new zzf(this, task));
        }
    }
}
