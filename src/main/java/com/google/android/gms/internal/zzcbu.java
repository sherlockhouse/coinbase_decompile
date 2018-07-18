package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import com.coinbase.api.internal.ApiConstants;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.measurement.AppMeasurement.Event;
import com.google.android.gms.measurement.AppMeasurement.Param;
import com.google.android.gms.measurement.AppMeasurement.UserProperty;

public final class zzcbu extends zzcdu {
    private static String[] zzipo = new String[Event.zzikj.length];
    private static String[] zzipp = new String[Param.zzikl.length];
    private static String[] zzipq = new String[UserProperty.zzikq.length];

    zzcbu(zzccw com_google_android_gms_internal_zzccw) {
        super(com_google_android_gms_internal_zzccw);
    }

    private static String zza(String str, String[] strArr, String[] strArr2, String[] strArr3) {
        boolean z = true;
        int i = 0;
        zzbp.zzu(strArr);
        zzbp.zzu(strArr2);
        zzbp.zzu(strArr3);
        zzbp.zzbh(strArr.length == strArr2.length);
        if (strArr.length != strArr3.length) {
            z = false;
        }
        zzbp.zzbh(z);
        while (i < strArr.length) {
            if (zzcfw.zzas(str, strArr[i])) {
                synchronized (strArr3) {
                    if (strArr3[i] == null) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(strArr2[i]);
                        stringBuilder.append("(");
                        stringBuilder.append(strArr[i]);
                        stringBuilder.append(")");
                        strArr3[i] = stringBuilder.toString();
                    }
                    str = strArr3[i];
                }
                return str;
            }
            i++;
        }
        return str;
    }

    private static void zza(StringBuilder stringBuilder, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            stringBuilder.append("  ");
        }
    }

    private final void zza(StringBuilder stringBuilder, int i, zzcfz com_google_android_gms_internal_zzcfz) {
        if (com_google_android_gms_internal_zzcfz != null) {
            zza(stringBuilder, i);
            stringBuilder.append("filter {\n");
            zza(stringBuilder, i, "complement", com_google_android_gms_internal_zzcfz.zzixv);
            zza(stringBuilder, i, "param_name", zzjd(com_google_android_gms_internal_zzcfz.zzixw));
            int i2 = i + 1;
            String str = "string_filter";
            zzcgc com_google_android_gms_internal_zzcgc = com_google_android_gms_internal_zzcfz.zzixt;
            if (com_google_android_gms_internal_zzcgc != null) {
                zza(stringBuilder, i2);
                stringBuilder.append(str);
                stringBuilder.append(" {\n");
                if (com_google_android_gms_internal_zzcgc.zziyf != null) {
                    Object obj = "UNKNOWN_MATCH_TYPE";
                    switch (com_google_android_gms_internal_zzcgc.zziyf.intValue()) {
                        case 1:
                            obj = "REGEXP";
                            break;
                        case 2:
                            obj = "BEGINS_WITH";
                            break;
                        case 3:
                            obj = "ENDS_WITH";
                            break;
                        case 4:
                            obj = "PARTIAL";
                            break;
                        case 5:
                            obj = "EXACT";
                            break;
                        case 6:
                            obj = "IN_LIST";
                            break;
                    }
                    zza(stringBuilder, i2, "match_type", obj);
                }
                zza(stringBuilder, i2, "expression", com_google_android_gms_internal_zzcgc.zziyg);
                zza(stringBuilder, i2, "case_sensitive", com_google_android_gms_internal_zzcgc.zziyh);
                if (com_google_android_gms_internal_zzcgc.zziyi.length > 0) {
                    zza(stringBuilder, i2 + 1);
                    stringBuilder.append("expression_list {\n");
                    for (String str2 : com_google_android_gms_internal_zzcgc.zziyi) {
                        zza(stringBuilder, i2 + 2);
                        stringBuilder.append(str2);
                        stringBuilder.append("\n");
                    }
                    stringBuilder.append("}\n");
                }
                zza(stringBuilder, i2);
                stringBuilder.append("}\n");
            }
            zza(stringBuilder, i + 1, "number_filter", com_google_android_gms_internal_zzcfz.zzixu);
            zza(stringBuilder, i);
            stringBuilder.append("}\n");
        }
    }

    private final void zza(StringBuilder stringBuilder, int i, String str, zzcga com_google_android_gms_internal_zzcga) {
        if (com_google_android_gms_internal_zzcga != null) {
            zza(stringBuilder, i);
            stringBuilder.append(str);
            stringBuilder.append(" {\n");
            if (com_google_android_gms_internal_zzcga.zzixx != null) {
                Object obj = "UNKNOWN_COMPARISON_TYPE";
                switch (com_google_android_gms_internal_zzcga.zzixx.intValue()) {
                    case 1:
                        obj = "LESS_THAN";
                        break;
                    case 2:
                        obj = "GREATER_THAN";
                        break;
                    case 3:
                        obj = "EQUAL";
                        break;
                    case 4:
                        obj = "BETWEEN";
                        break;
                }
                zza(stringBuilder, i, "comparison_type", obj);
            }
            zza(stringBuilder, i, "match_as_float", com_google_android_gms_internal_zzcga.zzixy);
            zza(stringBuilder, i, "comparison_value", com_google_android_gms_internal_zzcga.zzixz);
            zza(stringBuilder, i, "min_comparison_value", com_google_android_gms_internal_zzcga.zziya);
            zza(stringBuilder, i, "max_comparison_value", com_google_android_gms_internal_zzcga.zziyb);
            zza(stringBuilder, i);
            stringBuilder.append("}\n");
        }
    }

    private static void zza(StringBuilder stringBuilder, int i, String str, zzcgl com_google_android_gms_internal_zzcgl) {
        int i2 = 0;
        if (com_google_android_gms_internal_zzcgl != null) {
            int i3;
            int i4;
            int i5 = i + 1;
            zza(stringBuilder, i5);
            stringBuilder.append(str);
            stringBuilder.append(" {\n");
            if (com_google_android_gms_internal_zzcgl.zzjah != null) {
                zza(stringBuilder, i5 + 1);
                stringBuilder.append("results: ");
                long[] jArr = com_google_android_gms_internal_zzcgl.zzjah;
                int length = jArr.length;
                i3 = 0;
                i4 = 0;
                while (i3 < length) {
                    Long valueOf = Long.valueOf(jArr[i3]);
                    int i6 = i4 + 1;
                    if (i4 != 0) {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append(valueOf);
                    i3++;
                    i4 = i6;
                }
                stringBuilder.append('\n');
            }
            if (com_google_android_gms_internal_zzcgl.zzjag != null) {
                zza(stringBuilder, i5 + 1);
                stringBuilder.append("status: ");
                long[] jArr2 = com_google_android_gms_internal_zzcgl.zzjag;
                int length2 = jArr2.length;
                i3 = 0;
                while (i2 < length2) {
                    Long valueOf2 = Long.valueOf(jArr2[i2]);
                    i4 = i3 + 1;
                    if (i3 != 0) {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append(valueOf2);
                    i2++;
                    i3 = i4;
                }
                stringBuilder.append('\n');
            }
            zza(stringBuilder, i5);
            stringBuilder.append("}\n");
        }
    }

    private static void zza(StringBuilder stringBuilder, int i, String str, Object obj) {
        if (obj != null) {
            zza(stringBuilder, i + 1);
            stringBuilder.append(str);
            stringBuilder.append(": ");
            stringBuilder.append(obj);
            stringBuilder.append('\n');
        }
    }

    private final void zza(StringBuilder stringBuilder, int i, zzcgg[] com_google_android_gms_internal_zzcggArr) {
        if (com_google_android_gms_internal_zzcggArr != null) {
            for (zzcgg com_google_android_gms_internal_zzcgg : com_google_android_gms_internal_zzcggArr) {
                if (com_google_android_gms_internal_zzcgg != null) {
                    zza(stringBuilder, 2);
                    stringBuilder.append("audience_membership {\n");
                    zza(stringBuilder, 2, "audience_id", com_google_android_gms_internal_zzcgg.zzixj);
                    zza(stringBuilder, 2, "new_audience", com_google_android_gms_internal_zzcgg.zziyv);
                    zza(stringBuilder, 2, "current_data", com_google_android_gms_internal_zzcgg.zziyt);
                    zza(stringBuilder, 2, "previous_data", com_google_android_gms_internal_zzcgg.zziyu);
                    zza(stringBuilder, 2);
                    stringBuilder.append("}\n");
                }
            }
        }
    }

    private final void zza(StringBuilder stringBuilder, int i, zzcgh[] com_google_android_gms_internal_zzcghArr) {
        if (com_google_android_gms_internal_zzcghArr != null) {
            for (zzcgh com_google_android_gms_internal_zzcgh : com_google_android_gms_internal_zzcghArr) {
                if (com_google_android_gms_internal_zzcgh != null) {
                    zza(stringBuilder, 2);
                    stringBuilder.append("event {\n");
                    zza(stringBuilder, 2, "name", zzjc(com_google_android_gms_internal_zzcgh.name));
                    zza(stringBuilder, 2, "timestamp_millis", com_google_android_gms_internal_zzcgh.zziyy);
                    zza(stringBuilder, 2, "previous_timestamp_millis", com_google_android_gms_internal_zzcgh.zziyz);
                    zza(stringBuilder, 2, "count", com_google_android_gms_internal_zzcgh.count);
                    zzcgi[] com_google_android_gms_internal_zzcgiArr = com_google_android_gms_internal_zzcgh.zziyx;
                    if (com_google_android_gms_internal_zzcgiArr != null) {
                        for (zzcgi com_google_android_gms_internal_zzcgi : com_google_android_gms_internal_zzcgiArr) {
                            if (com_google_android_gms_internal_zzcgi != null) {
                                zza(stringBuilder, 3);
                                stringBuilder.append("param {\n");
                                zza(stringBuilder, 3, "name", zzjd(com_google_android_gms_internal_zzcgi.name));
                                zza(stringBuilder, 3, "string_value", com_google_android_gms_internal_zzcgi.zzfwo);
                                zza(stringBuilder, 3, "int_value", com_google_android_gms_internal_zzcgi.zzizb);
                                zza(stringBuilder, 3, "double_value", com_google_android_gms_internal_zzcgi.zzixc);
                                zza(stringBuilder, 3);
                                stringBuilder.append("}\n");
                            }
                        }
                    }
                    zza(stringBuilder, 2);
                    stringBuilder.append("}\n");
                }
            }
        }
    }

    private final void zza(StringBuilder stringBuilder, int i, zzcgm[] com_google_android_gms_internal_zzcgmArr) {
        if (com_google_android_gms_internal_zzcgmArr != null) {
            for (zzcgm com_google_android_gms_internal_zzcgm : com_google_android_gms_internal_zzcgmArr) {
                if (com_google_android_gms_internal_zzcgm != null) {
                    zza(stringBuilder, 2);
                    stringBuilder.append("user_property {\n");
                    zza(stringBuilder, 2, "set_timestamp_millis", com_google_android_gms_internal_zzcgm.zzjaj);
                    zza(stringBuilder, 2, "name", zzje(com_google_android_gms_internal_zzcgm.name));
                    zza(stringBuilder, 2, "string_value", com_google_android_gms_internal_zzcgm.zzfwo);
                    zza(stringBuilder, 2, "int_value", com_google_android_gms_internal_zzcgm.zzizb);
                    zza(stringBuilder, 2, "double_value", com_google_android_gms_internal_zzcgm.zzixc);
                    zza(stringBuilder, 2);
                    stringBuilder.append("}\n");
                }
            }
        }
    }

    private final boolean zzayc() {
        return this.zziki.zzaul().zzad(3);
    }

    private final String zzb(zzcbh com_google_android_gms_internal_zzcbh) {
        return com_google_android_gms_internal_zzcbh == null ? null : !zzayc() ? com_google_android_gms_internal_zzcbh.toString() : zzx(com_google_android_gms_internal_zzcbh.zzaxz());
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    protected final String zza(zzcbf com_google_android_gms_internal_zzcbf) {
        if (com_google_android_gms_internal_zzcbf == null) {
            return null;
        }
        if (!zzayc()) {
            return com_google_android_gms_internal_zzcbf.toString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Event{appId='");
        stringBuilder.append(com_google_android_gms_internal_zzcbf.mAppId);
        stringBuilder.append("', name='");
        stringBuilder.append(zzjc(com_google_android_gms_internal_zzcbf.mName));
        stringBuilder.append("', params=");
        stringBuilder.append(zzb(com_google_android_gms_internal_zzcbf.zzink));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    protected final String zza(zzcfy com_google_android_gms_internal_zzcfy) {
        int i = 0;
        if (com_google_android_gms_internal_zzcfy == null) {
            return "null";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nevent_filter {\n");
        zza(stringBuilder, 0, "filter_id", com_google_android_gms_internal_zzcfy.zzixn);
        zza(stringBuilder, 0, "event_name", zzjc(com_google_android_gms_internal_zzcfy.zzixo));
        zza(stringBuilder, 1, "event_count_filter", com_google_android_gms_internal_zzcfy.zzixr);
        stringBuilder.append("  filters {\n");
        zzcfz[] com_google_android_gms_internal_zzcfzArr = com_google_android_gms_internal_zzcfy.zzixp;
        int length = com_google_android_gms_internal_zzcfzArr.length;
        while (i < length) {
            zza(stringBuilder, 2, com_google_android_gms_internal_zzcfzArr[i]);
            i++;
        }
        zza(stringBuilder, 1);
        stringBuilder.append("}\n}\n");
        return stringBuilder.toString();
    }

    protected final String zza(zzcgb com_google_android_gms_internal_zzcgb) {
        if (com_google_android_gms_internal_zzcgb == null) {
            return "null";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nproperty_filter {\n");
        zza(stringBuilder, 0, "filter_id", com_google_android_gms_internal_zzcgb.zzixn);
        zza(stringBuilder, 0, "property_name", zzje(com_google_android_gms_internal_zzcgb.zziyd));
        zza(stringBuilder, 1, com_google_android_gms_internal_zzcgb.zziye);
        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }

    protected final String zza(zzcgj com_google_android_gms_internal_zzcgj) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nbatch {\n");
        if (com_google_android_gms_internal_zzcgj.zzizc != null) {
            for (zzcgk com_google_android_gms_internal_zzcgk : com_google_android_gms_internal_zzcgj.zzizc) {
                if (!(com_google_android_gms_internal_zzcgk == null || com_google_android_gms_internal_zzcgk == null)) {
                    zza(stringBuilder, 1);
                    stringBuilder.append("bundle {\n");
                    zza(stringBuilder, 1, "protocol_version", com_google_android_gms_internal_zzcgk.zzize);
                    zza(stringBuilder, 1, ApiConstants.PLATFORM, com_google_android_gms_internal_zzcgk.zzizm);
                    zza(stringBuilder, 1, "gmp_version", com_google_android_gms_internal_zzcgk.zzizq);
                    zza(stringBuilder, 1, "uploading_gmp_version", com_google_android_gms_internal_zzcgk.zzizr);
                    zza(stringBuilder, 1, "config_version", com_google_android_gms_internal_zzcgk.zzjad);
                    zza(stringBuilder, 1, "gmp_app_id", com_google_android_gms_internal_zzcgk.zzilu);
                    zza(stringBuilder, 1, "app_id", com_google_android_gms_internal_zzcgk.zzci);
                    zza(stringBuilder, 1, "app_version", com_google_android_gms_internal_zzcgk.zzhtt);
                    zza(stringBuilder, 1, "app_version_major", com_google_android_gms_internal_zzcgk.zzizz);
                    zza(stringBuilder, 1, "firebase_instance_id", com_google_android_gms_internal_zzcgk.zzimc);
                    zza(stringBuilder, 1, "dev_cert_hash", com_google_android_gms_internal_zzcgk.zzizv);
                    zza(stringBuilder, 1, "app_store", com_google_android_gms_internal_zzcgk.zzilv);
                    zza(stringBuilder, 1, "upload_timestamp_millis", com_google_android_gms_internal_zzcgk.zzizh);
                    zza(stringBuilder, 1, "start_timestamp_millis", com_google_android_gms_internal_zzcgk.zzizi);
                    zza(stringBuilder, 1, "end_timestamp_millis", com_google_android_gms_internal_zzcgk.zzizj);
                    zza(stringBuilder, 1, "previous_bundle_start_timestamp_millis", com_google_android_gms_internal_zzcgk.zzizk);
                    zza(stringBuilder, 1, "previous_bundle_end_timestamp_millis", com_google_android_gms_internal_zzcgk.zzizl);
                    zza(stringBuilder, 1, "app_instance_id", com_google_android_gms_internal_zzcgk.zzizu);
                    zza(stringBuilder, 1, "resettable_device_id", com_google_android_gms_internal_zzcgk.zzizs);
                    zza(stringBuilder, 1, ApiConstants.DEVICE_ID, com_google_android_gms_internal_zzcgk.zzjac);
                    zza(stringBuilder, 1, "limited_ad_tracking", com_google_android_gms_internal_zzcgk.zzizt);
                    zza(stringBuilder, 1, "os_version", com_google_android_gms_internal_zzcgk.zzcw);
                    zza(stringBuilder, 1, "device_model", com_google_android_gms_internal_zzcgk.zzizn);
                    zza(stringBuilder, 1, "user_default_language", com_google_android_gms_internal_zzcgk.zzizo);
                    zza(stringBuilder, 1, "time_zone_offset_minutes", com_google_android_gms_internal_zzcgk.zzizp);
                    zza(stringBuilder, 1, "bundle_sequential_index", com_google_android_gms_internal_zzcgk.zzizw);
                    zza(stringBuilder, 1, "service_upload", com_google_android_gms_internal_zzcgk.zzizx);
                    zza(stringBuilder, 1, "health_monitor", com_google_android_gms_internal_zzcgk.zzily);
                    if (com_google_android_gms_internal_zzcgk.zzjae.longValue() != 0) {
                        zza(stringBuilder, 1, "android_id", com_google_android_gms_internal_zzcgk.zzjae);
                    }
                    zza(stringBuilder, 1, com_google_android_gms_internal_zzcgk.zzizg);
                    zza(stringBuilder, 1, com_google_android_gms_internal_zzcgk.zzizy);
                    zza(stringBuilder, 1, com_google_android_gms_internal_zzcgk.zzizf);
                    zza(stringBuilder, 1);
                    stringBuilder.append("}\n");
                }
            }
        }
        stringBuilder.append("}\n");
        return stringBuilder.toString();
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

    protected final String zzb(zzcbk com_google_android_gms_internal_zzcbk) {
        if (com_google_android_gms_internal_zzcbk == null) {
            return null;
        }
        if (!zzayc()) {
            return com_google_android_gms_internal_zzcbk.toString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("origin=");
        stringBuilder.append(com_google_android_gms_internal_zzcbk.zzimg);
        stringBuilder.append(",name=");
        stringBuilder.append(zzjc(com_google_android_gms_internal_zzcbk.name));
        stringBuilder.append(",params=");
        stringBuilder.append(zzb(com_google_android_gms_internal_zzcbk.zzinr));
        return stringBuilder.toString();
    }

    protected final String zzjc(String str) {
        return str == null ? null : zzayc() ? zza(str, Event.zzikk, Event.zzikj, zzipo) : str;
    }

    protected final String zzjd(String str) {
        return str == null ? null : zzayc() ? zza(str, Param.zzikm, Param.zzikl, zzipp) : str;
    }

    protected final String zzje(String str) {
        if (str == null) {
            return null;
        }
        if (!zzayc()) {
            return str;
        }
        if (!str.startsWith("_exp_")) {
            return zza(str, UserProperty.zzikr, UserProperty.zzikq, zzipq);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("experiment_id");
        stringBuilder.append("(");
        stringBuilder.append(str);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public final /* bridge */ /* synthetic */ void zzuj() {
        super.zzuj();
    }

    protected final void zzuk() {
    }

    public final /* bridge */ /* synthetic */ zzd zzvx() {
        return super.zzvx();
    }

    protected final String zzx(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        if (!zzayc()) {
            return bundle.toString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : bundle.keySet()) {
            if (stringBuilder.length() != 0) {
                stringBuilder.append(", ");
            } else {
                stringBuilder.append("Bundle[{");
            }
            stringBuilder.append(zzjd(str));
            stringBuilder.append("=");
            stringBuilder.append(bundle.get(str));
        }
        stringBuilder.append("}]");
        return stringBuilder.toString();
    }
}
