package com.google.android.gms.internal;

import android.content.Context;

public final class zzbed {
    private static zzbed zzfzt = new zzbed();
    private zzbec zzfzs = null;

    private final synchronized zzbec zzcq(Context context) {
        if (this.zzfzs == null) {
            if (context.getApplicationContext() != null) {
                context = context.getApplicationContext();
            }
            this.zzfzs = new zzbec(context);
        }
        return this.zzfzs;
    }

    public static zzbec zzcr(Context context) {
        return zzfzt.zzcq(context);
    }
}
