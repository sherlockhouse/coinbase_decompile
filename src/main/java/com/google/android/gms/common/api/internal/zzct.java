package com.google.android.gms.common.api.internal;

import com.google.android.gms.internal.zzbdz;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class zzct {
    private static final ExecutorService zzfnj = new ThreadPoolExecutor(0, 4, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new zzbdz("GAC_Transform"));

    public static ExecutorService zzahn() {
        return zzfnj;
    }
}
