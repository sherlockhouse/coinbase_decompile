package com.google.android.gms.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.measurement.AppMeasurement.Event;
import com.google.android.gms.measurement.AppMeasurement.Param;
import com.google.android.gms.measurement.AppMeasurement.UserProperty;
import java.io.IOException;
import java.util.Map;

public final class zzccq extends zzcdu {
    private final Map<String, Map<String, String>> zzirv = new ArrayMap();
    private final Map<String, Map<String, Boolean>> zzirw = new ArrayMap();
    private final Map<String, Map<String, Boolean>> zzirx = new ArrayMap();
    private final Map<String, zzcge> zziry = new ArrayMap();
    private final Map<String, String> zzirz = new ArrayMap();

    zzccq(zzccw com_google_android_gms_internal_zzccw) {
        super(com_google_android_gms_internal_zzccw);
    }

    private static Map<String, String> zza(zzcge com_google_android_gms_internal_zzcge) {
        Map<String, String> arrayMap = new ArrayMap();
        if (!(com_google_android_gms_internal_zzcge == null || com_google_android_gms_internal_zzcge.zziyo == null)) {
            for (zzcgf com_google_android_gms_internal_zzcgf : com_google_android_gms_internal_zzcge.zziyo) {
                if (com_google_android_gms_internal_zzcgf != null) {
                    arrayMap.put(com_google_android_gms_internal_zzcgf.key, com_google_android_gms_internal_zzcgf.value);
                }
            }
        }
        return arrayMap;
    }

    private final void zza(String str, zzcge com_google_android_gms_internal_zzcge) {
        Map arrayMap = new ArrayMap();
        Map arrayMap2 = new ArrayMap();
        if (!(com_google_android_gms_internal_zzcge == null || com_google_android_gms_internal_zzcge.zziyp == null)) {
            for (zzcgd com_google_android_gms_internal_zzcgd : com_google_android_gms_internal_zzcge.zziyp) {
                if (com_google_android_gms_internal_zzcgd != null) {
                    String zzil = Event.zzil(com_google_android_gms_internal_zzcgd.name);
                    if (zzil != null) {
                        com_google_android_gms_internal_zzcgd.name = zzil;
                    }
                    arrayMap.put(com_google_android_gms_internal_zzcgd.name, com_google_android_gms_internal_zzcgd.zziyk);
                    arrayMap2.put(com_google_android_gms_internal_zzcgd.name, com_google_android_gms_internal_zzcgd.zziyl);
                }
            }
        }
        this.zzirw.put(str, arrayMap);
        this.zzirx.put(str, arrayMap2);
    }

    private final zzcge zzc(String str, byte[] bArr) {
        if (bArr == null) {
            return new zzcge();
        }
        zzeye zzm = zzeye.zzm(bArr, 0, bArr.length);
        zzeyn com_google_android_gms_internal_zzcge = new zzcge();
        try {
            com_google_android_gms_internal_zzcge.zza(zzm);
            zzaul().zzayj().zze("Parsed config. version, gmp_app_id", com_google_android_gms_internal_zzcge.zziym, com_google_android_gms_internal_zzcge.zzilu);
            return com_google_android_gms_internal_zzcge;
        } catch (IOException e) {
            zzaul().zzayf().zze("Unable to merge remote config. appId", zzcbw.zzjf(str), e);
            return new zzcge();
        }
    }

    private final void zzjm(String str) {
        zzwk();
        zzuj();
        zzbp.zzgg(str);
        if (this.zziry.get(str) == null) {
            byte[] zziy = zzauf().zziy(str);
            if (zziy == null) {
                this.zzirv.put(str, null);
                this.zzirw.put(str, null);
                this.zzirx.put(str, null);
                this.zziry.put(str, null);
                this.zzirz.put(str, null);
                return;
            }
            zzcge zzc = zzc(str, zziy);
            this.zzirv.put(str, zza(zzc));
            zza(str, zzc);
            this.zziry.put(str, zzc);
            this.zzirz.put(str, null);
        }
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    final String zzan(String str, String str2) {
        zzuj();
        zzjm(str);
        Map map = (Map) this.zzirv.get(str);
        return map != null ? (String) map.get(str2) : null;
    }

    final boolean zzao(String str, String str2) {
        zzuj();
        zzjm(str);
        if (zzauh().zzkg(str) && zzcfw.zzkd(str2)) {
            return true;
        }
        if (zzauh().zzkh(str) && zzcfw.zzju(str2)) {
            return true;
        }
        Map map = (Map) this.zzirw.get(str);
        if (map == null) {
            return false;
        }
        Boolean bool = (Boolean) map.get(str2);
        return bool == null ? false : bool.booleanValue();
    }

    final boolean zzap(String str, String str2) {
        zzuj();
        zzjm(str);
        if ("ecommerce_purchase".equals(str2)) {
            return true;
        }
        Map map = (Map) this.zzirx.get(str);
        if (map == null) {
            return false;
        }
        Boolean bool = (Boolean) map.get(str2);
        return bool == null ? false : bool.booleanValue();
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

    protected final boolean zzb(String str, byte[] bArr, String str2) {
        zzwk();
        zzuj();
        zzbp.zzgg(str);
        zzeyn zzc = zzc(str, bArr);
        if (zzc == null) {
            return false;
        }
        zza(str, zzc);
        this.zziry.put(str, zzc);
        this.zzirz.put(str, str2);
        this.zzirv.put(str, zza(zzc));
        zzcdt zzaty = zzaty();
        zzcfx[] com_google_android_gms_internal_zzcfxArr = zzc.zziyq;
        zzbp.zzu(com_google_android_gms_internal_zzcfxArr);
        for (zzcfx com_google_android_gms_internal_zzcfx : com_google_android_gms_internal_zzcfxArr) {
            for (zzcfy com_google_android_gms_internal_zzcfy : com_google_android_gms_internal_zzcfx.zzixl) {
                String zzil = Event.zzil(com_google_android_gms_internal_zzcfy.zzixo);
                if (zzil != null) {
                    com_google_android_gms_internal_zzcfy.zzixo = zzil;
                }
                for (zzcfz com_google_android_gms_internal_zzcfz : com_google_android_gms_internal_zzcfy.zzixp) {
                    String zzil2 = Param.zzil(com_google_android_gms_internal_zzcfz.zzixw);
                    if (zzil2 != null) {
                        com_google_android_gms_internal_zzcfz.zzixw = zzil2;
                    }
                }
            }
            for (zzcgb com_google_android_gms_internal_zzcgb : com_google_android_gms_internal_zzcfx.zzixk) {
                String zzil3 = UserProperty.zzil(com_google_android_gms_internal_zzcgb.zziyd);
                if (zzil3 != null) {
                    com_google_android_gms_internal_zzcgb.zziyd = zzil3;
                }
            }
        }
        zzaty.zzauf().zza(str, com_google_android_gms_internal_zzcfxArr);
        try {
            zzc.zziyq = null;
            byte[] bArr2 = new byte[zzc.zzhi()];
            zzc.zza(zzeyf.zzn(bArr2, 0, bArr2.length));
            bArr = bArr2;
        } catch (IOException e) {
            zzaul().zzayf().zze("Unable to serialize reduced-size config. Storing full config instead. appId", zzcbw.zzjf(str), e);
        }
        zzcdt zzauf = zzauf();
        zzbp.zzgg(str);
        zzauf.zzuj();
        zzauf.zzwk();
        ContentValues contentValues = new ContentValues();
        contentValues.put("remote_config", bArr);
        try {
            if (((long) zzauf.getWritableDatabase().update("apps", contentValues, "app_id = ?", new String[]{str})) == 0) {
                zzauf.zzaul().zzayd().zzj("Failed to update remote config (got 0). appId", zzcbw.zzjf(str));
            }
        } catch (SQLiteException e2) {
            zzauf.zzaul().zzayd().zze("Error storing remote config. appId", zzcbw.zzjf(str), e2);
        }
        return true;
    }

    protected final zzcge zzjn(String str) {
        zzwk();
        zzuj();
        zzbp.zzgg(str);
        zzjm(str);
        return (zzcge) this.zziry.get(str);
    }

    protected final String zzjo(String str) {
        zzuj();
        return (String) this.zzirz.get(str);
    }

    protected final void zzjp(String str) {
        zzuj();
        this.zzirz.put(str, null);
    }

    final void zzjq(String str) {
        zzuj();
        this.zziry.remove(str);
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
