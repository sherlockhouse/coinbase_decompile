package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.google.android.gms.common.api.internal.zzca;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.common.util.zzr;
import com.google.android.gms.common.zze;
import java.lang.reflect.InvocationTargetException;

public final class zzcax extends zzcdt {
    private static String zzimq = String.valueOf(zze.GOOGLE_PLAY_SERVICES_VERSION_CODE / 1000).replaceAll("(\\d+)(\\d)(\\d\\d)", "$1.$2.$3");
    private Boolean zzdqo;

    zzcax(zzccw com_google_android_gms_internal_zzccw) {
        super(com_google_android_gms_internal_zzccw);
    }

    public static boolean zzaif() {
        return zzca.zzaif();
    }

    public static long zzauv() {
        return 11400;
    }

    static String zzavl() {
        return (String) zzcbm.zziob.get();
    }

    public static int zzavm() {
        return 25;
    }

    public static int zzavn() {
        return 40;
    }

    public static int zzavo() {
        return 24;
    }

    static int zzavp() {
        return 40;
    }

    static int zzavq() {
        return 100;
    }

    static int zzavr() {
        return 256;
    }

    static int zzavs() {
        return 1000;
    }

    public static int zzavt() {
        return 36;
    }

    public static int zzavu() {
        return 2048;
    }

    static int zzavv() {
        return 500;
    }

    public static long zzavw() {
        return (long) ((Integer) zzcbm.zziol.get()).intValue();
    }

    public static long zzavx() {
        return (long) ((Integer) zzcbm.zzion.get()).intValue();
    }

    static int zzavy() {
        return 25;
    }

    static int zzavz() {
        return 1000;
    }

    static int zzawa() {
        return 25;
    }

    static int zzawb() {
        return 1000;
    }

    static long zzawc() {
        return 15552000000L;
    }

    static long zzawd() {
        return 15552000000L;
    }

    static long zzawe() {
        return 3600000;
    }

    static long zzawf() {
        return 60000;
    }

    static long zzawg() {
        return 61000;
    }

    static long zzawh() {
        return ((Long) zzcbm.zziph.get()).longValue();
    }

    public static String zzawi() {
        return "google_app_measurement.db";
    }

    static String zzawj() {
        return "google_app_measurement_local.db";
    }

    public static boolean zzawk() {
        return false;
    }

    public static long zzawm() {
        return ((Long) zzcbm.zzipe.get()).longValue();
    }

    public static long zzawn() {
        return ((Long) zzcbm.zzioz.get()).longValue();
    }

    public static long zzawo() {
        return ((Long) zzcbm.zzipa.get()).longValue();
    }

    public static long zzawp() {
        return 1000;
    }

    public static long zzawq() {
        return Math.max(0, ((Long) zzcbm.zziod.get()).longValue());
    }

    public static int zzawr() {
        return Math.max(0, ((Integer) zzcbm.zzioj.get()).intValue());
    }

    public static int zzaws() {
        return Math.max(1, ((Integer) zzcbm.zziok.get()).intValue());
    }

    public static int zzawt() {
        return 100000;
    }

    public static String zzawu() {
        return (String) zzcbm.zzior.get();
    }

    public static long zzawv() {
        return ((Long) zzcbm.zzioe.get()).longValue();
    }

    public static long zzaww() {
        return Math.max(0, ((Long) zzcbm.zzios.get()).longValue());
    }

    public static long zzawx() {
        return Math.max(0, ((Long) zzcbm.zziou.get()).longValue());
    }

    public static long zzawy() {
        return Math.max(0, ((Long) zzcbm.zziov.get()).longValue());
    }

    public static long zzawz() {
        return Math.max(0, ((Long) zzcbm.zziow.get()).longValue());
    }

    public static long zzaxa() {
        return Math.max(0, ((Long) zzcbm.zziox.get()).longValue());
    }

    public static long zzaxb() {
        return Math.max(0, ((Long) zzcbm.zzioy.get()).longValue());
    }

    public static long zzaxc() {
        return ((Long) zzcbm.zziot.get()).longValue();
    }

    public static long zzaxd() {
        return Math.max(0, ((Long) zzcbm.zzipb.get()).longValue());
    }

    public static long zzaxe() {
        return Math.max(0, ((Long) zzcbm.zzipc.get()).longValue());
    }

    public static int zzaxf() {
        return Math.min(20, Math.max(0, ((Integer) zzcbm.zzipd.get()).intValue()));
    }

    public static boolean zzaxh() {
        return ((Boolean) zzcbm.zzinz.get()).booleanValue();
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final long zza(String str, zzcbn<Long> com_google_android_gms_internal_zzcbn_java_lang_Long) {
        if (str == null) {
            return ((Long) com_google_android_gms_internal_zzcbn_java_lang_Long.get()).longValue();
        }
        Object zzan = zzaui().zzan(str, com_google_android_gms_internal_zzcbn_java_lang_Long.getKey());
        if (TextUtils.isEmpty(zzan)) {
            return ((Long) com_google_android_gms_internal_zzcbn_java_lang_Long.get()).longValue();
        }
        try {
            return ((Long) com_google_android_gms_internal_zzcbn_java_lang_Long.get(Long.valueOf(Long.valueOf(zzan).longValue()))).longValue();
        } catch (NumberFormatException e) {
            return ((Long) com_google_android_gms_internal_zzcbn_java_lang_Long.get()).longValue();
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

    public final boolean zzawl() {
        Boolean zzit = zzit("firebase_analytics_collection_deactivated");
        return zzit != null && zzit.booleanValue();
    }

    public final String zzaxg() {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke(null, new Object[]{"debug.firebase.analytics.app", ""});
        } catch (ClassNotFoundException e) {
            zzaul().zzayd().zzj("Could not find SystemProperties class", e);
        } catch (NoSuchMethodException e2) {
            zzaul().zzayd().zzj("Could not find SystemProperties.get() method", e2);
        } catch (IllegalAccessException e3) {
            zzaul().zzayd().zzj("Could not access SystemProperties.get()", e3);
        } catch (InvocationTargetException e4) {
            zzaul().zzayd().zzj("SystemProperties.get() threw an exception", e4);
        }
        return "";
    }

    public final int zzb(String str, zzcbn<Integer> com_google_android_gms_internal_zzcbn_java_lang_Integer) {
        if (str == null) {
            return ((Integer) com_google_android_gms_internal_zzcbn_java_lang_Integer.get()).intValue();
        }
        Object zzan = zzaui().zzan(str, com_google_android_gms_internal_zzcbn_java_lang_Integer.getKey());
        if (TextUtils.isEmpty(zzan)) {
            return ((Integer) com_google_android_gms_internal_zzcbn_java_lang_Integer.get()).intValue();
        }
        try {
            return ((Integer) com_google_android_gms_internal_zzcbn_java_lang_Integer.get(Integer.valueOf(Integer.valueOf(zzan).intValue()))).intValue();
        } catch (NumberFormatException e) {
            return ((Integer) com_google_android_gms_internal_zzcbn_java_lang_Integer.get()).intValue();
        }
    }

    public final int zzis(String str) {
        return zzb(str, zzcbm.zziop);
    }

    final Boolean zzit(String str) {
        Boolean bool = null;
        zzbp.zzgg(str);
        try {
            if (getContext().getPackageManager() == null) {
                zzaul().zzayd().log("Failed to load metadata: PackageManager is null");
            } else {
                ApplicationInfo applicationInfo = zzbed.zzcr(getContext()).getApplicationInfo(getContext().getPackageName(), 128);
                if (applicationInfo == null) {
                    zzaul().zzayd().log("Failed to load metadata: ApplicationInfo is null");
                } else if (applicationInfo.metaData == null) {
                    zzaul().zzayd().log("Failed to load metadata: Metadata bundle is null");
                } else if (applicationInfo.metaData.containsKey(str)) {
                    bool = Boolean.valueOf(applicationInfo.metaData.getBoolean(str));
                }
            }
        } catch (NameNotFoundException e) {
            zzaul().zzayd().zzj("Failed to load metadata: Package name not found", e);
        }
        return bool;
    }

    public final boolean zziu(String str) {
        return "1".equals(zzaui().zzan(str, "gaia_collection_enabled"));
    }

    public final /* bridge */ /* synthetic */ void zzuj() {
        super.zzuj();
    }

    public final /* bridge */ /* synthetic */ zzd zzvx() {
        return super.zzvx();
    }

    public final boolean zzxu() {
        if (this.zzdqo == null) {
            synchronized (this) {
                if (this.zzdqo == null) {
                    ApplicationInfo applicationInfo = getContext().getApplicationInfo();
                    String zzalk = zzr.zzalk();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        boolean z = str != null && str.equals(zzalk);
                        this.zzdqo = Boolean.valueOf(z);
                    }
                    if (this.zzdqo == null) {
                        this.zzdqo = Boolean.TRUE;
                        zzaul().zzayd().log("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.zzdqo.booleanValue();
    }
}
