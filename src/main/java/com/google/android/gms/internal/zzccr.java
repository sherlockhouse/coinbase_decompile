package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.util.zzd;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public final class zzccr extends zzcdu {
    private static final AtomicLong zzisk = new AtomicLong(Long.MIN_VALUE);
    private ExecutorService zzisa;
    private zzccv zzisb;
    private zzccv zzisc;
    private final PriorityBlockingQueue<FutureTask<?>> zzisd = new PriorityBlockingQueue();
    private final BlockingQueue<FutureTask<?>> zzise = new LinkedBlockingQueue();
    private final UncaughtExceptionHandler zzisf = new zzcct(this, "Thread death: Uncaught exception on worker thread");
    private final UncaughtExceptionHandler zzisg = new zzcct(this, "Thread death: Uncaught exception on network thread");
    private final Object zzish = new Object();
    private final Semaphore zzisi = new Semaphore(2);
    private volatile boolean zzisj;

    zzccr(zzccw com_google_android_gms_internal_zzccw) {
        super(com_google_android_gms_internal_zzccw);
    }

    private final void zza(zzccu<?> com_google_android_gms_internal_zzccu_) {
        synchronized (this.zzish) {
            this.zzisd.add(com_google_android_gms_internal_zzccu_);
            if (this.zzisb == null) {
                this.zzisb = new zzccv(this, "Measurement Worker", this.zzisd);
                this.zzisb.setUncaughtExceptionHandler(this.zzisf);
                this.zzisb.start();
            } else {
                this.zzisb.zzml();
            }
        }
    }

    public static boolean zzaq() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final /* bridge */ /* synthetic */ void zzatu() {
        super.zzatu();
    }

    public final /* bridge */ /* synthetic */ void zzatv() {
        super.zzatv();
    }

    public final void zzatw() {
        if (Thread.currentThread() != this.zzisc) {
            throw new IllegalStateException("Call expected from network thread");
        }
    }

    public final /* bridge */ /* synthetic */ zzcan zzatx() {
        return super.zzatx();
    }

    public final /* bridge */ /* synthetic */ zzcau zzaty() {
        return super.zzaty();
    }

    public final /* bridge */ /* synthetic */ zzcdw zzatz() {
        return super.zzatz();
    }

    public final /* bridge */ /* synthetic */ zzcbr zzaua() {
        return super.zzaua();
    }

    public final /* bridge */ /* synthetic */ zzcbe zzaub() {
        return super.zzaub();
    }

    public final /* bridge */ /* synthetic */ zzceo zzauc() {
        return super.zzauc();
    }

    public final /* bridge */ /* synthetic */ zzcek zzaud() {
        return super.zzaud();
    }

    public final /* bridge */ /* synthetic */ zzcbs zzaue() {
        return super.zzaue();
    }

    public final /* bridge */ /* synthetic */ zzcay zzauf() {
        return super.zzauf();
    }

    public final /* bridge */ /* synthetic */ zzcbu zzaug() {
        return super.zzaug();
    }

    public final /* bridge */ /* synthetic */ zzcfw zzauh() {
        return super.zzauh();
    }

    public final /* bridge */ /* synthetic */ zzccq zzaui() {
        return super.zzaui();
    }

    public final /* bridge */ /* synthetic */ zzcfl zzauj() {
        return super.zzauj();
    }

    public final /* bridge */ /* synthetic */ zzccr zzauk() {
        return super.zzauk();
    }

    public final /* bridge */ /* synthetic */ zzcbw zzaul() {
        return super.zzaul();
    }

    public final /* bridge */ /* synthetic */ zzcch zzaum() {
        return super.zzaum();
    }

    public final /* bridge */ /* synthetic */ zzcax zzaun() {
        return super.zzaun();
    }

    public final boolean zzays() {
        return Thread.currentThread() == this.zzisb;
    }

    final ExecutorService zzayt() {
        ExecutorService executorService;
        synchronized (this.zzish) {
            if (this.zzisa == null) {
                this.zzisa = new ThreadPoolExecutor(0, 1, 30, TimeUnit.SECONDS, new ArrayBlockingQueue(100));
            }
            executorService = this.zzisa;
        }
        return executorService;
    }

    public final <V> Future<V> zzd(Callable<V> callable) throws IllegalStateException {
        zzwk();
        zzbp.zzu(callable);
        zzccu com_google_android_gms_internal_zzccu = new zzccu(this, (Callable) callable, false, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzisb) {
            if (!this.zzisd.isEmpty()) {
                zzaul().zzayf().log("Callable skipped the worker queue.");
            }
            com_google_android_gms_internal_zzccu.run();
        } else {
            zza(com_google_android_gms_internal_zzccu);
        }
        return com_google_android_gms_internal_zzccu;
    }

    public final <V> Future<V> zze(Callable<V> callable) throws IllegalStateException {
        zzwk();
        zzbp.zzu(callable);
        zzccu com_google_android_gms_internal_zzccu = new zzccu(this, (Callable) callable, true, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzisb) {
            com_google_android_gms_internal_zzccu.run();
        } else {
            zza(com_google_android_gms_internal_zzccu);
        }
        return com_google_android_gms_internal_zzccu;
    }

    public final void zzg(Runnable runnable) throws IllegalStateException {
        zzwk();
        zzbp.zzu(runnable);
        zza(new zzccu(this, runnable, false, "Task exception on worker thread"));
    }

    public final void zzh(Runnable runnable) throws IllegalStateException {
        zzwk();
        zzbp.zzu(runnable);
        zzccu com_google_android_gms_internal_zzccu = new zzccu(this, runnable, false, "Task exception on network thread");
        synchronized (this.zzish) {
            this.zzise.add(com_google_android_gms_internal_zzccu);
            if (this.zzisc == null) {
                this.zzisc = new zzccv(this, "Measurement Network", this.zzise);
                this.zzisc.setUncaughtExceptionHandler(this.zzisg);
                this.zzisc.start();
            } else {
                this.zzisc.zzml();
            }
        }
    }

    public final void zzuj() {
        if (Thread.currentThread() != this.zzisb) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    protected final void zzuk() {
    }

    public final /* bridge */ /* synthetic */ zzd zzvx() {
        return super.zzvx();
    }
}
