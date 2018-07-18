package com.google.android.gms.common.util;

import android.os.SystemClock;

public final class zzh implements zzd {
    private static zzh zzfyr = new zzh();

    private zzh() {
    }

    public static zzd zzalc() {
        return zzfyr;
    }

    public final long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public final long elapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }

    public final long nanoTime() {
        return System.nanoTime();
    }
}
