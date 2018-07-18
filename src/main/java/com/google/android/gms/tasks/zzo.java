package com.google.android.gms.tasks;

import java.util.concurrent.Callable;

final class zzo implements Runnable {
    private /* synthetic */ Callable zzdbx;
    private /* synthetic */ zzn zzkgn;

    zzo(zzn com_google_android_gms_tasks_zzn, Callable callable) {
        this.zzkgn = com_google_android_gms_tasks_zzn;
        this.zzdbx = callable;
    }

    public final void run() {
        try {
            this.zzkgn.setResult(this.zzdbx.call());
        } catch (Exception e) {
            this.zzkgn.setException(e);
        }
    }
}
