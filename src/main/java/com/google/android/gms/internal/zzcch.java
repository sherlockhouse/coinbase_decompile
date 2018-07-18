package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.math.BigInteger;
import java.util.Locale;

final class zzcch extends zzcdu {
    static final Pair<String, Long> zziqn = new Pair("", Long.valueOf(0));
    private SharedPreferences zzdtq;
    public final zzccl zziqo = new zzccl(this, "health_monitor", zzcax.zzawq());
    public final zzcck zziqp = new zzcck(this, "last_upload", 0);
    public final zzcck zziqq = new zzcck(this, "last_upload_attempt", 0);
    public final zzcck zziqr = new zzcck(this, "backoff", 0);
    public final zzcck zziqs = new zzcck(this, "last_delete_stale", 0);
    public final zzcck zziqt = new zzcck(this, "midnight_offset", 0);
    public final zzcck zziqu = new zzcck(this, "first_open_time", 0);
    public final zzccm zziqv = new zzccm(this, "app_instance_id", null);
    private String zziqw;
    private boolean zziqx;
    private long zziqy;
    private String zziqz;
    private long zzira;
    private final Object zzirb = new Object();
    public final zzcck zzirc = new zzcck(this, "time_before_start", 10000);
    public final zzcck zzird = new zzcck(this, "session_timeout", 1800000);
    public final zzccj zzire = new zzccj(this, "start_new_session", true);
    public final zzcck zzirf = new zzcck(this, "last_pause_time", 0);
    public final zzcck zzirg = new zzcck(this, "time_active", 0);
    public boolean zzirh;

    zzcch(zzccw com_google_android_gms_internal_zzccw) {
        super(com_google_android_gms_internal_zzccw);
    }

    private final SharedPreferences zzayl() {
        zzuj();
        zzwk();
        return this.zzdtq;
    }

    final void setMeasurementEnabled(boolean z) {
        zzuj();
        zzaul().zzayj().zzj("Setting measurementEnabled", Boolean.valueOf(z));
        Editor edit = zzayl().edit();
        edit.putBoolean("measurement_enabled", z);
        edit.apply();
    }

    final String zzaym() {
        zzuj();
        return zzayl().getString("gmp_app_id", null);
    }

    final String zzayn() {
        String str;
        synchronized (this.zzirb) {
            if (Math.abs(zzvx().elapsedRealtime() - this.zzira) < 1000) {
                str = this.zziqz;
            } else {
                str = null;
            }
        }
        return str;
    }

    final Boolean zzayo() {
        zzuj();
        return !zzayl().contains("use_service") ? null : Boolean.valueOf(zzayl().getBoolean("use_service", false));
    }

    final void zzayp() {
        boolean z = true;
        zzuj();
        zzaul().zzayj().log("Clearing collection preferences.");
        boolean contains = zzayl().contains("measurement_enabled");
        if (contains) {
            z = zzbn(true);
        }
        Editor edit = zzayl().edit();
        edit.clear();
        edit.apply();
        if (contains) {
            setMeasurementEnabled(z);
        }
    }

    protected final String zzayq() {
        zzuj();
        String string = zzayl().getString("previous_os_version", null);
        zzaub().zzwk();
        String str = VERSION.RELEASE;
        if (!(TextUtils.isEmpty(str) || str.equals(string))) {
            Editor edit = zzayl().edit();
            edit.putString("previous_os_version", str);
            edit.apply();
        }
        return string;
    }

    final void zzbm(boolean z) {
        zzuj();
        zzaul().zzayj().zzj("Setting useService", Boolean.valueOf(z));
        Editor edit = zzayl().edit();
        edit.putBoolean("use_service", z);
        edit.apply();
    }

    final boolean zzbn(boolean z) {
        zzuj();
        return zzayl().getBoolean("measurement_enabled", z);
    }

    final Pair<String, Boolean> zzjh(String str) {
        zzuj();
        long elapsedRealtime = zzvx().elapsedRealtime();
        if (this.zziqw != null && elapsedRealtime < this.zziqy) {
            return new Pair(this.zziqw, Boolean.valueOf(this.zziqx));
        }
        this.zziqy = elapsedRealtime + zzaun().zza(str, zzcbm.zzioc);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(getContext());
            if (advertisingIdInfo != null) {
                this.zziqw = advertisingIdInfo.getId();
                this.zziqx = advertisingIdInfo.isLimitAdTrackingEnabled();
            }
            if (this.zziqw == null) {
                this.zziqw = "";
            }
        } catch (Throwable th) {
            zzaul().zzayi().zzj("Unable to get advertising id", th);
            this.zziqw = "";
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair(this.zziqw, Boolean.valueOf(this.zziqx));
    }

    final String zzji(String str) {
        zzuj();
        String str2 = (String) zzjh(str).first;
        if (zzcfw.zzec("MD5") == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, zzcfw.zzec("MD5").digest(str2.getBytes()))});
    }

    final void zzjj(String str) {
        zzuj();
        Editor edit = zzayl().edit();
        edit.putString("gmp_app_id", str);
        edit.apply();
    }

    final void zzjk(String str) {
        synchronized (this.zzirb) {
            this.zziqz = str;
            this.zzira = zzvx().elapsedRealtime();
        }
    }

    protected final void zzuk() {
        this.zzdtq = getContext().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.zzirh = this.zzdtq.getBoolean("has_been_opened", false);
        if (!this.zzirh) {
            Editor edit = this.zzdtq.edit();
            edit.putBoolean("has_been_opened", true);
            edit.apply();
        }
    }
}
