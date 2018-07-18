package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.internal.zzbed;

public final class zzbe {
    private static Object zzaqd = new Object();
    private static boolean zzclj;
    private static String zzfvo;
    private static int zzfvp;

    public static String zzcf(Context context) {
        zzch(context);
        return zzfvo;
    }

    public static int zzcg(Context context) {
        zzch(context);
        return zzfvp;
    }

    private static void zzch(Context context) {
        synchronized (zzaqd) {
            if (zzclj) {
                return;
            }
            zzclj = true;
            try {
                Bundle bundle = zzbed.zzcr(context).getApplicationInfo(context.getPackageName(), 128).metaData;
                if (bundle == null) {
                    return;
                }
                zzfvo = bundle.getString("com.google.app.id");
                zzfvp = bundle.getInt("com.google.android.gms.version");
            } catch (Throwable e) {
                Log.wtf("MetadataValueReader", "This should never happen.", e);
            }
        }
    }
}
