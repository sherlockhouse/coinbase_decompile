package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;

@TargetApi(11)
final class zzcbt extends SQLiteOpenHelper {
    private /* synthetic */ zzcbs zzipn;

    zzcbt(zzcbs com_google_android_gms_internal_zzcbs, Context context, String str) {
        this.zzipn = com_google_android_gms_internal_zzcbs;
        super(context, str, null, 1);
    }

    public final SQLiteDatabase getWritableDatabase() {
        try {
            return super.getWritableDatabase();
        } catch (SQLiteException e) {
            if (VERSION.SDK_INT < 11 || !(e instanceof SQLiteDatabaseLockedException)) {
                this.zzipn.zzaul().zzayd().log("Opening the local database failed, dropping and recreating it");
                String zzawj = zzcax.zzawj();
                if (!this.zzipn.getContext().getDatabasePath(zzawj).delete()) {
                    this.zzipn.zzaul().zzayd().zzj("Failed to delete corrupted local db file", zzawj);
                }
                try {
                    return super.getWritableDatabase();
                } catch (SQLiteException e2) {
                    this.zzipn.zzaul().zzayd().zzj("Failed to open local database. Events will bypass local storage", e2);
                    return null;
                }
            }
            throw e2;
        }
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        zzcay.zza(this.zzipn.zzaul(), sQLiteDatabase);
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public final void onOpen(SQLiteDatabase sQLiteDatabase) {
        if (VERSION.SDK_INT < 15) {
            Cursor rawQuery = sQLiteDatabase.rawQuery("PRAGMA journal_mode=memory", null);
            try {
                rawQuery.moveToFirst();
            } finally {
                rawQuery.close();
            }
        }
        zzcay.zza(this.zzipn.zzaul(), sQLiteDatabase, "messages", "create table if not exists messages ( type INTEGER NOT NULL, entry BLOB NOT NULL)", "type,entry", null);
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
