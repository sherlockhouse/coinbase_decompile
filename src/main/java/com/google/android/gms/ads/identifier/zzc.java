package com.google.android.gms.ads.identifier;

import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.util.HashMap;
import java.util.Map;

final /* synthetic */ class zzc implements Runnable {
    private final Info zzalz;
    private final boolean zzama;
    private final long zzamb;

    zzc(Info info, boolean z, long j) {
        this.zzalz = info;
        this.zzama = z;
        this.zzamb = j;
    }

    public final void run() {
        Info info = this.zzalz;
        boolean z = this.zzama;
        long j = this.zzamb;
        Map hashMap = new HashMap();
        hashMap.put("ad_id_size", Integer.toString(info == null ? -1 : info.getId().length()));
        hashMap.put("has_gmscore", z ? "1" : "0");
        hashMap.put("tag", "AdvertisingIdLightClient");
        hashMap.put("time_spent", Long.toString(j));
        new zze().zzb(hashMap);
    }
}
