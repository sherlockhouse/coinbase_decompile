package com.google.android.gms.internal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbp;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

final class zzcau extends zzcdu {
    zzcau(zzccw com_google_android_gms_internal_zzccw) {
        super(com_google_android_gms_internal_zzccw);
    }

    private final Boolean zza(double d, zzcga com_google_android_gms_internal_zzcga) {
        try {
            return zza(new BigDecimal(d), com_google_android_gms_internal_zzcga, Math.ulp(d));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private final Boolean zza(long j, zzcga com_google_android_gms_internal_zzcga) {
        try {
            return zza(new BigDecimal(j), com_google_android_gms_internal_zzcga, 0.0d);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private final Boolean zza(zzcfy com_google_android_gms_internal_zzcfy, zzcgh com_google_android_gms_internal_zzcgh, long j) {
        Boolean zza;
        if (com_google_android_gms_internal_zzcfy.zzixr != null) {
            zza = zza(j, com_google_android_gms_internal_zzcfy.zzixr);
            if (zza == null) {
                return null;
            }
            if (!zza.booleanValue()) {
                return Boolean.valueOf(false);
            }
        }
        Set hashSet = new HashSet();
        for (zzcfz com_google_android_gms_internal_zzcfz : com_google_android_gms_internal_zzcfy.zzixp) {
            if (TextUtils.isEmpty(com_google_android_gms_internal_zzcfz.zzixw)) {
                zzaul().zzayf().zzj("null or empty param name in filter. event", zzaug().zzjc(com_google_android_gms_internal_zzcgh.name));
                return null;
            }
            hashSet.add(com_google_android_gms_internal_zzcfz.zzixw);
        }
        Map arrayMap = new ArrayMap();
        for (zzcgi com_google_android_gms_internal_zzcgi : com_google_android_gms_internal_zzcgh.zziyx) {
            if (hashSet.contains(com_google_android_gms_internal_zzcgi.name)) {
                if (com_google_android_gms_internal_zzcgi.zzizb != null) {
                    arrayMap.put(com_google_android_gms_internal_zzcgi.name, com_google_android_gms_internal_zzcgi.zzizb);
                } else if (com_google_android_gms_internal_zzcgi.zzixc != null) {
                    arrayMap.put(com_google_android_gms_internal_zzcgi.name, com_google_android_gms_internal_zzcgi.zzixc);
                } else if (com_google_android_gms_internal_zzcgi.zzfwo != null) {
                    arrayMap.put(com_google_android_gms_internal_zzcgi.name, com_google_android_gms_internal_zzcgi.zzfwo);
                } else {
                    zzaul().zzayf().zze("Unknown value for param. event, param", zzaug().zzjc(com_google_android_gms_internal_zzcgh.name), zzaug().zzjd(com_google_android_gms_internal_zzcgi.name));
                    return null;
                }
            }
        }
        for (zzcfz com_google_android_gms_internal_zzcfz2 : com_google_android_gms_internal_zzcfy.zzixp) {
            boolean equals = Boolean.TRUE.equals(com_google_android_gms_internal_zzcfz2.zzixv);
            String str = com_google_android_gms_internal_zzcfz2.zzixw;
            if (TextUtils.isEmpty(str)) {
                zzaul().zzayf().zzj("Event has empty param name. event", zzaug().zzjc(com_google_android_gms_internal_zzcgh.name));
                return null;
            }
            Object obj = arrayMap.get(str);
            if (obj instanceof Long) {
                if (com_google_android_gms_internal_zzcfz2.zzixu == null) {
                    zzaul().zzayf().zze("No number filter for long param. event, param", zzaug().zzjc(com_google_android_gms_internal_zzcgh.name), zzaug().zzjd(str));
                    return null;
                }
                zza = zza(((Long) obj).longValue(), com_google_android_gms_internal_zzcfz2.zzixu);
                if (zza == null) {
                    return null;
                }
                if (((!zza.booleanValue() ? 1 : 0) ^ equals) != 0) {
                    return Boolean.valueOf(false);
                }
            } else if (obj instanceof Double) {
                if (com_google_android_gms_internal_zzcfz2.zzixu == null) {
                    zzaul().zzayf().zze("No number filter for double param. event, param", zzaug().zzjc(com_google_android_gms_internal_zzcgh.name), zzaug().zzjd(str));
                    return null;
                }
                zza = zza(((Double) obj).doubleValue(), com_google_android_gms_internal_zzcfz2.zzixu);
                if (zza == null) {
                    return null;
                }
                if (((!zza.booleanValue() ? 1 : 0) ^ equals) != 0) {
                    return Boolean.valueOf(false);
                }
            } else if (obj instanceof String) {
                if (com_google_android_gms_internal_zzcfz2.zzixt != null) {
                    zza = zza((String) obj, com_google_android_gms_internal_zzcfz2.zzixt);
                } else if (com_google_android_gms_internal_zzcfz2.zzixu == null) {
                    zzaul().zzayf().zze("No filter for String param. event, param", zzaug().zzjc(com_google_android_gms_internal_zzcgh.name), zzaug().zzjd(str));
                    return null;
                } else if (zzcfw.zzkf((String) obj)) {
                    zza = zza((String) obj, com_google_android_gms_internal_zzcfz2.zzixu);
                } else {
                    zzaul().zzayf().zze("Invalid param value for number filter. event, param", zzaug().zzjc(com_google_android_gms_internal_zzcgh.name), zzaug().zzjd(str));
                    return null;
                }
                if (zza == null) {
                    return null;
                }
                if (((!zza.booleanValue() ? 1 : 0) ^ equals) != 0) {
                    return Boolean.valueOf(false);
                }
            } else if (obj == null) {
                zzaul().zzayj().zze("Missing param for filter. event, param", zzaug().zzjc(com_google_android_gms_internal_zzcgh.name), zzaug().zzjd(str));
                return Boolean.valueOf(false);
            } else {
                zzaul().zzayf().zze("Unknown param type. event, param", zzaug().zzjc(com_google_android_gms_internal_zzcgh.name), zzaug().zzjd(str));
                return null;
            }
        }
        return Boolean.valueOf(true);
    }

    private static Boolean zza(Boolean bool, boolean z) {
        return bool == null ? null : Boolean.valueOf(bool.booleanValue() ^ z);
    }

    private final Boolean zza(String str, int i, boolean z, String str2, List<String> list, String str3) {
        if (str == null) {
            return null;
        }
        if (i == 6) {
            if (list == null || list.size() == 0) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!(z || i == 1)) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (i) {
            case 1:
                try {
                    return Boolean.valueOf(Pattern.compile(str3, z ? 0 : 66).matcher(str).matches());
                } catch (PatternSyntaxException e) {
                    zzaul().zzayf().zzj("Invalid regular expression in REGEXP audience filter. expression", str3);
                    return null;
                }
            case 2:
                return Boolean.valueOf(str.startsWith(str2));
            case 3:
                return Boolean.valueOf(str.endsWith(str2));
            case 4:
                return Boolean.valueOf(str.contains(str2));
            case 5:
                return Boolean.valueOf(str.equals(str2));
            case 6:
                return Boolean.valueOf(list.contains(str));
            default:
                return null;
        }
    }

    private final Boolean zza(String str, zzcga com_google_android_gms_internal_zzcga) {
        Boolean bool = null;
        if (zzcfw.zzkf(str)) {
            try {
                bool = zza(new BigDecimal(str), com_google_android_gms_internal_zzcga, 0.0d);
            } catch (NumberFormatException e) {
            }
        }
        return bool;
    }

    private final Boolean zza(String str, zzcgc com_google_android_gms_internal_zzcgc) {
        int i = 0;
        String str2 = null;
        zzbp.zzu(com_google_android_gms_internal_zzcgc);
        if (str == null || com_google_android_gms_internal_zzcgc.zziyf == null || com_google_android_gms_internal_zzcgc.zziyf.intValue() == 0) {
            return null;
        }
        List list;
        if (com_google_android_gms_internal_zzcgc.zziyf.intValue() == 6) {
            if (com_google_android_gms_internal_zzcgc.zziyi == null || com_google_android_gms_internal_zzcgc.zziyi.length == 0) {
                return null;
            }
        } else if (com_google_android_gms_internal_zzcgc.zziyg == null) {
            return null;
        }
        int intValue = com_google_android_gms_internal_zzcgc.zziyf.intValue();
        boolean z = com_google_android_gms_internal_zzcgc.zziyh != null && com_google_android_gms_internal_zzcgc.zziyh.booleanValue();
        String toUpperCase = (z || intValue == 1 || intValue == 6) ? com_google_android_gms_internal_zzcgc.zziyg : com_google_android_gms_internal_zzcgc.zziyg.toUpperCase(Locale.ENGLISH);
        if (com_google_android_gms_internal_zzcgc.zziyi == null) {
            list = null;
        } else {
            String[] strArr = com_google_android_gms_internal_zzcgc.zziyi;
            if (z) {
                list = Arrays.asList(strArr);
            } else {
                list = new ArrayList();
                int length = strArr.length;
                while (i < length) {
                    list.add(strArr[i].toUpperCase(Locale.ENGLISH));
                    i++;
                }
            }
        }
        if (intValue == 1) {
            str2 = toUpperCase;
        }
        return zza(str, intValue, z, toUpperCase, list, str2);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static Boolean zza(BigDecimal bigDecimal, zzcga com_google_android_gms_internal_zzcga, double d) {
        boolean z = true;
        zzbp.zzu(com_google_android_gms_internal_zzcga);
        if (com_google_android_gms_internal_zzcga.zzixx == null || com_google_android_gms_internal_zzcga.zzixx.intValue() == 0) {
            return null;
        }
        BigDecimal bigDecimal2;
        BigDecimal bigDecimal3;
        if (com_google_android_gms_internal_zzcga.zzixx.intValue() == 4) {
            if (com_google_android_gms_internal_zzcga.zziya == null || com_google_android_gms_internal_zzcga.zziyb == null) {
                return null;
            }
        } else if (com_google_android_gms_internal_zzcga.zzixz == null) {
            return null;
        }
        int intValue = com_google_android_gms_internal_zzcga.zzixx.intValue();
        BigDecimal bigDecimal4;
        if (com_google_android_gms_internal_zzcga.zzixx.intValue() == 4) {
            if (!zzcfw.zzkf(com_google_android_gms_internal_zzcga.zziya) || !zzcfw.zzkf(com_google_android_gms_internal_zzcga.zziyb)) {
                return null;
            }
            try {
                bigDecimal2 = new BigDecimal(com_google_android_gms_internal_zzcga.zziya);
                bigDecimal4 = new BigDecimal(com_google_android_gms_internal_zzcga.zziyb);
                bigDecimal3 = null;
            } catch (NumberFormatException e) {
                return null;
            }
        } else if (!zzcfw.zzkf(com_google_android_gms_internal_zzcga.zzixz)) {
            return null;
        } else {
            try {
                bigDecimal2 = null;
                bigDecimal3 = new BigDecimal(com_google_android_gms_internal_zzcga.zzixz);
                bigDecimal4 = null;
            } catch (NumberFormatException e2) {
                return null;
            }
        }
        if (intValue == 4) {
            if (bigDecimal2 == null) {
                return null;
            }
        }
        switch (intValue) {
            case 1:
                if (bigDecimal.compareTo(bigDecimal3) != -1) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 2:
                if (bigDecimal.compareTo(bigDecimal3) != 1) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 3:
                if (d != 0.0d) {
                    if (!(bigDecimal.compareTo(bigDecimal3.subtract(new BigDecimal(d).multiply(new BigDecimal(2)))) == 1 && bigDecimal.compareTo(bigDecimal3.add(new BigDecimal(d).multiply(new BigDecimal(2)))) == -1)) {
                        z = false;
                    }
                    return Boolean.valueOf(z);
                }
                if (bigDecimal.compareTo(bigDecimal3) != 0) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 4:
                if (bigDecimal.compareTo(bigDecimal2) == -1 || bigDecimal.compareTo(r3) == 1) {
                    z = false;
                }
                return Boolean.valueOf(z);
        }
        return null;
    }

    final zzcgg[] zza(String str, zzcgh[] com_google_android_gms_internal_zzcghArr, zzcgm[] com_google_android_gms_internal_zzcgmArr) {
        int intValue;
        BitSet bitSet;
        BitSet bitSet2;
        Map map;
        Map map2;
        zzbp.zzgg(str);
        Set hashSet = new HashSet();
        ArrayMap arrayMap = new ArrayMap();
        Map arrayMap2 = new ArrayMap();
        ArrayMap arrayMap3 = new ArrayMap();
        Map zziz = zzauf().zziz(str);
        if (zziz != null) {
            for (Integer intValue2 : zziz.keySet()) {
                intValue = intValue2.intValue();
                zzcgl com_google_android_gms_internal_zzcgl = (zzcgl) zziz.get(Integer.valueOf(intValue));
                bitSet = (BitSet) arrayMap2.get(Integer.valueOf(intValue));
                bitSet2 = (BitSet) arrayMap3.get(Integer.valueOf(intValue));
                if (bitSet == null) {
                    bitSet = new BitSet();
                    arrayMap2.put(Integer.valueOf(intValue), bitSet);
                    bitSet2 = new BitSet();
                    arrayMap3.put(Integer.valueOf(intValue), bitSet2);
                }
                for (int i = 0; i < (com_google_android_gms_internal_zzcgl.zzjag.length << 6); i++) {
                    if (zzcfw.zza(com_google_android_gms_internal_zzcgl.zzjag, i)) {
                        zzaul().zzayj().zze("Filter already evaluated. audience ID, filter ID", Integer.valueOf(intValue), Integer.valueOf(i));
                        bitSet2.set(i);
                        if (zzcfw.zza(com_google_android_gms_internal_zzcgl.zzjah, i)) {
                            bitSet.set(i);
                        }
                    }
                }
                zzcgg com_google_android_gms_internal_zzcgg = new zzcgg();
                arrayMap.put(Integer.valueOf(intValue), com_google_android_gms_internal_zzcgg);
                com_google_android_gms_internal_zzcgg.zziyv = Boolean.valueOf(false);
                com_google_android_gms_internal_zzcgg.zziyu = com_google_android_gms_internal_zzcgl;
                com_google_android_gms_internal_zzcgg.zziyt = new zzcgl();
                com_google_android_gms_internal_zzcgg.zziyt.zzjah = zzcfw.zza(bitSet);
                com_google_android_gms_internal_zzcgg.zziyt.zzjag = zzcfw.zza(bitSet2);
            }
        }
        if (com_google_android_gms_internal_zzcghArr != null) {
            ArrayMap arrayMap4 = new ArrayMap();
            for (zzcgh com_google_android_gms_internal_zzcgh : com_google_android_gms_internal_zzcghArr) {
                zzcbg com_google_android_gms_internal_zzcbg;
                zzcbg zzaf = zzauf().zzaf(str, com_google_android_gms_internal_zzcgh.name);
                if (zzaf == null) {
                    zzaul().zzayf().zze("Event aggregate wasn't created during raw event logging. appId, event", zzcbw.zzjf(str), zzaug().zzjc(com_google_android_gms_internal_zzcgh.name));
                    com_google_android_gms_internal_zzcbg = new zzcbg(str, com_google_android_gms_internal_zzcgh.name, 1, 1, com_google_android_gms_internal_zzcgh.zziyy.longValue());
                } else {
                    com_google_android_gms_internal_zzcbg = zzaf.zzaxy();
                }
                zzauf().zza(com_google_android_gms_internal_zzcbg);
                long j = com_google_android_gms_internal_zzcbg.zzinl;
                map = (Map) arrayMap4.get(com_google_android_gms_internal_zzcgh.name);
                if (map == null) {
                    map = zzauf().zzak(str, com_google_android_gms_internal_zzcgh.name);
                    if (map == null) {
                        map = new ArrayMap();
                    }
                    arrayMap4.put(com_google_android_gms_internal_zzcgh.name, map);
                    map2 = map;
                } else {
                    map2 = map;
                }
                for (Integer intValue22 : r7.keySet()) {
                    int intValue3 = intValue22.intValue();
                    if (hashSet.contains(Integer.valueOf(intValue3))) {
                        zzaul().zzayj().zzj("Skipping failed audience ID", Integer.valueOf(intValue3));
                    } else {
                        bitSet = (BitSet) arrayMap2.get(Integer.valueOf(intValue3));
                        bitSet2 = (BitSet) arrayMap3.get(Integer.valueOf(intValue3));
                        if (((zzcgg) arrayMap.get(Integer.valueOf(intValue3))) == null) {
                            zzcgg com_google_android_gms_internal_zzcgg2 = new zzcgg();
                            arrayMap.put(Integer.valueOf(intValue3), com_google_android_gms_internal_zzcgg2);
                            com_google_android_gms_internal_zzcgg2.zziyv = Boolean.valueOf(true);
                            bitSet = new BitSet();
                            arrayMap2.put(Integer.valueOf(intValue3), bitSet);
                            bitSet2 = new BitSet();
                            arrayMap3.put(Integer.valueOf(intValue3), bitSet2);
                        }
                        for (zzcfy com_google_android_gms_internal_zzcfy : (List) r7.get(Integer.valueOf(intValue3))) {
                            if (zzaul().zzad(2)) {
                                zzaul().zzayj().zzd("Evaluating filter. audience, filter, event", Integer.valueOf(intValue3), com_google_android_gms_internal_zzcfy.zzixn, zzaug().zzjc(com_google_android_gms_internal_zzcfy.zzixo));
                                zzaul().zzayj().zzj("Filter definition", zzaug().zza(com_google_android_gms_internal_zzcfy));
                            }
                            if (com_google_android_gms_internal_zzcfy.zzixn == null || com_google_android_gms_internal_zzcfy.zzixn.intValue() > 256) {
                                zzaul().zzayf().zze("Invalid event filter ID. appId, id", zzcbw.zzjf(str), String.valueOf(com_google_android_gms_internal_zzcfy.zzixn));
                            } else if (bitSet.get(com_google_android_gms_internal_zzcfy.zzixn.intValue())) {
                                zzaul().zzayj().zze("Event filter already evaluated true. audience ID, filter ID", Integer.valueOf(intValue3), com_google_android_gms_internal_zzcfy.zzixn);
                            } else {
                                Object obj;
                                Boolean zza = zza(com_google_android_gms_internal_zzcfy, com_google_android_gms_internal_zzcgh, j);
                                zzcby zzayj = zzaul().zzayj();
                                String str2 = "Event filter result";
                                if (zza == null) {
                                    obj = "null";
                                } else {
                                    Boolean bool = zza;
                                }
                                zzayj.zzj(str2, obj);
                                if (zza == null) {
                                    hashSet.add(Integer.valueOf(intValue3));
                                } else {
                                    bitSet2.set(com_google_android_gms_internal_zzcfy.zzixn.intValue());
                                    if (zza.booleanValue()) {
                                        bitSet.set(com_google_android_gms_internal_zzcfy.zzixn.intValue());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (com_google_android_gms_internal_zzcgmArr != null) {
            Map arrayMap5 = new ArrayMap();
            for (zzcgm com_google_android_gms_internal_zzcgm : com_google_android_gms_internal_zzcgmArr) {
                map = (Map) arrayMap5.get(com_google_android_gms_internal_zzcgm.name);
                if (map == null) {
                    map = zzauf().zzal(str, com_google_android_gms_internal_zzcgm.name);
                    if (map == null) {
                        map = new ArrayMap();
                    }
                    arrayMap5.put(com_google_android_gms_internal_zzcgm.name, map);
                    map2 = map;
                } else {
                    map2 = map;
                }
                for (Integer intValue222 : r7.keySet()) {
                    int intValue4 = intValue222.intValue();
                    if (hashSet.contains(Integer.valueOf(intValue4))) {
                        zzaul().zzayj().zzj("Skipping failed audience ID", Integer.valueOf(intValue4));
                    } else {
                        bitSet = (BitSet) arrayMap2.get(Integer.valueOf(intValue4));
                        bitSet2 = (BitSet) arrayMap3.get(Integer.valueOf(intValue4));
                        if (((zzcgg) arrayMap.get(Integer.valueOf(intValue4))) == null) {
                            com_google_android_gms_internal_zzcgg2 = new zzcgg();
                            arrayMap.put(Integer.valueOf(intValue4), com_google_android_gms_internal_zzcgg2);
                            com_google_android_gms_internal_zzcgg2.zziyv = Boolean.valueOf(true);
                            bitSet = new BitSet();
                            arrayMap2.put(Integer.valueOf(intValue4), bitSet);
                            bitSet2 = new BitSet();
                            arrayMap3.put(Integer.valueOf(intValue4), bitSet2);
                        }
                        for (zzcgb com_google_android_gms_internal_zzcgb : (List) r7.get(Integer.valueOf(intValue4))) {
                            if (zzaul().zzad(2)) {
                                zzaul().zzayj().zzd("Evaluating filter. audience, filter, property", Integer.valueOf(intValue4), com_google_android_gms_internal_zzcgb.zzixn, zzaug().zzje(com_google_android_gms_internal_zzcgb.zziyd));
                                zzaul().zzayj().zzj("Filter definition", zzaug().zza(com_google_android_gms_internal_zzcgb));
                            }
                            if (com_google_android_gms_internal_zzcgb.zzixn == null || com_google_android_gms_internal_zzcgb.zzixn.intValue() > 256) {
                                zzaul().zzayf().zze("Invalid property filter ID. appId, id", zzcbw.zzjf(str), String.valueOf(com_google_android_gms_internal_zzcgb.zzixn));
                                hashSet.add(Integer.valueOf(intValue4));
                                break;
                            } else if (bitSet.get(com_google_android_gms_internal_zzcgb.zzixn.intValue())) {
                                zzaul().zzayj().zze("Property filter already evaluated true. audience ID, filter ID", Integer.valueOf(intValue4), com_google_android_gms_internal_zzcgb.zzixn);
                            } else {
                                Object obj2;
                                zzcfz com_google_android_gms_internal_zzcfz = com_google_android_gms_internal_zzcgb.zziye;
                                if (com_google_android_gms_internal_zzcfz == null) {
                                    zzaul().zzayf().zzj("Missing property filter. property", zzaug().zzje(com_google_android_gms_internal_zzcgm.name));
                                    bool = null;
                                } else {
                                    boolean equals = Boolean.TRUE.equals(com_google_android_gms_internal_zzcfz.zzixv);
                                    if (com_google_android_gms_internal_zzcgm.zzizb != null) {
                                        if (com_google_android_gms_internal_zzcfz.zzixu == null) {
                                            zzaul().zzayf().zzj("No number filter for long property. property", zzaug().zzje(com_google_android_gms_internal_zzcgm.name));
                                            bool = null;
                                        } else {
                                            bool = zza(zza(com_google_android_gms_internal_zzcgm.zzizb.longValue(), com_google_android_gms_internal_zzcfz.zzixu), equals);
                                        }
                                    } else if (com_google_android_gms_internal_zzcgm.zzixc != null) {
                                        if (com_google_android_gms_internal_zzcfz.zzixu == null) {
                                            zzaul().zzayf().zzj("No number filter for double property. property", zzaug().zzje(com_google_android_gms_internal_zzcgm.name));
                                            bool = null;
                                        } else {
                                            bool = zza(zza(com_google_android_gms_internal_zzcgm.zzixc.doubleValue(), com_google_android_gms_internal_zzcfz.zzixu), equals);
                                        }
                                    } else if (com_google_android_gms_internal_zzcgm.zzfwo == null) {
                                        zzaul().zzayf().zzj("User property has no value, property", zzaug().zzje(com_google_android_gms_internal_zzcgm.name));
                                        bool = null;
                                    } else if (com_google_android_gms_internal_zzcfz.zzixt == null) {
                                        if (com_google_android_gms_internal_zzcfz.zzixu == null) {
                                            zzaul().zzayf().zzj("No string or number filter defined. property", zzaug().zzje(com_google_android_gms_internal_zzcgm.name));
                                        } else if (zzcfw.zzkf(com_google_android_gms_internal_zzcgm.zzfwo)) {
                                            bool = zza(zza(com_google_android_gms_internal_zzcgm.zzfwo, com_google_android_gms_internal_zzcfz.zzixu), equals);
                                        } else {
                                            zzaul().zzayf().zze("Invalid user property value for Numeric number filter. property, value", zzaug().zzje(com_google_android_gms_internal_zzcgm.name), com_google_android_gms_internal_zzcgm.zzfwo);
                                        }
                                        bool = null;
                                    } else {
                                        bool = zza(zza(com_google_android_gms_internal_zzcgm.zzfwo, com_google_android_gms_internal_zzcfz.zzixt), equals);
                                    }
                                }
                                zzcby zzayj2 = zzaul().zzayj();
                                String str3 = "Property filter result";
                                if (bool == null) {
                                    obj2 = "null";
                                } else {
                                    zza = bool;
                                }
                                zzayj2.zzj(str3, obj2);
                                if (bool == null) {
                                    hashSet.add(Integer.valueOf(intValue4));
                                } else {
                                    bitSet2.set(com_google_android_gms_internal_zzcgb.zzixn.intValue());
                                    if (bool.booleanValue()) {
                                        bitSet.set(com_google_android_gms_internal_zzcgb.zzixn.intValue());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        zzcgg[] com_google_android_gms_internal_zzcggArr = new zzcgg[arrayMap2.size()];
        int i2 = 0;
        for (Integer intValue2222 : arrayMap2.keySet()) {
            intValue = intValue2222.intValue();
            if (!hashSet.contains(Integer.valueOf(intValue))) {
                com_google_android_gms_internal_zzcgg2 = (zzcgg) arrayMap.get(Integer.valueOf(intValue));
                com_google_android_gms_internal_zzcgg = com_google_android_gms_internal_zzcgg2 == null ? new zzcgg() : com_google_android_gms_internal_zzcgg2;
                int i3 = i2 + 1;
                com_google_android_gms_internal_zzcggArr[i2] = com_google_android_gms_internal_zzcgg;
                com_google_android_gms_internal_zzcgg.zzixj = Integer.valueOf(intValue);
                com_google_android_gms_internal_zzcgg.zziyt = new zzcgl();
                com_google_android_gms_internal_zzcgg.zziyt.zzjah = zzcfw.zza((BitSet) arrayMap2.get(Integer.valueOf(intValue)));
                com_google_android_gms_internal_zzcgg.zziyt.zzjag = zzcfw.zza((BitSet) arrayMap3.get(Integer.valueOf(intValue)));
                zzcdt zzauf = zzauf();
                zzeyn com_google_android_gms_internal_zzeyn = com_google_android_gms_internal_zzcgg.zziyt;
                zzauf.zzwk();
                zzauf.zzuj();
                zzbp.zzgg(str);
                zzbp.zzu(com_google_android_gms_internal_zzeyn);
                try {
                    byte[] bArr = new byte[com_google_android_gms_internal_zzeyn.zzhi()];
                    zzeyf zzn = zzeyf.zzn(bArr, 0, bArr.length);
                    com_google_android_gms_internal_zzeyn.zza(zzn);
                    zzn.zzctn();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("app_id", str);
                    contentValues.put("audience_id", Integer.valueOf(intValue));
                    contentValues.put("current_results", bArr);
                    try {
                        if (zzauf.getWritableDatabase().insertWithOnConflict("audience_filter_values", null, contentValues, 5) == -1) {
                            zzauf.zzaul().zzayd().zzj("Failed to insert filter results (got -1). appId", zzcbw.zzjf(str));
                        }
                        i2 = i3;
                    } catch (SQLiteException e) {
                        zzauf.zzaul().zzayd().zze("Error storing filter results. appId", zzcbw.zzjf(str), e);
                        i2 = i3;
                    }
                } catch (IOException e2) {
                    zzauf.zzaul().zzayd().zze("Configuration loss. Failed to serialize filter results. appId", zzcbw.zzjf(str), e2);
                    i2 = i3;
                }
            }
        }
        return (zzcgg[]) Arrays.copyOf(com_google_android_gms_internal_zzcggArr, i2);
    }

    protected final void zzuk() {
    }
}
