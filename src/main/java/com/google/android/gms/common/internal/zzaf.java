package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.ServiceConnection;

public abstract class zzaf {
    private static final Object zzfut = new Object();
    private static zzaf zzfuu;

    public static zzaf zzce(Context context) {
        synchronized (zzfut) {
            if (zzfuu == null) {
                zzfuu = new zzah(context.getApplicationContext());
            }
        }
        return zzfuu;
    }

    public final void zza(String str, String str2, int i, ServiceConnection serviceConnection, String str3) {
        zzb(new zzag(str, str2, i), serviceConnection, str3);
    }

    protected abstract boolean zza(zzag com_google_android_gms_common_internal_zzag, ServiceConnection serviceConnection, String str);

    protected abstract void zzb(zzag com_google_android_gms_common_internal_zzag, ServiceConnection serviceConnection, String str);
}
