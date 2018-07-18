package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbp;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

final class zzccu<V> extends FutureTask<V> implements Comparable<zzccu> {
    private final String zzisl;
    private /* synthetic */ zzccr zzism;
    private final long zzisn = zzccr.zzisk.getAndIncrement();
    private final boolean zziso;

    zzccu(zzccr com_google_android_gms_internal_zzccr, Runnable runnable, boolean z, String str) {
        this.zzism = com_google_android_gms_internal_zzccr;
        super(runnable, null);
        zzbp.zzu(str);
        this.zzisl = str;
        this.zziso = false;
        if (this.zzisn == Long.MAX_VALUE) {
            com_google_android_gms_internal_zzccr.zzaul().zzayd().log("Tasks index overflow");
        }
    }

    zzccu(zzccr com_google_android_gms_internal_zzccr, Callable<V> callable, boolean z, String str) {
        this.zzism = com_google_android_gms_internal_zzccr;
        super(callable);
        zzbp.zzu(str);
        this.zzisl = str;
        this.zziso = z;
        if (this.zzisn == Long.MAX_VALUE) {
            com_google_android_gms_internal_zzccr.zzaul().zzayd().log("Tasks index overflow");
        }
    }

    public final /* synthetic */ int compareTo(Object obj) {
        zzccu com_google_android_gms_internal_zzccu = (zzccu) obj;
        if (this.zziso != com_google_android_gms_internal_zzccu.zziso) {
            return this.zziso ? -1 : 1;
        } else {
            if (this.zzisn < com_google_android_gms_internal_zzccu.zzisn) {
                return -1;
            }
            if (this.zzisn > com_google_android_gms_internal_zzccu.zzisn) {
                return 1;
            }
            this.zzism.zzaul().zzaye().zzj("Two tasks share the same index. index", Long.valueOf(this.zzisn));
            return 0;
        }
    }

    protected final void setException(Throwable th) {
        this.zzism.zzaul().zzayd().zzj(this.zzisl, th);
        if (th instanceof zzccs) {
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
        }
        super.setException(th);
    }
}
