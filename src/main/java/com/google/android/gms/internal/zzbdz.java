package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbp;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzbdz implements ThreadFactory {
    private final int mPriority;
    private final String zzfzn;
    private final AtomicInteger zzfzo;
    private final ThreadFactory zzfzp;

    public zzbdz(String str) {
        this(str, 0);
    }

    private zzbdz(String str, int i) {
        this.zzfzo = new AtomicInteger();
        this.zzfzp = Executors.defaultThreadFactory();
        this.zzfzn = (String) zzbp.zzb((Object) str, (Object) "Name must not be null");
        this.mPriority = 0;
    }

    public final Thread newThread(Runnable runnable) {
        Thread newThread = this.zzfzp.newThread(new zzbea(runnable, 0));
        String str = this.zzfzn;
        newThread.setName(new StringBuilder(String.valueOf(str).length() + 13).append(str).append("[").append(this.zzfzo.getAndIncrement()).append("]").toString());
        return newThread;
    }
}
