package com.google.android.gms.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbp;
import java.util.Iterator;

public final class zzcbf {
    final String mAppId;
    final String mName;
    private String mOrigin;
    final long zzfdc;
    final long zzinj;
    final zzcbh zzink;

    zzcbf(zzccw com_google_android_gms_internal_zzccw, String str, String str2, String str3, long j, long j2, Bundle bundle) {
        zzbp.zzgg(str2);
        zzbp.zzgg(str3);
        this.mAppId = str2;
        this.mName = str3;
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.mOrigin = str;
        this.zzfdc = j;
        this.zzinj = j2;
        if (this.zzinj != 0 && this.zzinj > this.zzfdc) {
            com_google_android_gms_internal_zzccw.zzaul().zzayf().zzj("Event created with reverse previous/current timestamps. appId", zzcbw.zzjf(str2));
        }
        this.zzink = zza(com_google_android_gms_internal_zzccw, bundle);
    }

    private zzcbf(zzccw com_google_android_gms_internal_zzccw, String str, String str2, String str3, long j, long j2, zzcbh com_google_android_gms_internal_zzcbh) {
        zzbp.zzgg(str2);
        zzbp.zzgg(str3);
        zzbp.zzu(com_google_android_gms_internal_zzcbh);
        this.mAppId = str2;
        this.mName = str3;
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.mOrigin = str;
        this.zzfdc = j;
        this.zzinj = j2;
        if (this.zzinj != 0 && this.zzinj > this.zzfdc) {
            com_google_android_gms_internal_zzccw.zzaul().zzayf().zzj("Event created with reverse previous/current timestamps. appId", zzcbw.zzjf(str2));
        }
        this.zzink = com_google_android_gms_internal_zzcbh;
    }

    private static zzcbh zza(zzccw com_google_android_gms_internal_zzccw, Bundle bundle) {
        if (bundle == null || bundle.isEmpty()) {
            return new zzcbh(new Bundle());
        }
        Bundle bundle2 = new Bundle(bundle);
        Iterator it = bundle2.keySet().iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str == null) {
                com_google_android_gms_internal_zzccw.zzaul().zzayd().log("Param name can't be null");
                it.remove();
            } else {
                Object zzk = com_google_android_gms_internal_zzccw.zzauh().zzk(str, bundle2.get(str));
                if (zzk == null) {
                    com_google_android_gms_internal_zzccw.zzaul().zzayf().zzj("Param value can't be null", com_google_android_gms_internal_zzccw.zzaug().zzjd(str));
                    it.remove();
                } else {
                    com_google_android_gms_internal_zzccw.zzauh().zza(bundle2, str, zzk);
                }
            }
        }
        return new zzcbh(bundle2);
    }

    public final String toString() {
        String str = this.mAppId;
        String str2 = this.mName;
        String valueOf = String.valueOf(this.zzink);
        return new StringBuilder(((String.valueOf(str).length() + 33) + String.valueOf(str2).length()) + String.valueOf(valueOf).length()).append("Event{appId='").append(str).append("', name='").append(str2).append("', params=").append(valueOf).append("}").toString();
    }

    final zzcbf zza(zzccw com_google_android_gms_internal_zzccw, long j) {
        return new zzcbf(com_google_android_gms_internal_zzccw, this.mOrigin, this.mAppId, this.mName, this.zzfdc, j, this.zzink);
    }
}
