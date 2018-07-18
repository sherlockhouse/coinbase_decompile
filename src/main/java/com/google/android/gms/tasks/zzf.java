package com.google.android.gms.tasks;

final class zzf implements Runnable {
    private /* synthetic */ Task zzkfw;
    private /* synthetic */ zze zzkga;

    zzf(zze com_google_android_gms_tasks_zze, Task task) {
        this.zzkga = com_google_android_gms_tasks_zze;
        this.zzkfw = task;
    }

    public final void run() {
        synchronized (this.zzkga.mLock) {
            if (this.zzkga.zzkfz != null) {
                this.zzkga.zzkfz.onComplete(this.zzkfw);
            }
        }
    }
}
