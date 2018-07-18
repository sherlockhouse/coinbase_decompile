package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbp;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.FutureTask;

final class zzccv extends Thread {
    private /* synthetic */ zzccr zzism;
    private final Object zzisp = new Object();
    private final BlockingQueue<FutureTask<?>> zzisq;

    public zzccv(zzccr com_google_android_gms_internal_zzccr, String str, BlockingQueue<FutureTask<?>> blockingQueue) {
        this.zzism = com_google_android_gms_internal_zzccr;
        zzbp.zzu(str);
        zzbp.zzu(blockingQueue);
        this.zzisq = blockingQueue;
        setName(str);
    }

    private final void zza(InterruptedException interruptedException) {
        this.zzism.zzaul().zzayf().zzj(String.valueOf(getName()).concat(" was interrupted"), interruptedException);
    }

    public final void run() {
        Object obj = null;
        while (obj == null) {
            try {
                this.zzism.zzisi.acquire();
                obj = 1;
            } catch (InterruptedException e) {
                zza(e);
            }
        }
        while (true) {
            try {
                FutureTask futureTask = (FutureTask) this.zzisq.poll();
                if (futureTask != null) {
                    futureTask.run();
                } else {
                    synchronized (this.zzisp) {
                        if (this.zzisq.peek() == null && !this.zzism.zzisj) {
                            try {
                                this.zzisp.wait(30000);
                            } catch (InterruptedException e2) {
                                zza(e2);
                            }
                        }
                    }
                    synchronized (this.zzism.zzish) {
                        if (this.zzisq.peek() == null) {
                            break;
                        }
                    }
                }
            } catch (Throwable th) {
                synchronized (this.zzism.zzish) {
                    this.zzism.zzisi.release();
                    this.zzism.zzish.notifyAll();
                    if (this == this.zzism.zzisb) {
                        this.zzism.zzisb = null;
                    } else if (this == this.zzism.zzisc) {
                        this.zzism.zzisc = null;
                    } else {
                        this.zzism.zzaul().zzayd().log("Current scheduler thread is neither worker nor network");
                    }
                }
            }
        }
        synchronized (this.zzism.zzish) {
            this.zzism.zzisi.release();
            this.zzism.zzish.notifyAll();
            if (this == this.zzism.zzisb) {
                this.zzism.zzisb = null;
            } else if (this == this.zzism.zzisc) {
                this.zzism.zzisc = null;
            } else {
                this.zzism.zzaul().zzayd().log("Current scheduler thread is neither worker nor network");
            }
        }
    }

    public final void zzml() {
        synchronized (this.zzisp) {
            this.zzisp.notifyAll();
        }
    }
}
