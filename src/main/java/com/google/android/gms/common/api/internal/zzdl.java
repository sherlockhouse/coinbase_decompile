package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import com.google.android.gms.common.api.zze;
import java.lang.ref.WeakReference;
import java.util.NoSuchElementException;

final class zzdl implements DeathRecipient, zzdm {
    private final WeakReference<zzs<?>> zzfpv;
    private final WeakReference<zze> zzfpw;
    private final WeakReference<IBinder> zzfpx;

    private zzdl(zzs<?> com_google_android_gms_common_api_internal_zzs_, zze com_google_android_gms_common_api_zze, IBinder iBinder) {
        this.zzfpw = new WeakReference(com_google_android_gms_common_api_zze);
        this.zzfpv = new WeakReference(com_google_android_gms_common_api_internal_zzs_);
        this.zzfpx = new WeakReference(iBinder);
    }

    private final void zzair() {
        zzs com_google_android_gms_common_api_internal_zzs = (zzs) this.zzfpv.get();
        zze com_google_android_gms_common_api_zze = (zze) this.zzfpw.get();
        if (!(com_google_android_gms_common_api_zze == null || com_google_android_gms_common_api_internal_zzs == null)) {
            com_google_android_gms_common_api_zze.remove(com_google_android_gms_common_api_internal_zzs.zzafs().intValue());
        }
        IBinder iBinder = (IBinder) this.zzfpx.get();
        if (iBinder != null) {
            try {
                iBinder.unlinkToDeath(this, 0);
            } catch (NoSuchElementException e) {
            }
        }
    }

    public final void binderDied() {
        zzair();
    }

    public final void zzc(zzs<?> com_google_android_gms_common_api_internal_zzs_) {
        zzair();
    }
}
