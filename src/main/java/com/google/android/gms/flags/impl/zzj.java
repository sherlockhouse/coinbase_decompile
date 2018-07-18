package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.internal.zzbvp;

public final class zzj {
    private static SharedPreferences zzhbi = null;

    public static SharedPreferences zzcy(Context context) throws Exception {
        SharedPreferences sharedPreferences;
        synchronized (SharedPreferences.class) {
            if (zzhbi == null) {
                zzhbi = (SharedPreferences) zzbvp.zza(new zzk(context));
            }
            sharedPreferences = zzhbi;
        }
        return sharedPreferences;
    }
}
