package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.zzo;

public class zzb {
    private static zzb zzaly;
    private final Context zzahz;

    private zzb(Context context) {
        this.zzahz = context;
    }

    private final void zza(Info info, boolean z, long j) {
        if (Math.random() <= ((double) new zzd(this.zzahz).getFloat("gads:ad_id_use_shared_preference:ping_ratio", 0.0f))) {
            new Thread(new zzc(info, z, j)).start();
        }
    }

    public static zzb zzd(Context context) {
        zzb com_google_android_gms_ads_identifier_zzb;
        synchronized (zzb.class) {
            if (zzaly == null) {
                zzaly = new zzb(context);
            }
            com_google_android_gms_ads_identifier_zzb = zzaly;
        }
        return com_google_android_gms_ads_identifier_zzb;
    }

    public final Info getInfo() {
        Info info = null;
        Context remoteContext = zzo.getRemoteContext(this.zzahz);
        if (remoteContext == null) {
            zza(null, false, -1);
        } else {
            SharedPreferences sharedPreferences = remoteContext.getSharedPreferences("adid_settings", 0);
            if (sharedPreferences == null) {
                zza(null, false, -1);
            } else {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (sharedPreferences.contains("adid_key") && sharedPreferences.contains("enable_limit_ad_tracking")) {
                    info = new Info(sharedPreferences.getString("adid_key", ""), sharedPreferences.getBoolean("enable_limit_ad_tracking", false));
                }
                zza(info, true, SystemClock.elapsedRealtime() - elapsedRealtime);
            }
        }
        return info;
    }
}
