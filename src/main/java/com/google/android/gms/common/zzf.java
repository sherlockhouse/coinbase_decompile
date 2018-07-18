package com.google.android.gms.common;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.internal.zzaz;
import com.google.android.gms.common.internal.zzba;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamite.DynamiteModule;

final class zzf {
    private static zzaz zzffl;
    private static final Object zzffm = new Object();
    private static Context zzffn;

    static boolean zza(String str, zzg com_google_android_gms_common_zzg) {
        return zza(str, com_google_android_gms_common_zzg, false);
    }

    private static boolean zza(String str, zzg com_google_android_gms_common_zzg, boolean z) {
        boolean z2 = false;
        if (zzaey()) {
            zzbp.zzu(zzffn);
            try {
                z2 = zzffl.zza(new zzm(str, com_google_android_gms_common_zzg, z), zzn.zzw(zzffn.getPackageManager()));
            } catch (Throwable e) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
            }
        }
        return z2;
    }

    private static boolean zzaey() {
        boolean z = true;
        if (zzffl == null) {
            zzbp.zzu(zzffn);
            synchronized (zzffm) {
                if (zzffl == null) {
                    try {
                        zzffl = zzba.zzal(DynamiteModule.zza(zzffn, DynamiteModule.zzgpr, "com.google.android.gms.googlecertificates").zzgv("com.google.android.gms.common.GoogleCertificatesImpl"));
                    } catch (Throwable e) {
                        Log.e("GoogleCertificates", "Failed to load com.google.android.gms.googlecertificates", e);
                        z = false;
                    }
                }
            }
        }
        return z;
    }

    static boolean zzb(String str, zzg com_google_android_gms_common_zzg) {
        return zza(str, com_google_android_gms_common_zzg, true);
    }

    static synchronized void zzbx(Context context) {
        synchronized (zzf.class) {
            if (zzffn != null) {
                Log.w("GoogleCertificates", "GoogleCertificates has been initialized already");
            } else if (context != null) {
                zzffn = context.getApplicationContext();
            }
        }
    }
}
