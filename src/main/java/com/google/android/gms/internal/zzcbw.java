package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.coinbase.android.utils.CryptoUri;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.measurement.AppMeasurement;

public final class zzcbw extends zzcdu {
    private final String zzfvk = zzcax.zzavl();
    private final long zzilg = zzcax.zzauv();
    private final char zzipr;
    private final zzcby zzips;
    private final zzcby zzipt;
    private final zzcby zzipu;
    private final zzcby zzipv;
    private final zzcby zzipw;
    private final zzcby zzipx;
    private final zzcby zzipy;
    private final zzcby zzipz;
    private final zzcby zziqa;

    zzcbw(zzccw com_google_android_gms_internal_zzccw) {
        super(com_google_android_gms_internal_zzccw);
        if (zzaun().zzxu()) {
            zzcax.zzawk();
            this.zzipr = 'C';
        } else {
            zzcax.zzawk();
            this.zzipr = 'c';
        }
        this.zzips = new zzcby(this, 6, false, false);
        this.zzipt = new zzcby(this, 6, true, false);
        this.zzipu = new zzcby(this, 6, false, true);
        this.zzipv = new zzcby(this, 5, false, false);
        this.zzipw = new zzcby(this, 5, true, false);
        this.zzipx = new zzcby(this, 5, false, true);
        this.zzipy = new zzcby(this, 4, false, false);
        this.zzipz = new zzcby(this, 3, false, false);
        this.zziqa = new zzcby(this, 2, false, false);
    }

    private static String zza(boolean z, String str, Object obj, Object obj2, Object obj3) {
        if (str == null) {
            Object obj4 = "";
        }
        Object zzc = zzc(z, obj);
        Object zzc2 = zzc(z, obj2);
        Object zzc3 = zzc(z, obj3);
        StringBuilder stringBuilder = new StringBuilder();
        String str2 = "";
        if (!TextUtils.isEmpty(obj4)) {
            stringBuilder.append(obj4);
            str2 = ": ";
        }
        if (!TextUtils.isEmpty(zzc)) {
            stringBuilder.append(str2);
            stringBuilder.append(zzc);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(zzc2)) {
            stringBuilder.append(str2);
            stringBuilder.append(zzc2);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(zzc3)) {
            stringBuilder.append(str2);
            stringBuilder.append(zzc3);
        }
        return stringBuilder.toString();
    }

    private static String zzc(boolean z, Object obj) {
        if (obj == null) {
            return "";
        }
        Object valueOf = obj instanceof Integer ? Long.valueOf((long) ((Integer) obj).intValue()) : obj;
        if (valueOf instanceof Long) {
            if (!z) {
                return String.valueOf(valueOf);
            }
            if (Math.abs(((Long) valueOf).longValue()) < 100) {
                return String.valueOf(valueOf);
            }
            String str = String.valueOf(valueOf).charAt(0) == '-' ? "-" : "";
            String valueOf2 = String.valueOf(Math.abs(((Long) valueOf).longValue()));
            return new StringBuilder((String.valueOf(str).length() + 43) + String.valueOf(str).length()).append(str).append(Math.round(Math.pow(10.0d, (double) (valueOf2.length() - 1)))).append("...").append(str).append(Math.round(Math.pow(10.0d, (double) valueOf2.length()) - 1.0d)).toString();
        } else if (valueOf instanceof Boolean) {
            return String.valueOf(valueOf);
        } else {
            if (!(valueOf instanceof Throwable)) {
                return valueOf instanceof zzcbz ? ((zzcbz) valueOf).zzgql : z ? "-" : String.valueOf(valueOf);
            } else {
                Throwable th = (Throwable) valueOf;
                StringBuilder stringBuilder = new StringBuilder(z ? th.getClass().getName() : th.toString());
                String zzjg = zzjg(AppMeasurement.class.getCanonicalName());
                String zzjg2 = zzjg(zzccw.class.getCanonicalName());
                for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                    if (!stackTraceElement.isNativeMethod()) {
                        String className = stackTraceElement.getClassName();
                        if (className != null) {
                            className = zzjg(className);
                            if (className.equals(zzjg) || className.equals(zzjg2)) {
                                stringBuilder.append(": ");
                                stringBuilder.append(stackTraceElement);
                                break;
                            }
                        } else {
                            continue;
                        }
                    }
                }
                return stringBuilder.toString();
            }
        }
    }

    protected static Object zzjf(String str) {
        return str == null ? null : new zzcbz(str);
    }

    private static String zzjg(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf != -1 ? str.substring(0, lastIndexOf) : str;
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    protected final void zza(int i, boolean z, boolean z2, String str, Object obj, Object obj2, Object obj3) {
        if (!z && zzad(i)) {
            zzk(i, zza(false, str, obj, obj2, obj3));
        }
        if (!z2 && i >= 5) {
            zzbp.zzu(str);
            zzcdu zzayx = this.zziki.zzayx();
            if (zzayx == null) {
                zzk(6, "Scheduler not set. Not logging error/warn");
            } else if (zzayx.isInitialized()) {
                int i2 = i < 0 ? 0 : i;
                if (i2 >= 9) {
                    i2 = 8;
                }
                String str2 = "2";
                char charAt = "01VDIWEA?".charAt(i2);
                char c = this.zzipr;
                long j = this.zzilg;
                String zza = zza(true, str, obj, obj2, obj3);
                String stringBuilder = new StringBuilder((String.valueOf(str2).length() + 23) + String.valueOf(zza).length()).append(str2).append(charAt).append(c).append(j).append(CryptoUri.URI_SCHEME_DELIMETER).append(zza).toString();
                if (stringBuilder.length() > 1024) {
                    stringBuilder = str.substring(0, 1024);
                }
                zzayx.zzg(new zzcbx(this, stringBuilder));
            } else {
                zzk(6, "Scheduler not initialized. Not logging error/warn");
            }
        }
    }

    protected final boolean zzad(int i) {
        return Log.isLoggable(this.zzfvk, i);
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

    public final zzcby zzayd() {
        return this.zzips;
    }

    public final zzcby zzaye() {
        return this.zzipt;
    }

    public final zzcby zzayf() {
        return this.zzipv;
    }

    public final zzcby zzayg() {
        return this.zzipx;
    }

    public final zzcby zzayh() {
        return this.zzipy;
    }

    public final zzcby zzayi() {
        return this.zzipz;
    }

    public final zzcby zzayj() {
        return this.zziqa;
    }

    public final String zzayk() {
        Pair zzzi = zzaum().zziqo.zzzi();
        if (zzzi == null || zzzi == zzcch.zziqn) {
            return null;
        }
        String valueOf = String.valueOf(zzzi.second);
        String str = (String) zzzi.first;
        return new StringBuilder((String.valueOf(valueOf).length() + 1) + String.valueOf(str).length()).append(valueOf).append(CryptoUri.URI_SCHEME_DELIMETER).append(str).toString();
    }

    protected final void zzk(int i, String str) {
        Log.println(i, this.zzfvk, str);
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
