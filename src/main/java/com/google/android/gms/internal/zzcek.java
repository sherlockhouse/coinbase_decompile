package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.measurement.AppMeasurement.zza;
import com.google.android.gms.measurement.AppMeasurement.zzb;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public final class zzcek extends zzcdu {
    protected zzcen zzivj;
    private volatile zzb zzivk;
    private zzb zzivl;
    private long zzivm;
    private final Map<Activity, zzcen> zzivn = new ArrayMap();
    private final CopyOnWriteArrayList<zza> zzivo = new CopyOnWriteArrayList();
    private boolean zzivp;
    private zzb zzivq;
    private String zzivr;

    public zzcek(zzccw com_google_android_gms_internal_zzccw) {
        super(com_google_android_gms_internal_zzccw);
    }

    private final void zza(Activity activity, zzcen com_google_android_gms_internal_zzcen, boolean z) {
        boolean hasNext;
        boolean z2 = true;
        zzb com_google_android_gms_measurement_AppMeasurement_zzb = this.zzivk != null ? this.zzivk : (this.zzivl == null || Math.abs(zzvx().elapsedRealtime() - this.zzivm) >= 1000) ? null : this.zzivl;
        com_google_android_gms_measurement_AppMeasurement_zzb = com_google_android_gms_measurement_AppMeasurement_zzb != null ? new zzb(com_google_android_gms_measurement_AppMeasurement_zzb) : null;
        this.zzivp = true;
        try {
            Iterator it = this.zzivo.iterator();
            while (true) {
                hasNext = it.hasNext();
                if (!hasNext) {
                    break;
                }
                try {
                    z2 &= ((zza) it.next()).zza(com_google_android_gms_measurement_AppMeasurement_zzb, com_google_android_gms_internal_zzcen);
                } catch (Exception e) {
                    zzaul().zzayd().zzj("onScreenChangeCallback threw exception", e);
                }
            }
            hasNext = z2;
        } catch (Exception e2) {
            Exception exception = e2;
            hasNext = z2;
            z2 = zzaul().zzayd();
            z2.zzj("onScreenChangeCallback loop threw exception", exception);
        } finally {
            this.zzivp = false;
        }
        com_google_android_gms_measurement_AppMeasurement_zzb = this.zzivk == null ? this.zzivl : this.zzivk;
        if (hasNext) {
            if (com_google_android_gms_internal_zzcen.zziko == null) {
                com_google_android_gms_internal_zzcen.zziko = zzjt(activity.getClass().getCanonicalName());
            }
            zzb com_google_android_gms_internal_zzcen2 = new zzcen(com_google_android_gms_internal_zzcen);
            this.zzivl = this.zzivk;
            this.zzivm = zzvx().elapsedRealtime();
            this.zzivk = com_google_android_gms_internal_zzcen2;
            zzauk().zzg(new zzcel(this, z, com_google_android_gms_measurement_AppMeasurement_zzb, com_google_android_gms_internal_zzcen2));
        }
    }

    private final void zza(zzcen com_google_android_gms_internal_zzcen) {
        zzatx().zzaj(zzvx().elapsedRealtime());
        if (zzauj().zzbs(com_google_android_gms_internal_zzcen.zzivx)) {
            com_google_android_gms_internal_zzcen.zzivx = false;
        }
    }

    public static void zza(zzb com_google_android_gms_measurement_AppMeasurement_zzb, Bundle bundle) {
        if (bundle != null && com_google_android_gms_measurement_AppMeasurement_zzb != null && !bundle.containsKey("_sc")) {
            if (com_google_android_gms_measurement_AppMeasurement_zzb.zzikn != null) {
                bundle.putString("_sn", com_google_android_gms_measurement_AppMeasurement_zzb.zzikn);
            }
            bundle.putString("_sc", com_google_android_gms_measurement_AppMeasurement_zzb.zziko);
            bundle.putLong("_si", com_google_android_gms_measurement_AppMeasurement_zzb.zzikp);
        }
    }

    private static String zzjt(String str) {
        String[] split = str.split("\\.");
        if (split.length == 0) {
            return str.substring(0, 36);
        }
        String str2 = split[split.length - 1];
        return str2.length() > 36 ? str2.substring(0, 36) : str2;
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final void onActivityDestroyed(Activity activity) {
        this.zzivn.remove(activity);
    }

    public final void onActivityPaused(Activity activity) {
        zzcen zzq = zzq(activity);
        this.zzivl = this.zzivk;
        this.zzivm = zzvx().elapsedRealtime();
        this.zzivk = null;
        zzauk().zzg(new zzcem(this, zzq));
    }

    public final void onActivityResumed(Activity activity) {
        zza(activity, zzq(activity), false);
        zzcdt zzatx = zzatx();
        zzatx.zzauk().zzg(new zzcaq(zzatx, zzatx.zzvx().elapsedRealtime()));
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        if (bundle != null) {
            zzcen com_google_android_gms_internal_zzcen = (zzcen) this.zzivn.get(activity);
            if (com_google_android_gms_internal_zzcen != null) {
                Bundle bundle2 = new Bundle();
                bundle2.putLong("id", com_google_android_gms_internal_zzcen.zzikp);
                bundle2.putString("name", com_google_android_gms_internal_zzcen.zzikn);
                bundle2.putString("referrer_name", com_google_android_gms_internal_zzcen.zziko);
                bundle.putBundle("com.google.firebase.analytics.screen_service", bundle2);
            }
        }
    }

    public final void registerOnScreenChangeCallback(zza com_google_android_gms_measurement_AppMeasurement_zza) {
        zzatv();
        if (com_google_android_gms_measurement_AppMeasurement_zza == null) {
            zzaul().zzayf().log("Attempting to register null OnScreenChangeCallback");
            return;
        }
        this.zzivo.remove(com_google_android_gms_measurement_AppMeasurement_zza);
        this.zzivo.add(com_google_android_gms_measurement_AppMeasurement_zza);
    }

    public final void setCurrentScreen(Activity activity, String str, String str2) {
        if (activity == null) {
            zzaul().zzayf().log("setCurrentScreen must be called with a non-null activity");
            return;
        }
        zzauk();
        if (!zzccr.zzaq()) {
            zzaul().zzayf().log("setCurrentScreen must be called from the main thread");
        } else if (this.zzivp) {
            zzaul().zzayf().log("Cannot call setCurrentScreen from onScreenChangeCallback");
        } else if (this.zzivk == null) {
            zzaul().zzayf().log("setCurrentScreen cannot be called while no activity active");
        } else if (this.zzivn.get(activity) == null) {
            zzaul().zzayf().log("setCurrentScreen must be called with an activity in the activity lifecycle");
        } else {
            if (str2 == null) {
                str2 = zzjt(activity.getClass().getCanonicalName());
            }
            boolean equals = this.zzivk.zziko.equals(str2);
            boolean zzas = zzcfw.zzas(this.zzivk.zzikn, str);
            if (equals && zzas) {
                zzaul().zzayg().log("setCurrentScreen cannot be called with the same class and name");
            } else if (str != null && (str.length() <= 0 || str.length() > zzcax.zzavq())) {
                zzaul().zzayf().zzj("Invalid screen name length in setCurrentScreen. Length", Integer.valueOf(str.length()));
            } else if (str2 == null || (str2.length() > 0 && str2.length() <= zzcax.zzavq())) {
                Object obj;
                zzcby zzayj = zzaul().zzayj();
                String str3 = "Setting current screen to name, class";
                if (str == null) {
                    obj = "null";
                } else {
                    String str4 = str;
                }
                zzayj.zze(str3, obj, str2);
                zzcen com_google_android_gms_internal_zzcen = new zzcen(str, str2, zzauh().zzazx());
                this.zzivn.put(activity, com_google_android_gms_internal_zzcen);
                zza(activity, com_google_android_gms_internal_zzcen, true);
            } else {
                zzaul().zzayf().zzj("Invalid class name length in setCurrentScreen. Length", Integer.valueOf(str2.length()));
            }
        }
    }

    public final void unregisterOnScreenChangeCallback(zza com_google_android_gms_measurement_AppMeasurement_zza) {
        zzatv();
        this.zzivo.remove(com_google_android_gms_measurement_AppMeasurement_zza);
    }

    public final void zza(String str, zzb com_google_android_gms_measurement_AppMeasurement_zzb) {
        zzuj();
        synchronized (this) {
            if (this.zzivr == null || this.zzivr.equals(str) || com_google_android_gms_measurement_AppMeasurement_zzb != null) {
                this.zzivr = str;
                this.zzivq = com_google_android_gms_measurement_AppMeasurement_zzb;
            }
        }
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

    public final zzcen zzazn() {
        zzwk();
        zzuj();
        return this.zzivj;
    }

    public final zzb zzazo() {
        zzatv();
        zzb com_google_android_gms_measurement_AppMeasurement_zzb = this.zzivk;
        return com_google_android_gms_measurement_AppMeasurement_zzb == null ? null : new zzb(com_google_android_gms_measurement_AppMeasurement_zzb);
    }

    final zzcen zzq(Activity activity) {
        zzbp.zzu(activity);
        zzcen com_google_android_gms_internal_zzcen = (zzcen) this.zzivn.get(activity);
        if (com_google_android_gms_internal_zzcen != null) {
            return com_google_android_gms_internal_zzcen;
        }
        com_google_android_gms_internal_zzcen = new zzcen(null, zzjt(activity.getClass().getCanonicalName()), zzauh().zzazx());
        this.zzivn.put(activity, com_google_android_gms_internal_zzcen);
        return com_google_android_gms_internal_zzcen;
    }

    public final /* bridge */ /* synthetic */ void zzuj() {
        super.zzuj();
    }

    protected final void zzuk() {
    }

    public final /* bridge */ /* synthetic */ zzd zzvx() {
        return super.zzvx();
    }
}
