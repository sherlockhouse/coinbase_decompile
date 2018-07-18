package com.google.android.gms.tasks;

import java.util.ArrayDeque;
import java.util.Queue;

final class zzl<TResult> {
    private final Object mLock = new Object();
    private Queue<zzk<TResult>> zzkgf;
    private boolean zzkgg;

    zzl() {
    }

    public final void zza(zzk<TResult> com_google_android_gms_tasks_zzk_TResult) {
        synchronized (this.mLock) {
            if (this.zzkgf == null) {
                this.zzkgf = new ArrayDeque();
            }
            this.zzkgf.add(com_google_android_gms_tasks_zzk_TResult);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(Task<TResult> task) {
        synchronized (this.mLock) {
            if (this.zzkgf == null || this.zzkgg) {
            } else {
                this.zzkgg = true;
            }
        }
    }
}
