package com.google.android.gms.common.api.internal;

import com.google.android.gms.internal.zzbdz;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class zzbo {
    private static final ExecutorService zzfnj = Executors.newFixedThreadPool(2, new zzbdz("GAC_Executor"));

    public static ExecutorService zzahn() {
        return zzfnj;
    }
}
