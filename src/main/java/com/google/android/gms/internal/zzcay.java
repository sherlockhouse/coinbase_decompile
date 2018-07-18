package com.google.android.gms.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Pair;
import com.coinbase.android.utils.CryptoUri;
import com.google.android.gms.common.internal.zzbp;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

final class zzcay extends zzcdu {
    private static final Map<String, String> zzimr;
    private static final Map<String, String> zzims;
    private static final Map<String, String> zzimt;
    private static final Map<String, String> zzimu;
    private static final Map<String, String> zzimv;
    private final zzcbb zzimw = new zzcbb(this, getContext(), zzcax.zzawi());
    private final zzcfq zzimx = new zzcfq(zzvx());

    static {
        Map arrayMap = new ArrayMap(1);
        zzimr = arrayMap;
        arrayMap.put("origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;");
        arrayMap = new ArrayMap(18);
        zzims = arrayMap;
        arrayMap.put("app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;");
        zzims.put("app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;");
        zzims.put("gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;");
        zzims.put("dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;");
        zzims.put("measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;");
        zzims.put("last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;");
        zzims.put("day", "ALTER TABLE apps ADD COLUMN day INTEGER;");
        zzims.put("daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;");
        zzims.put("daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;");
        zzims.put("daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;");
        zzims.put("remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;");
        zzims.put("config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;");
        zzims.put("failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;");
        zzims.put("app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;");
        zzims.put("firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;");
        zzims.put("daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;");
        zzims.put("daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;");
        zzims.put("health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;");
        zzims.put("android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;");
        arrayMap = new ArrayMap(1);
        zzimt = arrayMap;
        arrayMap.put("realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;");
        arrayMap = new ArrayMap(1);
        zzimu = arrayMap;
        arrayMap.put("has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;");
        arrayMap = new ArrayMap(1);
        zzimv = arrayMap;
        arrayMap.put("previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;");
    }

    zzcay(zzccw com_google_android_gms_internal_zzccw) {
        super(com_google_android_gms_internal_zzccw);
    }

    private final long zza(String str, String[] strArr, long j) {
        Cursor cursor = null;
        try {
            cursor = getWritableDatabase().rawQuery(str, strArr);
            if (cursor.moveToFirst()) {
                j = cursor.getLong(0);
                if (cursor != null) {
                    cursor.close();
                }
            } else if (cursor != null) {
                cursor.close();
            }
            return j;
        } catch (SQLiteException e) {
            zzaul().zzayd().zze("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private final Object zza(Cursor cursor, int i) {
        int type = cursor.getType(i);
        switch (type) {
            case 0:
                zzaul().zzayd().log("Loaded invalid null value from database");
                return null;
            case 1:
                return Long.valueOf(cursor.getLong(i));
            case 2:
                return Double.valueOf(cursor.getDouble(i));
            case 3:
                return cursor.getString(i);
            case 4:
                zzaul().zzayd().log("Loaded invalid blob type value, ignoring it");
                return null;
            default:
                zzaul().zzayd().zzj("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
                return null;
        }
    }

    private static void zza(ContentValues contentValues, String str, Object obj) {
        zzbp.zzgg(str);
        zzbp.zzu(obj);
        if (obj instanceof String) {
            contentValues.put(str, (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put(str, (Long) obj);
        } else if (obj instanceof Double) {
            contentValues.put(str, (Double) obj);
        } else {
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    static void zza(zzcbw com_google_android_gms_internal_zzcbw, SQLiteDatabase sQLiteDatabase) {
        if (com_google_android_gms_internal_zzcbw == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        File file = new File(sQLiteDatabase.getPath());
        if (!file.setReadable(false, false)) {
            com_google_android_gms_internal_zzcbw.zzayf().log("Failed to turn off database read permission");
        }
        if (!file.setWritable(false, false)) {
            com_google_android_gms_internal_zzcbw.zzayf().log("Failed to turn off database write permission");
        }
        if (!file.setReadable(true, true)) {
            com_google_android_gms_internal_zzcbw.zzayf().log("Failed to turn on database read permission for owner");
        }
        if (!file.setWritable(true, true)) {
            com_google_android_gms_internal_zzcbw.zzayf().log("Failed to turn on database write permission for owner");
        }
    }

    static void zza(zzcbw com_google_android_gms_internal_zzcbw, SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, Map<String, String> map) throws SQLiteException {
        if (com_google_android_gms_internal_zzcbw == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        if (!zza(com_google_android_gms_internal_zzcbw, sQLiteDatabase, str)) {
            sQLiteDatabase.execSQL(str2);
        }
        try {
            zza(com_google_android_gms_internal_zzcbw, sQLiteDatabase, str, str3, map);
        } catch (SQLiteException e) {
            com_google_android_gms_internal_zzcbw.zzayd().zzj("Failed to verify columns on table that was just created", str);
            throw e;
        }
    }

    private static void zza(zzcbw com_google_android_gms_internal_zzcbw, SQLiteDatabase sQLiteDatabase, String str, String str2, Map<String, String> map) throws SQLiteException {
        if (com_google_android_gms_internal_zzcbw == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        Iterable zzb = zzb(sQLiteDatabase, str);
        String[] split = str2.split(",");
        int length = split.length;
        int i = 0;
        while (i < length) {
            String str3 = split[i];
            if (zzb.remove(str3)) {
                i++;
            } else {
                throw new SQLiteException(new StringBuilder((String.valueOf(str).length() + 35) + String.valueOf(str3).length()).append("Table ").append(str).append(" is missing required column: ").append(str3).toString());
            }
        }
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                if (!zzb.remove(entry.getKey())) {
                    sQLiteDatabase.execSQL((String) entry.getValue());
                }
            }
        }
        if (!zzb.isEmpty()) {
            com_google_android_gms_internal_zzcbw.zzayf().zze("Table has extra columns. table, columns", str, TextUtils.join(", ", zzb));
        }
    }

    private static boolean zza(zzcbw com_google_android_gms_internal_zzcbw, SQLiteDatabase sQLiteDatabase, String str) {
        Cursor query;
        Object e;
        Throwable th;
        Cursor cursor = null;
        if (com_google_android_gms_internal_zzcbw == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        try {
            SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
            query = sQLiteDatabase2.query("SQLITE_MASTER", new String[]{"name"}, "name=?", new String[]{str}, null, null, null);
            try {
                boolean moveToFirst = query.moveToFirst();
                if (query == null) {
                    return moveToFirst;
                }
                query.close();
                return moveToFirst;
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    com_google_android_gms_internal_zzcbw.zzayf().zze("Error querying for table", str, e);
                    if (query != null) {
                        query.close();
                    }
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            query = null;
            com_google_android_gms_internal_zzcbw.zzayf().zze("Error querying for table", str, e);
            if (query != null) {
                query.close();
            }
            return false;
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private final boolean zza(String str, int i, zzcfy com_google_android_gms_internal_zzcfy) {
        zzwk();
        zzuj();
        zzbp.zzgg(str);
        zzbp.zzu(com_google_android_gms_internal_zzcfy);
        if (TextUtils.isEmpty(com_google_android_gms_internal_zzcfy.zzixo)) {
            zzaul().zzayf().zzd("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", zzcbw.zzjf(str), Integer.valueOf(i), String.valueOf(com_google_android_gms_internal_zzcfy.zzixn));
            return false;
        }
        try {
            byte[] bArr = new byte[com_google_android_gms_internal_zzcfy.zzhi()];
            zzeyf zzn = zzeyf.zzn(bArr, 0, bArr.length);
            com_google_android_gms_internal_zzcfy.zza(zzn);
            zzn.zzctn();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", com_google_android_gms_internal_zzcfy.zzixn);
            contentValues.put("event_name", com_google_android_gms_internal_zzcfy.zzixo);
            contentValues.put("data", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("event_filters", null, contentValues, 5) == -1) {
                    zzaul().zzayd().zzj("Failed to insert event filter (got -1). appId", zzcbw.zzjf(str));
                }
                return true;
            } catch (SQLiteException e) {
                zzaul().zzayd().zze("Error storing event filter. appId", zzcbw.zzjf(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzaul().zzayd().zze("Configuration loss. Failed to serialize event filter. appId", zzcbw.zzjf(str), e2);
            return false;
        }
    }

    private final boolean zza(String str, int i, zzcgb com_google_android_gms_internal_zzcgb) {
        zzwk();
        zzuj();
        zzbp.zzgg(str);
        zzbp.zzu(com_google_android_gms_internal_zzcgb);
        if (TextUtils.isEmpty(com_google_android_gms_internal_zzcgb.zziyd)) {
            zzaul().zzayf().zzd("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", zzcbw.zzjf(str), Integer.valueOf(i), String.valueOf(com_google_android_gms_internal_zzcgb.zzixn));
            return false;
        }
        try {
            byte[] bArr = new byte[com_google_android_gms_internal_zzcgb.zzhi()];
            zzeyf zzn = zzeyf.zzn(bArr, 0, bArr.length);
            com_google_android_gms_internal_zzcgb.zza(zzn);
            zzn.zzctn();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", com_google_android_gms_internal_zzcgb.zzixn);
            contentValues.put("property_name", com_google_android_gms_internal_zzcgb.zziyd);
            contentValues.put("data", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("property_filters", null, contentValues, 5) != -1) {
                    return true;
                }
                zzaul().zzayd().zzj("Failed to insert property filter (got -1). appId", zzcbw.zzjf(str));
                return false;
            } catch (SQLiteException e) {
                zzaul().zzayd().zze("Error storing property filter. appId", zzcbw.zzjf(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzaul().zzayd().zze("Configuration loss. Failed to serialize property filter. appId", zzcbw.zzjf(str), e2);
            return false;
        }
    }

    private final boolean zzaxq() {
        return getContext().getDatabasePath(zzcax.zzawi()).exists();
    }

    private final long zzb(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            cursor = getWritableDatabase().rawQuery(str, strArr);
            if (cursor.moveToFirst()) {
                long j = cursor.getLong(0);
                if (cursor != null) {
                    cursor.close();
                }
                return j;
            }
            throw new SQLiteException("Database returned empty set");
        } catch (SQLiteException e) {
            zzaul().zzayd().zze("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private static Set<String> zzb(SQLiteDatabase sQLiteDatabase, String str) {
        Set<String> hashSet = new HashSet();
        Cursor rawQuery = sQLiteDatabase.rawQuery(new StringBuilder(String.valueOf(str).length() + 22).append("SELECT * FROM ").append(str).append(" LIMIT 0").toString(), null);
        try {
            Collections.addAll(hashSet, rawQuery.getColumnNames());
            return hashSet;
        } finally {
            rawQuery.close();
        }
    }

    private final boolean zzc(String str, List<Integer> list) {
        zzbp.zzgg(str);
        zzwk();
        zzuj();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        try {
            if (zzb("select count(1) from audience_filter_values where app_id=?", new String[]{str}) <= ((long) Math.max(0, Math.min(2000, zzaun().zzb(str, zzcbm.zzipg))))) {
                return false;
            }
            Iterable arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                Integer num = (Integer) list.get(i);
                if (num == null || !(num instanceof Integer)) {
                    return false;
                }
                arrayList.add(Integer.toString(num.intValue()));
            }
            String join = TextUtils.join(",", arrayList);
            join = new StringBuilder(String.valueOf(join).length() + 2).append("(").append(join).append(")").toString();
            return writableDatabase.delete("audience_filter_values", new StringBuilder(String.valueOf(join).length() + 140).append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ").append(join).append(" order by rowid desc limit -1 offset ?)").toString(), new String[]{str, Integer.toString(r5)}) > 0;
        } catch (SQLiteException e) {
            zzaul().zzayd().zze("Database error querying filters. appId", zzcbw.zzjf(str), e);
            return false;
        }
    }

    public final void beginTransaction() {
        zzwk();
        getWritableDatabase().beginTransaction();
    }

    public final void endTransaction() {
        zzwk();
        getWritableDatabase().endTransaction();
    }

    final SQLiteDatabase getWritableDatabase() {
        zzuj();
        try {
            return this.zzimw.getWritableDatabase();
        } catch (SQLiteException e) {
            zzaul().zzayf().zzj("Error opening database", e);
            throw e;
        }
    }

    public final void setTransactionSuccessful() {
        zzwk();
        getWritableDatabase().setTransactionSuccessful();
    }

    public final long zza(zzcgk com_google_android_gms_internal_zzcgk) throws IOException {
        zzuj();
        zzwk();
        zzbp.zzu(com_google_android_gms_internal_zzcgk);
        zzbp.zzgg(com_google_android_gms_internal_zzcgk.zzci);
        try {
            long j;
            Object obj = new byte[com_google_android_gms_internal_zzcgk.zzhi()];
            zzeyf zzn = zzeyf.zzn(obj, 0, obj.length);
            com_google_android_gms_internal_zzcgk.zza(zzn);
            zzn.zzctn();
            zzcdt zzauh = zzauh();
            zzbp.zzu(obj);
            zzauh.zzuj();
            MessageDigest zzec = zzcfw.zzec("MD5");
            if (zzec == null) {
                zzauh.zzaul().zzayd().log("Failed to get MD5");
                j = 0;
            } else {
                j = zzcfw.zzr(zzec.digest(obj));
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", com_google_android_gms_internal_zzcgk.zzci);
            contentValues.put("metadata_fingerprint", Long.valueOf(j));
            contentValues.put("metadata", obj);
            try {
                getWritableDatabase().insertWithOnConflict("raw_events_metadata", null, contentValues, 4);
                return j;
            } catch (SQLiteException e) {
                zzaul().zzayd().zze("Error storing raw event metadata. appId", zzcbw.zzjf(com_google_android_gms_internal_zzcgk.zzci), e);
                throw e;
            }
        } catch (IOException e2) {
            zzaul().zzayd().zze("Data loss. Failed to serialize event metadata. appId", zzcbw.zzjf(com_google_android_gms_internal_zzcgk.zzci), e2);
            throw e2;
        }
    }

    public final zzcaz zza(long j, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        Cursor query;
        Object e;
        Throwable th;
        zzbp.zzgg(str);
        zzuj();
        zzwk();
        String[] strArr = new String[]{str};
        zzcaz com_google_android_gms_internal_zzcaz = new zzcaz();
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            query = writableDatabase.query("apps", new String[]{"day", "daily_events_count", "daily_public_events_count", "daily_conversions_count", "daily_error_events_count", "daily_realtime_events_count"}, "app_id=?", new String[]{str}, null, null, null);
            try {
                if (query.moveToFirst()) {
                    if (query.getLong(0) == j) {
                        com_google_android_gms_internal_zzcaz.zzimz = query.getLong(1);
                        com_google_android_gms_internal_zzcaz.zzimy = query.getLong(2);
                        com_google_android_gms_internal_zzcaz.zzina = query.getLong(3);
                        com_google_android_gms_internal_zzcaz.zzinb = query.getLong(4);
                        com_google_android_gms_internal_zzcaz.zzinc = query.getLong(5);
                    }
                    if (z) {
                        com_google_android_gms_internal_zzcaz.zzimz++;
                    }
                    if (z2) {
                        com_google_android_gms_internal_zzcaz.zzimy++;
                    }
                    if (z3) {
                        com_google_android_gms_internal_zzcaz.zzina++;
                    }
                    if (z4) {
                        com_google_android_gms_internal_zzcaz.zzinb++;
                    }
                    if (z5) {
                        com_google_android_gms_internal_zzcaz.zzinc++;
                    }
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("day", Long.valueOf(j));
                    contentValues.put("daily_public_events_count", Long.valueOf(com_google_android_gms_internal_zzcaz.zzimy));
                    contentValues.put("daily_events_count", Long.valueOf(com_google_android_gms_internal_zzcaz.zzimz));
                    contentValues.put("daily_conversions_count", Long.valueOf(com_google_android_gms_internal_zzcaz.zzina));
                    contentValues.put("daily_error_events_count", Long.valueOf(com_google_android_gms_internal_zzcaz.zzinb));
                    contentValues.put("daily_realtime_events_count", Long.valueOf(com_google_android_gms_internal_zzcaz.zzinc));
                    writableDatabase.update("apps", contentValues, "app_id=?", strArr);
                    if (query != null) {
                        query.close();
                    }
                    return com_google_android_gms_internal_zzcaz;
                }
                zzaul().zzayf().zzj("Not updating daily counts, app is not known. appId", zzcbw.zzjf(str));
                if (query != null) {
                    query.close();
                }
                return com_google_android_gms_internal_zzcaz;
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    zzaul().zzayd().zze("Error updating daily counts. appId", zzcbw.zzjf(str), e);
                    if (query != null) {
                        query.close();
                    }
                    return com_google_android_gms_internal_zzcaz;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            query = null;
            zzaul().zzayd().zze("Error updating daily counts. appId", zzcbw.zzjf(str), e);
            if (query != null) {
                query.close();
            }
            return com_google_android_gms_internal_zzcaz;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    public final void zza(zzcar com_google_android_gms_internal_zzcar) {
        zzbp.zzu(com_google_android_gms_internal_zzcar);
        zzuj();
        zzwk();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", com_google_android_gms_internal_zzcar.getAppId());
        contentValues.put("app_instance_id", com_google_android_gms_internal_zzcar.getAppInstanceId());
        contentValues.put("gmp_app_id", com_google_android_gms_internal_zzcar.getGmpAppId());
        contentValues.put("resettable_device_id_hash", com_google_android_gms_internal_zzcar.zzaup());
        contentValues.put("last_bundle_index", Long.valueOf(com_google_android_gms_internal_zzcar.zzauy()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(com_google_android_gms_internal_zzcar.zzaur()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(com_google_android_gms_internal_zzcar.zzaus()));
        contentValues.put("app_version", com_google_android_gms_internal_zzcar.zzuo());
        contentValues.put("app_store", com_google_android_gms_internal_zzcar.zzauu());
        contentValues.put("gmp_version", Long.valueOf(com_google_android_gms_internal_zzcar.zzauv()));
        contentValues.put("dev_cert_hash", Long.valueOf(com_google_android_gms_internal_zzcar.zzauw()));
        contentValues.put("measurement_enabled", Boolean.valueOf(com_google_android_gms_internal_zzcar.zzaux()));
        contentValues.put("day", Long.valueOf(com_google_android_gms_internal_zzcar.zzavc()));
        contentValues.put("daily_public_events_count", Long.valueOf(com_google_android_gms_internal_zzcar.zzavd()));
        contentValues.put("daily_events_count", Long.valueOf(com_google_android_gms_internal_zzcar.zzave()));
        contentValues.put("daily_conversions_count", Long.valueOf(com_google_android_gms_internal_zzcar.zzavf()));
        contentValues.put("config_fetched_time", Long.valueOf(com_google_android_gms_internal_zzcar.zzauz()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(com_google_android_gms_internal_zzcar.zzava()));
        contentValues.put("app_version_int", Long.valueOf(com_google_android_gms_internal_zzcar.zzaut()));
        contentValues.put("firebase_instance_id", com_google_android_gms_internal_zzcar.zzauq());
        contentValues.put("daily_error_events_count", Long.valueOf(com_google_android_gms_internal_zzcar.zzavh()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(com_google_android_gms_internal_zzcar.zzavg()));
        contentValues.put("health_monitor_sample", com_google_android_gms_internal_zzcar.zzavi());
        contentValues.put("android_id", Long.valueOf(com_google_android_gms_internal_zzcar.zzavk()));
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (((long) writableDatabase.update("apps", contentValues, "app_id = ?", new String[]{com_google_android_gms_internal_zzcar.getAppId()})) == 0 && writableDatabase.insertWithOnConflict("apps", null, contentValues, 5) == -1) {
                zzaul().zzayd().zzj("Failed to insert/update app (got -1). appId", zzcbw.zzjf(com_google_android_gms_internal_zzcar.getAppId()));
            }
        } catch (SQLiteException e) {
            zzaul().zzayd().zze("Error storing app. appId", zzcbw.zzjf(com_google_android_gms_internal_zzcar.getAppId()), e);
        }
    }

    public final void zza(zzcbg com_google_android_gms_internal_zzcbg) {
        zzbp.zzu(com_google_android_gms_internal_zzcbg);
        zzuj();
        zzwk();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", com_google_android_gms_internal_zzcbg.mAppId);
        contentValues.put("name", com_google_android_gms_internal_zzcbg.mName);
        contentValues.put("lifetime_count", Long.valueOf(com_google_android_gms_internal_zzcbg.zzinl));
        contentValues.put("current_bundle_count", Long.valueOf(com_google_android_gms_internal_zzcbg.zzinm));
        contentValues.put("last_fire_timestamp", Long.valueOf(com_google_android_gms_internal_zzcbg.zzinn));
        try {
            if (getWritableDatabase().insertWithOnConflict("events", null, contentValues, 5) == -1) {
                zzaul().zzayd().zzj("Failed to insert/update event aggregates (got -1). appId", zzcbw.zzjf(com_google_android_gms_internal_zzcbg.mAppId));
            }
        } catch (SQLiteException e) {
            zzaul().zzayd().zze("Error storing event aggregates. appId", zzcbw.zzjf(com_google_android_gms_internal_zzcbg.mAppId), e);
        }
    }

    final void zza(String str, zzcfx[] com_google_android_gms_internal_zzcfxArr) {
        int i = 0;
        zzwk();
        zzuj();
        zzbp.zzgg(str);
        zzbp.zzu(com_google_android_gms_internal_zzcfxArr);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            int i2;
            zzwk();
            zzuj();
            zzbp.zzgg(str);
            SQLiteDatabase writableDatabase2 = getWritableDatabase();
            writableDatabase2.delete("property_filters", "app_id=?", new String[]{str});
            writableDatabase2.delete("event_filters", "app_id=?", new String[]{str});
            for (zzcfx com_google_android_gms_internal_zzcfx : com_google_android_gms_internal_zzcfxArr) {
                zzwk();
                zzuj();
                zzbp.zzgg(str);
                zzbp.zzu(com_google_android_gms_internal_zzcfx);
                zzbp.zzu(com_google_android_gms_internal_zzcfx.zzixl);
                zzbp.zzu(com_google_android_gms_internal_zzcfx.zzixk);
                if (com_google_android_gms_internal_zzcfx.zzixj == null) {
                    zzaul().zzayf().zzj("Audience with no ID. appId", zzcbw.zzjf(str));
                } else {
                    int intValue = com_google_android_gms_internal_zzcfx.zzixj.intValue();
                    for (zzcfy com_google_android_gms_internal_zzcfy : com_google_android_gms_internal_zzcfx.zzixl) {
                        if (com_google_android_gms_internal_zzcfy.zzixn == null) {
                            zzaul().zzayf().zze("Event filter with no ID. Audience definition ignored. appId, audienceId", zzcbw.zzjf(str), com_google_android_gms_internal_zzcfx.zzixj);
                            break;
                        }
                    }
                    for (zzcgb com_google_android_gms_internal_zzcgb : com_google_android_gms_internal_zzcfx.zzixk) {
                        if (com_google_android_gms_internal_zzcgb.zzixn == null) {
                            zzaul().zzayf().zze("Property filter with no ID. Audience definition ignored. appId, audienceId", zzcbw.zzjf(str), com_google_android_gms_internal_zzcfx.zzixj);
                            break;
                        }
                    }
                    for (zzcfy com_google_android_gms_internal_zzcfy2 : com_google_android_gms_internal_zzcfx.zzixl) {
                        if (!zza(str, intValue, com_google_android_gms_internal_zzcfy2)) {
                            i2 = 0;
                            break;
                        }
                    }
                    i2 = 1;
                    if (i2 != 0) {
                        for (zzcgb com_google_android_gms_internal_zzcgb2 : com_google_android_gms_internal_zzcfx.zzixk) {
                            if (!zza(str, intValue, com_google_android_gms_internal_zzcgb2)) {
                                i2 = 0;
                                break;
                            }
                        }
                    }
                    if (i2 == 0) {
                        zzwk();
                        zzuj();
                        zzbp.zzgg(str);
                        SQLiteDatabase writableDatabase3 = getWritableDatabase();
                        writableDatabase3.delete("property_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(intValue)});
                        writableDatabase3.delete("event_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(intValue)});
                    }
                }
            }
            List arrayList = new ArrayList();
            i2 = com_google_android_gms_internal_zzcfxArr.length;
            while (i < i2) {
                arrayList.add(com_google_android_gms_internal_zzcfxArr[i].zzixj);
                i++;
            }
            zzc(str, arrayList);
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    public final boolean zza(zzcav com_google_android_gms_internal_zzcav) {
        zzbp.zzu(com_google_android_gms_internal_zzcav);
        zzuj();
        zzwk();
        if (zzah(com_google_android_gms_internal_zzcav.packageName, com_google_android_gms_internal_zzcav.zzimh.name) == null) {
            long zzb = zzb("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{com_google_android_gms_internal_zzcav.packageName});
            zzcax.zzawb();
            if (zzb >= 1000) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", com_google_android_gms_internal_zzcav.packageName);
        contentValues.put("origin", com_google_android_gms_internal_zzcav.zzimg);
        contentValues.put("name", com_google_android_gms_internal_zzcav.zzimh.name);
        zza(contentValues, CryptoUri.VALUE, com_google_android_gms_internal_zzcav.zzimh.getValue());
        contentValues.put("active", Boolean.valueOf(com_google_android_gms_internal_zzcav.zzimj));
        contentValues.put("trigger_event_name", com_google_android_gms_internal_zzcav.zzimk);
        contentValues.put("trigger_timeout", Long.valueOf(com_google_android_gms_internal_zzcav.zzimm));
        zzauh();
        contentValues.put("timed_out_event", zzcfw.zza(com_google_android_gms_internal_zzcav.zziml));
        contentValues.put("creation_timestamp", Long.valueOf(com_google_android_gms_internal_zzcav.zzimi));
        zzauh();
        contentValues.put("triggered_event", zzcfw.zza(com_google_android_gms_internal_zzcav.zzimn));
        contentValues.put("triggered_timestamp", Long.valueOf(com_google_android_gms_internal_zzcav.zzimh.zziwz));
        contentValues.put("time_to_live", Long.valueOf(com_google_android_gms_internal_zzcav.zzimo));
        zzauh();
        contentValues.put("expired_event", zzcfw.zza(com_google_android_gms_internal_zzcav.zzimp));
        try {
            if (getWritableDatabase().insertWithOnConflict("conditional_properties", null, contentValues, 5) == -1) {
                zzaul().zzayd().zzj("Failed to insert/update conditional user property (got -1)", zzcbw.zzjf(com_google_android_gms_internal_zzcav.packageName));
            }
        } catch (SQLiteException e) {
            zzaul().zzayd().zze("Error storing conditional user property", zzcbw.zzjf(com_google_android_gms_internal_zzcav.packageName), e);
        }
        return true;
    }

    public final boolean zza(zzcbf com_google_android_gms_internal_zzcbf, long j, boolean z) {
        zzuj();
        zzwk();
        zzbp.zzu(com_google_android_gms_internal_zzcbf);
        zzbp.zzgg(com_google_android_gms_internal_zzcbf.mAppId);
        zzeyn com_google_android_gms_internal_zzcgh = new zzcgh();
        com_google_android_gms_internal_zzcgh.zziyz = Long.valueOf(com_google_android_gms_internal_zzcbf.zzinj);
        com_google_android_gms_internal_zzcgh.zziyx = new zzcgi[com_google_android_gms_internal_zzcbf.zzink.size()];
        Iterator it = com_google_android_gms_internal_zzcbf.zzink.iterator();
        int i = 0;
        while (it.hasNext()) {
            String str = (String) it.next();
            zzcgi com_google_android_gms_internal_zzcgi = new zzcgi();
            int i2 = i + 1;
            com_google_android_gms_internal_zzcgh.zziyx[i] = com_google_android_gms_internal_zzcgi;
            com_google_android_gms_internal_zzcgi.name = str;
            zzauh().zza(com_google_android_gms_internal_zzcgi, com_google_android_gms_internal_zzcbf.zzink.get(str));
            i = i2;
        }
        try {
            byte[] bArr = new byte[com_google_android_gms_internal_zzcgh.zzhi()];
            zzeyf zzn = zzeyf.zzn(bArr, 0, bArr.length);
            com_google_android_gms_internal_zzcgh.zza(zzn);
            zzn.zzctn();
            zzaul().zzayj().zze("Saving event, name, data size", zzaug().zzjc(com_google_android_gms_internal_zzcbf.mName), Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", com_google_android_gms_internal_zzcbf.mAppId);
            contentValues.put("name", com_google_android_gms_internal_zzcbf.mName);
            contentValues.put("timestamp", Long.valueOf(com_google_android_gms_internal_zzcbf.zzfdc));
            contentValues.put("metadata_fingerprint", Long.valueOf(j));
            contentValues.put("data", bArr);
            contentValues.put("realtime", Integer.valueOf(z ? 1 : 0));
            try {
                if (getWritableDatabase().insert("raw_events", null, contentValues) != -1) {
                    return true;
                }
                zzaul().zzayd().zzj("Failed to insert raw event (got -1). appId", zzcbw.zzjf(com_google_android_gms_internal_zzcbf.mAppId));
                return false;
            } catch (SQLiteException e) {
                zzaul().zzayd().zze("Error storing raw event. appId", zzcbw.zzjf(com_google_android_gms_internal_zzcbf.mAppId), e);
                return false;
            }
        } catch (IOException e2) {
            zzaul().zzayd().zze("Data loss. Failed to serialize event params/data. appId", zzcbw.zzjf(com_google_android_gms_internal_zzcbf.mAppId), e2);
            return false;
        }
    }

    public final boolean zza(zzcfv com_google_android_gms_internal_zzcfv) {
        zzbp.zzu(com_google_android_gms_internal_zzcfv);
        zzuj();
        zzwk();
        if (zzah(com_google_android_gms_internal_zzcfv.mAppId, com_google_android_gms_internal_zzcfv.mName) == null) {
            long zzb;
            if (zzcfw.zzju(com_google_android_gms_internal_zzcfv.mName)) {
                zzb = zzb("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{com_google_android_gms_internal_zzcfv.mAppId});
                zzcax.zzavy();
                if (zzb >= 25) {
                    return false;
                }
            }
            zzb = zzb("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{com_google_android_gms_internal_zzcfv.mAppId, com_google_android_gms_internal_zzcfv.mOrigin});
            zzcax.zzawa();
            if (zzb >= 25) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", com_google_android_gms_internal_zzcfv.mAppId);
        contentValues.put("origin", com_google_android_gms_internal_zzcfv.mOrigin);
        contentValues.put("name", com_google_android_gms_internal_zzcfv.mName);
        contentValues.put("set_timestamp", Long.valueOf(com_google_android_gms_internal_zzcfv.zzixd));
        zza(contentValues, CryptoUri.VALUE, com_google_android_gms_internal_zzcfv.mValue);
        try {
            if (getWritableDatabase().insertWithOnConflict("user_attributes", null, contentValues, 5) == -1) {
                zzaul().zzayd().zzj("Failed to insert/update user property (got -1). appId", zzcbw.zzjf(com_google_android_gms_internal_zzcfv.mAppId));
            }
        } catch (SQLiteException e) {
            zzaul().zzayd().zze("Error storing user property. appId", zzcbw.zzjf(com_google_android_gms_internal_zzcfv.mAppId), e);
        }
        return true;
    }

    public final boolean zza(zzcgk com_google_android_gms_internal_zzcgk, boolean z) {
        zzuj();
        zzwk();
        zzbp.zzu(com_google_android_gms_internal_zzcgk);
        zzbp.zzgg(com_google_android_gms_internal_zzcgk.zzci);
        zzbp.zzu(com_google_android_gms_internal_zzcgk.zzizj);
        zzaxk();
        long currentTimeMillis = zzvx().currentTimeMillis();
        if (com_google_android_gms_internal_zzcgk.zzizj.longValue() < currentTimeMillis - zzcax.zzawm() || com_google_android_gms_internal_zzcgk.zzizj.longValue() > zzcax.zzawm() + currentTimeMillis) {
            zzaul().zzayf().zzd("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzcbw.zzjf(com_google_android_gms_internal_zzcgk.zzci), Long.valueOf(currentTimeMillis), com_google_android_gms_internal_zzcgk.zzizj);
        }
        try {
            byte[] bArr = new byte[com_google_android_gms_internal_zzcgk.zzhi()];
            zzeyf zzn = zzeyf.zzn(bArr, 0, bArr.length);
            com_google_android_gms_internal_zzcgk.zza(zzn);
            zzn.zzctn();
            bArr = zzauh().zzp(bArr);
            zzaul().zzayj().zzj("Saving bundle, size", Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", com_google_android_gms_internal_zzcgk.zzci);
            contentValues.put("bundle_end_timestamp", com_google_android_gms_internal_zzcgk.zzizj);
            contentValues.put("data", bArr);
            contentValues.put("has_realtime", Integer.valueOf(z ? 1 : 0));
            try {
                if (getWritableDatabase().insert("queue", null, contentValues) != -1) {
                    return true;
                }
                zzaul().zzayd().zzj("Failed to insert bundle (got -1). appId", zzcbw.zzjf(com_google_android_gms_internal_zzcgk.zzci));
                return false;
            } catch (SQLiteException e) {
                zzaul().zzayd().zze("Error storing bundle. appId", zzcbw.zzjf(com_google_android_gms_internal_zzcgk.zzci), e);
                return false;
            }
        } catch (IOException e2) {
            zzaul().zzayd().zze("Data loss. Failed to serialize bundle. appId", zzcbw.zzjf(com_google_android_gms_internal_zzcgk.zzci), e2);
            return false;
        }
    }

    public final void zzae(List<Long> list) {
        zzbp.zzu(list);
        zzuj();
        zzwk();
        StringBuilder stringBuilder = new StringBuilder("rowid in (");
        for (int i = 0; i < list.size(); i++) {
            if (i != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(((Long) list.get(i)).longValue());
        }
        stringBuilder.append(")");
        int delete = getWritableDatabase().delete("raw_events", stringBuilder.toString(), null);
        if (delete != list.size()) {
            zzaul().zzayd().zze("Deleted fewer rows from raw events table than expected", Integer.valueOf(delete), Integer.valueOf(list.size()));
        }
    }

    public final zzcbg zzaf(String str, String str2) {
        Object e;
        Cursor cursor;
        Throwable th;
        Cursor cursor2 = null;
        zzbp.zzgg(str);
        zzbp.zzgg(str2);
        zzuj();
        zzwk();
        try {
            Cursor query = getWritableDatabase().query("events", new String[]{"lifetime_count", "current_bundle_count", "last_fire_timestamp"}, "app_id=? and name=?", new String[]{str, str2}, null, null, null);
            try {
                if (query.moveToFirst()) {
                    zzcbg com_google_android_gms_internal_zzcbg = new zzcbg(str, str2, query.getLong(0), query.getLong(1), query.getLong(2));
                    if (query.moveToNext()) {
                        zzaul().zzayd().zzj("Got multiple records for event aggregates, expected one. appId", zzcbw.zzjf(str));
                    }
                    if (query == null) {
                        return com_google_android_gms_internal_zzcbg;
                    }
                    query.close();
                    return com_google_android_gms_internal_zzcbg;
                }
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (SQLiteException e2) {
                e = e2;
                cursor = query;
                try {
                    zzaul().zzayd().zzd("Error querying events. appId", zzcbw.zzjf(str), zzaug().zzjc(str2), e);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    cursor2 = cursor;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                cursor2 = query;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
            zzaul().zzayd().zzd("Error querying events. appId", zzcbw.zzjf(str), zzaug().zzjc(str2), e);
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }

    public final void zzag(String str, String str2) {
        zzbp.zzgg(str);
        zzbp.zzgg(str2);
        zzuj();
        zzwk();
        try {
            zzaul().zzayj().zzj("Deleted user attribute rows", Integer.valueOf(getWritableDatabase().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2})));
        } catch (SQLiteException e) {
            zzaul().zzayd().zzd("Error deleting user attribute. appId", zzcbw.zzjf(str), zzaug().zzje(str2), e);
        }
    }

    public final zzcfv zzah(String str, String str2) {
        Object e;
        Cursor cursor;
        Throwable th;
        Cursor cursor2 = null;
        zzbp.zzgg(str);
        zzbp.zzgg(str2);
        zzuj();
        zzwk();
        try {
            Cursor query = getWritableDatabase().query("user_attributes", new String[]{"set_timestamp", CryptoUri.VALUE, "origin"}, "app_id=? and name=?", new String[]{str, str2}, null, null, null);
            try {
                if (query.moveToFirst()) {
                    String str3 = str;
                    zzcfv com_google_android_gms_internal_zzcfv = new zzcfv(str3, query.getString(2), str2, query.getLong(0), zza(query, 1));
                    if (query.moveToNext()) {
                        zzaul().zzayd().zzj("Got multiple records for user property, expected one. appId", zzcbw.zzjf(str));
                    }
                    if (query == null) {
                        return com_google_android_gms_internal_zzcfv;
                    }
                    query.close();
                    return com_google_android_gms_internal_zzcfv;
                }
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (SQLiteException e2) {
                e = e2;
                cursor = query;
                try {
                    zzaul().zzayd().zzd("Error querying user property. appId", zzcbw.zzjf(str), zzaug().zzje(str2), e);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    cursor2 = cursor;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                cursor2 = query;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
            zzaul().zzayd().zzd("Error querying user property. appId", zzcbw.zzjf(str), zzaug().zzje(str2), e);
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }

    public final zzcav zzai(String str, String str2) {
        Cursor query;
        Object e;
        Cursor cursor;
        Throwable th;
        zzbp.zzgg(str);
        zzbp.zzgg(str2);
        zzuj();
        zzwk();
        try {
            query = getWritableDatabase().query("conditional_properties", new String[]{"origin", CryptoUri.VALUE, "active", "trigger_event_name", "trigger_timeout", "timed_out_event", "creation_timestamp", "triggered_event", "triggered_timestamp", "time_to_live", "expired_event"}, "app_id=? and name=?", new String[]{str, str2}, null, null, null);
            try {
                if (query.moveToFirst()) {
                    String string = query.getString(0);
                    Object zza = zza(query, 1);
                    boolean z = query.getInt(2) != 0;
                    String string2 = query.getString(3);
                    long j = query.getLong(4);
                    zzcbk com_google_android_gms_internal_zzcbk = (zzcbk) zzauh().zzb(query.getBlob(5), zzcbk.CREATOR);
                    long j2 = query.getLong(6);
                    zzcbk com_google_android_gms_internal_zzcbk2 = (zzcbk) zzauh().zzb(query.getBlob(7), zzcbk.CREATOR);
                    long j3 = query.getLong(8);
                    zzcav com_google_android_gms_internal_zzcav = new zzcav(str, string, new zzcft(str2, j3, zza, string), j2, z, string2, com_google_android_gms_internal_zzcbk, j, com_google_android_gms_internal_zzcbk2, query.getLong(9), (zzcbk) zzauh().zzb(query.getBlob(10), zzcbk.CREATOR));
                    if (query.moveToNext()) {
                        zzaul().zzayd().zze("Got multiple records for conditional property, expected one", zzcbw.zzjf(str), zzaug().zzje(str2));
                    }
                    if (query == null) {
                        return com_google_android_gms_internal_zzcav;
                    }
                    query.close();
                    return com_google_android_gms_internal_zzcav;
                }
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (SQLiteException e2) {
                e = e2;
                cursor = query;
                try {
                    zzaul().zzayd().zzd("Error querying conditional property", zzcbw.zzjf(str), zzaug().zzje(str2), e);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    query = cursor;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
            zzaul().zzayd().zzd("Error querying conditional property", zzcbw.zzjf(str), zzaug().zzje(str2), e);
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    public final int zzaj(String str, String str2) {
        int i = 0;
        zzbp.zzgg(str);
        zzbp.zzgg(str2);
        zzuj();
        zzwk();
        try {
            i = getWritableDatabase().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzaul().zzayd().zzd("Error deleting conditional property", zzcbw.zzjf(str), zzaug().zzje(str2), e);
        }
        return i;
    }

    final Map<Integer, List<zzcfy>> zzak(String str, String str2) {
        Object e;
        Throwable th;
        zzwk();
        zzuj();
        zzbp.zzgg(str);
        zzbp.zzgg(str2);
        Map<Integer, List<zzcfy>> arrayMap = new ArrayMap();
        Cursor query;
        try {
            query = getWritableDatabase().query("event_filters", new String[]{"audience_id", "data"}, "app_id=? AND event_name=?", new String[]{str, str2}, null, null, null);
            if (query.moveToFirst()) {
                do {
                    try {
                        byte[] blob = query.getBlob(1);
                        zzeye zzm = zzeye.zzm(blob, 0, blob.length);
                        zzeyn com_google_android_gms_internal_zzcfy = new zzcfy();
                        try {
                            com_google_android_gms_internal_zzcfy.zza(zzm);
                            int i = query.getInt(0);
                            List list = (List) arrayMap.get(Integer.valueOf(i));
                            if (list == null) {
                                list = new ArrayList();
                                arrayMap.put(Integer.valueOf(i), list);
                            }
                            list.add(com_google_android_gms_internal_zzcfy);
                        } catch (IOException e2) {
                            zzaul().zzayd().zze("Failed to merge filter. appId", zzcbw.zzjf(str), e2);
                        }
                    } catch (SQLiteException e3) {
                        e = e3;
                    }
                } while (query.moveToNext());
                if (query != null) {
                    query.close();
                }
                return arrayMap;
            }
            Map<Integer, List<zzcfy>> emptyMap = Collections.emptyMap();
            if (query == null) {
                return emptyMap;
            }
            query.close();
            return emptyMap;
        } catch (SQLiteException e4) {
            e = e4;
            query = null;
            try {
                zzaul().zzayd().zze("Database error querying filters. appId", zzcbw.zzjf(str), e);
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    final Map<Integer, List<zzcgb>> zzal(String str, String str2) {
        Object e;
        Throwable th;
        zzwk();
        zzuj();
        zzbp.zzgg(str);
        zzbp.zzgg(str2);
        Map<Integer, List<zzcgb>> arrayMap = new ArrayMap();
        Cursor query;
        try {
            query = getWritableDatabase().query("property_filters", new String[]{"audience_id", "data"}, "app_id=? AND property_name=?", new String[]{str, str2}, null, null, null);
            if (query.moveToFirst()) {
                do {
                    try {
                        byte[] blob = query.getBlob(1);
                        zzeye zzm = zzeye.zzm(blob, 0, blob.length);
                        zzeyn com_google_android_gms_internal_zzcgb = new zzcgb();
                        try {
                            com_google_android_gms_internal_zzcgb.zza(zzm);
                            int i = query.getInt(0);
                            List list = (List) arrayMap.get(Integer.valueOf(i));
                            if (list == null) {
                                list = new ArrayList();
                                arrayMap.put(Integer.valueOf(i), list);
                            }
                            list.add(com_google_android_gms_internal_zzcgb);
                        } catch (IOException e2) {
                            zzaul().zzayd().zze("Failed to merge filter", zzcbw.zzjf(str), e2);
                        }
                    } catch (SQLiteException e3) {
                        e = e3;
                    }
                } while (query.moveToNext());
                if (query != null) {
                    query.close();
                }
                return arrayMap;
            }
            Map<Integer, List<zzcgb>> emptyMap = Collections.emptyMap();
            if (query == null) {
                return emptyMap;
            }
            query.close();
            return emptyMap;
        } catch (SQLiteException e4) {
            e = e4;
            query = null;
            try {
                zzaul().zzayd().zze("Database error querying filters. appId", zzcbw.zzjf(str), e);
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    protected final long zzam(String str, String str2) {
        Object e;
        zzbp.zzgg(str);
        zzbp.zzgg(str2);
        zzuj();
        zzwk();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        long zza;
        try {
            zza = zza(new StringBuilder(String.valueOf(str2).length() + 32).append("select ").append(str2).append(" from app2 where app_id=?").toString(), new String[]{str}, -1);
            if (zza == -1) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("app_id", str);
                contentValues.put("first_open_count", Integer.valueOf(0));
                contentValues.put("previous_install_count", Integer.valueOf(0));
                if (writableDatabase.insertWithOnConflict("app2", null, contentValues, 5) == -1) {
                    zzaul().zzayd().zze("Failed to insert column (got -1). appId", zzcbw.zzjf(str), str2);
                    writableDatabase.endTransaction();
                    return -1;
                }
                zza = 0;
            }
            try {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("app_id", str);
                contentValues2.put(str2, Long.valueOf(1 + zza));
                if (((long) writableDatabase.update("app2", contentValues2, "app_id = ?", new String[]{str})) == 0) {
                    zzaul().zzayd().zze("Failed to update column (got 0). appId", zzcbw.zzjf(str), str2);
                    writableDatabase.endTransaction();
                    return -1;
                }
                writableDatabase.setTransactionSuccessful();
                writableDatabase.endTransaction();
                return zza;
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    zzaul().zzayd().zzd("Error inserting column. appId", zzcbw.zzjf(str), str2, e);
                    return zza;
                } finally {
                    writableDatabase.endTransaction();
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            zza = 0;
            zzaul().zzayd().zzd("Error inserting column. appId", zzcbw.zzjf(str), str2, e);
            return zza;
        }
    }

    public final String zzaxi() {
        Object e;
        Throwable th;
        String str = null;
        Cursor rawQuery;
        try {
            rawQuery = getWritableDatabase().rawQuery("select app_id from queue order by has_realtime desc, rowid asc limit 1;", null);
            try {
                if (rawQuery.moveToFirst()) {
                    str = rawQuery.getString(0);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                } else if (rawQuery != null) {
                    rawQuery.close();
                }
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    zzaul().zzayd().zzj("Database error getting next bundle app id", e);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            rawQuery = null;
            zzaul().zzayd().zzj("Database error getting next bundle app id", e);
            if (rawQuery != null) {
                rawQuery.close();
            }
            return str;
        } catch (Throwable th3) {
            rawQuery = null;
            th = th3;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        return str;
    }

    public final boolean zzaxj() {
        return zzb("select count(1) > 0 from queue where has_realtime = 1", null) != 0;
    }

    final void zzaxk() {
        zzuj();
        zzwk();
        if (zzaxq()) {
            long j = zzaum().zziqs.get();
            long elapsedRealtime = zzvx().elapsedRealtime();
            if (Math.abs(elapsedRealtime - j) > zzcax.zzawn()) {
                zzaum().zziqs.set(elapsedRealtime);
                zzuj();
                zzwk();
                if (zzaxq()) {
                    int delete = getWritableDatabase().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(zzvx().currentTimeMillis()), String.valueOf(zzcax.zzawm())});
                    if (delete > 0) {
                        zzaul().zzayj().zzj("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
                    }
                }
            }
        }
    }

    public final long zzaxl() {
        return zza("select max(bundle_end_timestamp) from queue", null, 0);
    }

    public final long zzaxm() {
        return zza("select max(timestamp) from raw_events", null, 0);
    }

    public final boolean zzaxn() {
        return zzb("select count(1) > 0 from raw_events", null) != 0;
    }

    public final boolean zzaxo() {
        return zzb("select count(1) > 0 from raw_events where realtime = 1", null) != 0;
    }

    public final long zzaxp() {
        long j = -1;
        Cursor cursor = null;
        try {
            cursor = getWritableDatabase().rawQuery("select rowid from raw_events order by rowid desc limit 1;", null);
            if (cursor.moveToFirst()) {
                j = cursor.getLong(0);
                if (cursor != null) {
                    cursor.close();
                }
            } else if (cursor != null) {
                cursor.close();
            }
        } catch (SQLiteException e) {
            zzaul().zzayd().zzj("Error querying raw events", e);
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
        return j;
    }

    public final String zzba(long j) {
        Object e;
        Throwable th;
        String str = null;
        zzuj();
        zzwk();
        Cursor rawQuery;
        try {
            rawQuery = getWritableDatabase().rawQuery("select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;", new String[]{String.valueOf(j)});
            try {
                if (rawQuery.moveToFirst()) {
                    str = rawQuery.getString(0);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                } else {
                    zzaul().zzayj().log("No expired configs for apps with pending events");
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                }
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    zzaul().zzayd().zzj("Error selecting expired configs", e);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            rawQuery = str;
            zzaul().zzayd().zzj("Error selecting expired configs", e);
            if (rawQuery != null) {
                rawQuery.close();
            }
            return str;
        } catch (Throwable th3) {
            rawQuery = str;
            th = th3;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        return str;
    }

    public final List<zzcav> zzc(String str, String[] strArr) {
        Object e;
        Cursor cursor;
        Throwable th;
        zzuj();
        zzwk();
        List<zzcav> arrayList = new ArrayList();
        Cursor query;
        try {
            String[] strArr2 = new String[]{"app_id", "origin", "name", CryptoUri.VALUE, "active", "trigger_event_name", "trigger_timeout", "timed_out_event", "creation_timestamp", "triggered_event", "triggered_timestamp", "time_to_live", "expired_event"};
            zzcax.zzawb();
            query = getWritableDatabase().query("conditional_properties", strArr2, str, strArr, null, null, "rowid", "1001");
            try {
                if (query.moveToFirst()) {
                    do {
                        if (arrayList.size() >= zzcax.zzawb()) {
                            zzaul().zzayd().zzj("Read more than the max allowed conditional properties, ignoring extra", Integer.valueOf(zzcax.zzawb()));
                            break;
                        }
                        String string = query.getString(0);
                        String string2 = query.getString(1);
                        String string3 = query.getString(2);
                        Object zza = zza(query, 3);
                        boolean z = query.getInt(4) != 0;
                        String string4 = query.getString(5);
                        long j = query.getLong(6);
                        zzcbk com_google_android_gms_internal_zzcbk = (zzcbk) zzauh().zzb(query.getBlob(7), zzcbk.CREATOR);
                        long j2 = query.getLong(8);
                        zzcbk com_google_android_gms_internal_zzcbk2 = (zzcbk) zzauh().zzb(query.getBlob(9), zzcbk.CREATOR);
                        long j3 = query.getLong(10);
                        List<zzcav> list = arrayList;
                        list.add(new zzcav(string, string2, new zzcft(string3, j3, zza, string2), j2, z, string4, com_google_android_gms_internal_zzcbk, j, com_google_android_gms_internal_zzcbk2, query.getLong(11), (zzcbk) zzauh().zzb(query.getBlob(12), zzcbk.CREATOR)));
                    } while (query.moveToNext());
                    if (query != null) {
                        query.close();
                    }
                    return arrayList;
                }
                if (query != null) {
                    query.close();
                }
                return arrayList;
            } catch (SQLiteException e2) {
                e = e2;
                cursor = query;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
            try {
                zzaul().zzayd().zzj("Error querying conditional user property value", e);
                List<zzcav> emptyList = Collections.emptyList();
                if (cursor == null) {
                    return emptyList;
                }
                cursor.close();
                return emptyList;
            } catch (Throwable th3) {
                th = th3;
                query = cursor;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    public final List<zzcfv> zzg(String str, String str2, String str3) {
        Object obj;
        Object e;
        Cursor cursor;
        Throwable th;
        Cursor cursor2 = null;
        zzbp.zzgg(str);
        zzuj();
        zzwk();
        List<zzcfv> arrayList = new ArrayList();
        try {
            List arrayList2 = new ArrayList(3);
            arrayList2.add(str);
            StringBuilder stringBuilder = new StringBuilder("app_id=?");
            if (!TextUtils.isEmpty(str2)) {
                arrayList2.add(str2);
                stringBuilder.append(" and origin=?");
            }
            if (!TextUtils.isEmpty(str3)) {
                arrayList2.add(String.valueOf(str3).concat("*"));
                stringBuilder.append(" and name glob ?");
            }
            String[] strArr = (String[]) arrayList2.toArray(new String[arrayList2.size()]);
            String[] strArr2 = new String[]{"name", "set_timestamp", CryptoUri.VALUE, "origin"};
            zzcax.zzavz();
            Cursor query = getWritableDatabase().query("user_attributes", strArr2, stringBuilder.toString(), strArr, null, null, "rowid", "1001");
            try {
                if (query.moveToFirst()) {
                    while (arrayList.size() < zzcax.zzavz()) {
                        String string;
                        try {
                            String string2 = query.getString(0);
                            long j = query.getLong(1);
                            Object zza = zza(query, 2);
                            string = query.getString(3);
                            if (zza == null) {
                                zzaul().zzayd().zzd("(2)Read invalid user property value, ignoring it", zzcbw.zzjf(str), string, str3);
                            } else {
                                arrayList.add(new zzcfv(str, string, string2, j, zza));
                            }
                            if (!query.moveToNext()) {
                                break;
                            }
                            obj = string;
                        } catch (SQLiteException e2) {
                            e = e2;
                            cursor = query;
                            obj = string;
                        } catch (Throwable th2) {
                            th = th2;
                            cursor2 = query;
                        }
                    }
                    zzaul().zzayd().zzj("Read more than the max allowed user properties, ignoring excess", Integer.valueOf(zzcax.zzavz()));
                    if (query != null) {
                        query.close();
                    }
                    return arrayList;
                }
                if (query != null) {
                    query.close();
                }
                return arrayList;
            } catch (SQLiteException e3) {
                e = e3;
                cursor = query;
            } catch (Throwable th22) {
                th = th22;
                cursor2 = query;
            }
        } catch (SQLiteException e4) {
            e = e4;
            cursor = null;
            try {
                zzaul().zzayd().zzd("(2)Error querying user properties", zzcbw.zzjf(str), obj, e);
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                cursor2 = cursor;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }

    public final List<zzcav> zzh(String str, String str2, String str3) {
        zzbp.zzgg(str);
        zzuj();
        zzwk();
        List arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder stringBuilder = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            stringBuilder.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(String.valueOf(str3).concat("*"));
            stringBuilder.append(" and name glob ?");
        }
        return zzc(stringBuilder.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    public final List<zzcfv> zziv(String str) {
        Object e;
        Cursor cursor;
        Throwable th;
        Cursor cursor2 = null;
        zzbp.zzgg(str);
        zzuj();
        zzwk();
        List<zzcfv> arrayList = new ArrayList();
        try {
            Cursor query = getWritableDatabase().query("user_attributes", new String[]{"name", "origin", "set_timestamp", CryptoUri.VALUE}, "app_id=?", new String[]{str}, null, null, "rowid", String.valueOf(zzcax.zzavz()));
            try {
                if (query.moveToFirst()) {
                    do {
                        String string = query.getString(0);
                        String string2 = query.getString(1);
                        if (string2 == null) {
                            string2 = "";
                        }
                        long j = query.getLong(2);
                        Object zza = zza(query, 3);
                        if (zza == null) {
                            zzaul().zzayd().zzj("Read invalid user property value, ignoring it. appId", zzcbw.zzjf(str));
                        } else {
                            arrayList.add(new zzcfv(str, string2, string, j, zza));
                        }
                    } while (query.moveToNext());
                    if (query != null) {
                        query.close();
                    }
                    return arrayList;
                }
                if (query != null) {
                    query.close();
                }
                return arrayList;
            } catch (SQLiteException e2) {
                e = e2;
                cursor = query;
            } catch (Throwable th2) {
                th = th2;
                cursor2 = query;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
            try {
                zzaul().zzayd().zze("Error querying user properties. appId", zzcbw.zzjf(str), e);
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                cursor2 = cursor;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }

    public final zzcar zziw(String str) {
        Object e;
        Throwable th;
        zzbp.zzgg(str);
        zzuj();
        zzwk();
        Cursor query;
        try {
            query = getWritableDatabase().query("apps", new String[]{"app_instance_id", "gmp_app_id", "resettable_device_id_hash", "last_bundle_index", "last_bundle_start_timestamp", "last_bundle_end_timestamp", "app_version", "app_store", "gmp_version", "dev_cert_hash", "measurement_enabled", "day", "daily_public_events_count", "daily_events_count", "daily_conversions_count", "config_fetched_time", "failed_config_fetch_time", "app_version_int", "firebase_instance_id", "daily_error_events_count", "daily_realtime_events_count", "health_monitor_sample", "android_id"}, "app_id=?", new String[]{str}, null, null, null);
            try {
                if (query.moveToFirst()) {
                    zzcar com_google_android_gms_internal_zzcar = new zzcar(this.zziki, str);
                    com_google_android_gms_internal_zzcar.zzim(query.getString(0));
                    com_google_android_gms_internal_zzcar.zzin(query.getString(1));
                    com_google_android_gms_internal_zzcar.zzio(query.getString(2));
                    com_google_android_gms_internal_zzcar.zzaq(query.getLong(3));
                    com_google_android_gms_internal_zzcar.zzal(query.getLong(4));
                    com_google_android_gms_internal_zzcar.zzam(query.getLong(5));
                    com_google_android_gms_internal_zzcar.setAppVersion(query.getString(6));
                    com_google_android_gms_internal_zzcar.zziq(query.getString(7));
                    com_google_android_gms_internal_zzcar.zzao(query.getLong(8));
                    com_google_android_gms_internal_zzcar.zzap(query.getLong(9));
                    com_google_android_gms_internal_zzcar.setMeasurementEnabled((query.isNull(10) ? 1 : query.getInt(10)) != 0);
                    com_google_android_gms_internal_zzcar.zzat(query.getLong(11));
                    com_google_android_gms_internal_zzcar.zzau(query.getLong(12));
                    com_google_android_gms_internal_zzcar.zzav(query.getLong(13));
                    com_google_android_gms_internal_zzcar.zzaw(query.getLong(14));
                    com_google_android_gms_internal_zzcar.zzar(query.getLong(15));
                    com_google_android_gms_internal_zzcar.zzas(query.getLong(16));
                    com_google_android_gms_internal_zzcar.zzan(query.isNull(17) ? -2147483648L : (long) query.getInt(17));
                    com_google_android_gms_internal_zzcar.zzip(query.getString(18));
                    com_google_android_gms_internal_zzcar.zzay(query.getLong(19));
                    com_google_android_gms_internal_zzcar.zzax(query.getLong(20));
                    com_google_android_gms_internal_zzcar.zzir(query.getString(21));
                    com_google_android_gms_internal_zzcar.zzaz(query.isNull(22) ? 0 : query.getLong(22));
                    com_google_android_gms_internal_zzcar.zzauo();
                    if (query.moveToNext()) {
                        zzaul().zzayd().zzj("Got multiple records for app, expected one. appId", zzcbw.zzjf(str));
                    }
                    if (query == null) {
                        return com_google_android_gms_internal_zzcar;
                    }
                    query.close();
                    return com_google_android_gms_internal_zzcar;
                }
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    zzaul().zzayd().zze("Error querying app. appId", zzcbw.zzjf(str), e);
                    if (query != null) {
                        query.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            query = null;
            zzaul().zzayd().zze("Error querying app. appId", zzcbw.zzjf(str), e);
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    public final long zzix(String str) {
        zzbp.zzgg(str);
        zzuj();
        zzwk();
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            String valueOf = String.valueOf(Math.max(0, Math.min(1000000, zzaun().zzb(str, zzcbm.zzioq))));
            return (long) writableDatabase.delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str, valueOf});
        } catch (SQLiteException e) {
            zzaul().zzayd().zze("Error deleting over the limit events. appId", zzcbw.zzjf(str), e);
            return 0;
        }
    }

    public final byte[] zziy(String str) {
        Object e;
        Throwable th;
        zzbp.zzgg(str);
        zzuj();
        zzwk();
        Cursor query;
        try {
            query = getWritableDatabase().query("apps", new String[]{"remote_config"}, "app_id=?", new String[]{str}, null, null, null);
            try {
                if (query.moveToFirst()) {
                    byte[] blob = query.getBlob(0);
                    if (query.moveToNext()) {
                        zzaul().zzayd().zzj("Got multiple records for app config, expected one. appId", zzcbw.zzjf(str));
                    }
                    if (query == null) {
                        return blob;
                    }
                    query.close();
                    return blob;
                }
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    zzaul().zzayd().zze("Error querying remote config. appId", zzcbw.zzjf(str), e);
                    if (query != null) {
                        query.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            query = null;
            zzaul().zzayd().zze("Error querying remote config. appId", zzcbw.zzjf(str), e);
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    final Map<Integer, zzcgl> zziz(String str) {
        Object e;
        Throwable th;
        zzwk();
        zzuj();
        zzbp.zzgg(str);
        Cursor query;
        try {
            query = getWritableDatabase().query("audience_filter_values", new String[]{"audience_id", "current_results"}, "app_id=?", new String[]{str}, null, null, null);
            if (query.moveToFirst()) {
                Map<Integer, zzcgl> arrayMap = new ArrayMap();
                do {
                    int i = query.getInt(0);
                    byte[] blob = query.getBlob(1);
                    zzeye zzm = zzeye.zzm(blob, 0, blob.length);
                    zzeyn com_google_android_gms_internal_zzcgl = new zzcgl();
                    try {
                        com_google_android_gms_internal_zzcgl.zza(zzm);
                        try {
                            arrayMap.put(Integer.valueOf(i), com_google_android_gms_internal_zzcgl);
                        } catch (SQLiteException e2) {
                            e = e2;
                        }
                    } catch (IOException e3) {
                        zzaul().zzayd().zzd("Failed to merge filter results. appId, audienceId, error", zzcbw.zzjf(str), Integer.valueOf(i), e3);
                    }
                } while (query.moveToNext());
                if (query == null) {
                    return arrayMap;
                }
                query.close();
                return arrayMap;
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (SQLiteException e4) {
            e = e4;
            query = null;
            try {
                zzaul().zzayd().zze("Database error querying filter results. appId", zzcbw.zzjf(str), e);
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    public final long zzja(String str) {
        zzbp.zzgg(str);
        return zza("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0);
    }

    public final List<Pair<zzcgk, Long>> zzl(String str, int i, int i2) {
        Cursor query;
        List<Pair<zzcgk, Long>> arrayList;
        Object e;
        Cursor cursor;
        Throwable th;
        boolean z = true;
        zzuj();
        zzwk();
        zzbp.zzbh(i > 0);
        if (i2 <= 0) {
            z = false;
        }
        zzbp.zzbh(z);
        zzbp.zzgg(str);
        try {
            query = getWritableDatabase().query("queue", new String[]{"rowid", "data"}, "app_id=?", new String[]{str}, null, null, "rowid", String.valueOf(i));
            try {
                if (query.moveToFirst()) {
                    arrayList = new ArrayList();
                    int i3 = 0;
                    while (true) {
                        long j = query.getLong(0);
                        int length;
                        try {
                            byte[] zzq = zzauh().zzq(query.getBlob(1));
                            if (!arrayList.isEmpty() && zzq.length + i3 > i2) {
                                break;
                            }
                            zzeye zzm = zzeye.zzm(zzq, 0, zzq.length);
                            zzeyn com_google_android_gms_internal_zzcgk = new zzcgk();
                            try {
                                com_google_android_gms_internal_zzcgk.zza(zzm);
                                length = zzq.length + i3;
                                arrayList.add(Pair.create(com_google_android_gms_internal_zzcgk, Long.valueOf(j)));
                            } catch (IOException e2) {
                                zzaul().zzayd().zze("Failed to merge queued bundle. appId", zzcbw.zzjf(str), e2);
                                length = i3;
                            }
                            if (!query.moveToNext() || length > i2) {
                                break;
                            }
                            i3 = length;
                        } catch (IOException e22) {
                            zzaul().zzayd().zze("Failed to unzip queued bundle. appId", zzcbw.zzjf(str), e22);
                            length = i3;
                        }
                    }
                    if (query != null) {
                        query.close();
                    }
                } else {
                    arrayList = Collections.emptyList();
                    if (query != null) {
                        query.close();
                    }
                }
            } catch (SQLiteException e3) {
                e = e3;
                cursor = query;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (SQLiteException e4) {
            e = e4;
            cursor = null;
            try {
                zzaul().zzayd().zze("Error querying bundles. appId", zzcbw.zzjf(str), e);
                arrayList = Collections.emptyList();
                if (cursor != null) {
                    cursor.close();
                }
                return arrayList;
            } catch (Throwable th3) {
                th = th3;
                query = cursor;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
        return arrayList;
    }

    protected final void zzuk() {
    }
}
