package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.measurement.AppMeasurement.zzb;
import java.util.Map;

public final class zzcan extends zzcdt {
    private final Map<String, Long> zziku = new ArrayMap();
    private final Map<String, Integer> zzikv = new ArrayMap();
    private long zzikw;

    public zzcan(zzccw com_google_android_gms_internal_zzccw) {
        super(com_google_android_gms_internal_zzccw);
    }

    private final void zza(long j, zzb com_google_android_gms_measurement_AppMeasurement_zzb) {
        if (com_google_android_gms_measurement_AppMeasurement_zzb == null) {
            zzaul().zzayj().log("Not logging ad exposure. No active activity");
        } else if (j < 1000) {
            zzaul().zzayj().zzj("Not logging ad exposure. Less than 1000 ms. exposure", Long.valueOf(j));
        } else {
            Bundle bundle = new Bundle();
            bundle.putLong("_xt", j);
            zzcek.zza(com_google_android_gms_measurement_AppMeasurement_zzb, bundle);
            zzatz().zzc("am", "_xa", bundle);
        }
    }

    private final void zza(String str, long j, zzb com_google_android_gms_measurement_AppMeasurement_zzb) {
        if (com_google_android_gms_measurement_AppMeasurement_zzb == null) {
            zzaul().zzayj().log("Not logging ad unit exposure. No active activity");
        } else if (j < 1000) {
            zzaul().zzayj().zzj("Not logging ad unit exposure. Less than 1000 ms. exposure", Long.valueOf(j));
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("_ai", str);
            bundle.putLong("_xt", j);
            zzcek.zza(com_google_android_gms_measurement_AppMeasurement_zzb, bundle);
            zzatz().zzc("am", "_xu", bundle);
        }
    }

    private final void zzak(long j) {
        for (String put : this.zziku.keySet()) {
            this.zziku.put(put, Long.valueOf(j));
        }
        if (!this.zziku.isEmpty()) {
            this.zzikw = j;
        }
    }

    private final void zzd(String str, long j) {
        zzatv();
        zzuj();
        zzbp.zzgg(str);
        if (this.zzikv.isEmpty()) {
            this.zzikw = j;
        }
        Integer num = (Integer) this.zzikv.get(str);
        if (num != null) {
            this.zzikv.put(str, Integer.valueOf(num.intValue() + 1));
        } else if (this.zzikv.size() >= 100) {
            zzaul().zzayf().log("Too many ads visible");
        } else {
            this.zzikv.put(str, Integer.valueOf(1));
            this.zziku.put(str, Long.valueOf(j));
        }
    }

    private final void zze(String str, long j) {
        zzatv();
        zzuj();
        zzbp.zzgg(str);
        Integer num = (Integer) this.zzikv.get(str);
        if (num != null) {
            zzb zzazn = zzaud().zzazn();
            int intValue = num.intValue() - 1;
            if (intValue == 0) {
                this.zzikv.remove(str);
                Long l = (Long) this.zziku.get(str);
                if (l == null) {
                    zzaul().zzayd().log("First ad unit exposure time was never set");
                } else {
                    long longValue = j - l.longValue();
                    this.zziku.remove(str);
                    zza(str, longValue, zzazn);
                }
                if (!this.zzikv.isEmpty()) {
                    return;
                }
                if (this.zzikw == 0) {
                    zzaul().zzayd().log("First ad exposure time was never set");
                    return;
                }
                zza(j - this.zzikw, zzazn);
                this.zzikw = 0;
                return;
            }
            this.zzikv.put(str, Integer.valueOf(intValue));
            return;
        }
        zzaul().zzayd().zzj("Call to endAdUnitExposure for unknown ad unit id", str);
    }

    public final void beginAdUnitExposure(String str) {
        if (str == null || str.length() == 0) {
            zzaul().zzayd().log("Ad unit id must be a non-empty string");
            return;
        }
        zzauk().zzg(new zzcao(this, str, zzvx().elapsedRealtime()));
    }

    public final void endAdUnitExposure(String str) {
        if (str == null || str.length() == 0) {
            zzaul().zzayd().log("Ad unit id must be a non-empty string");
            return;
        }
        zzauk().zzg(new zzcap(this, str, zzvx().elapsedRealtime()));
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final void zzaj(long j) {
        zzb zzazn = zzaud().zzazn();
        for (String str : this.zziku.keySet()) {
            zza(str, j - ((Long) this.zziku.get(str)).longValue(), zzazn);
        }
        if (!this.zziku.isEmpty()) {
            zza(j - this.zzikw, zzazn);
        }
        zzak(j);
    }

    public final /* bridge */ /* synthetic */ void zzatu() {
        super.zzatu();
    }

    public final /* bridge */ /* synthetic */ void zzatv() {
        super.zzatv();
    }

    public final /* bridge */ /* synthetic */ void zzatw() {
        super.zzatw();
    }

    public final /* bridge */ /* synthetic */ zzcan zzatx() {
        return super.zzatx();
    }

    public final /* bridge */ /* synthetic */ zzcau zzaty() {
        return super.zzaty();
    }

    public final /* bridge */ /* synthetic */ zzcdw zzatz() {
        return super.zzatz();
    }

    public final /* bridge */ /* synthetic */ zzcbr zzaua() {
        return super.zzaua();
    }

    public final /* bridge */ /* synthetic */ zzcbe zzaub() {
        return super.zzaub();
    }

    public final /* bridge */ /* synthetic */ zzceo zzauc() {
        return super.zzauc();
    }

    public final /* bridge */ /* synthetic */ zzcek zzaud() {
        return super.zzaud();
    }

    public final /* bridge */ /* synthetic */ zzcbs zzaue() {
        return super.zzaue();
    }

    public final /* bridge */ /* synthetic */ zzcay zzauf() {
        return super.zzauf();
    }

    public final /* bridge */ /* synthetic */ zzcbu zzaug() {
        return super.zzaug();
    }

    public final /* bridge */ /* synthetic */ zzcfw zzauh() {
        return super.zzauh();
    }

    public final /* bridge */ /* synthetic */ zzccq zzaui() {
        return super.zzaui();
    }

    public final /* bridge */ /* synthetic */ zzcfl zzauj() {
        return super.zzauj();
    }

    public final /* bridge */ /* synthetic */ zzccr zzauk() {
        return super.zzauk();
    }

    public final /* bridge */ /* synthetic */ zzcbw zzaul() {
        return super.zzaul();
    }

    public final /* bridge */ /* synthetic */ zzcch zzaum() {
        return super.zzaum();
    }

    public final /* bridge */ /* synthetic */ zzcax zzaun() {
        return super.zzaun();
    }

    public final /* bridge */ /* synthetic */ void zzuj() {
        super.zzuj();
    }

    public final /* bridge */ /* synthetic */ zzd zzvx() {
        return super.zzvx();
    }
}
