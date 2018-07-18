package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule.zzd;

final class zzc implements zzd {
    zzc() {
    }

    public final zzj zza(Context context, String str, zzi com_google_android_gms_dynamite_zzi) throws com.google.android.gms.dynamite.DynamiteModule.zzc {
        zzj com_google_android_gms_dynamite_zzj = new zzj();
        com_google_android_gms_dynamite_zzj.zzgpy = com_google_android_gms_dynamite_zzi.zzad(context, str);
        if (com_google_android_gms_dynamite_zzj.zzgpy != 0) {
            com_google_android_gms_dynamite_zzj.zzgqa = -1;
        } else {
            com_google_android_gms_dynamite_zzj.zzgpz = com_google_android_gms_dynamite_zzi.zzb(context, str, true);
            if (com_google_android_gms_dynamite_zzj.zzgpz != 0) {
                com_google_android_gms_dynamite_zzj.zzgqa = 1;
            }
        }
        return com_google_android_gms_dynamite_zzj;
    }
}
