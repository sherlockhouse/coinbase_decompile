package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.util.zzq;

public final class zzbeb {
    private static Context zzfzq;
    private static Boolean zzfzr;

    public static synchronized boolean zzcp(Context context) {
        boolean booleanValue;
        synchronized (zzbeb.class) {
            Context applicationContext = context.getApplicationContext();
            if (zzfzq == null || zzfzr == null || zzfzq != applicationContext) {
                zzfzr = null;
                if (zzq.isAtLeastO()) {
                    zzfzr = Boolean.valueOf(applicationContext.getPackageManager().isInstantApp());
                } else {
                    try {
                        context.getClassLoader().loadClass("com.google.android.instantapps.supervisor.InstantAppsRuntime");
                        zzfzr = Boolean.valueOf(true);
                    } catch (ClassNotFoundException e) {
                        zzfzr = Boolean.valueOf(false);
                    }
                }
                zzfzq = applicationContext;
                booleanValue = zzfzr.booleanValue();
            } else {
                booleanValue = zzfzr.booleanValue();
            }
        }
        return booleanValue;
    }
}
