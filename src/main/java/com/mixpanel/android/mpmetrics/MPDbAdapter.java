package com.mixpanel.android.mpmetrics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.coinbase.android.db.TransactionORM;
import java.io.File;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class MPDbAdapter {
    private static final String CREATE_EVENTS_TABLE = ("CREATE TABLE " + Table.EVENTS.getName() + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "data" + " STRING NOT NULL, " + TransactionORM.COLUMN_CREATED_AT + " INTEGER NOT NULL);");
    private static final String CREATE_PEOPLE_TABLE = ("CREATE TABLE " + Table.PEOPLE.getName() + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "data" + " STRING NOT NULL, " + TransactionORM.COLUMN_CREATED_AT + " INTEGER NOT NULL);");
    private static final String EVENTS_TIME_INDEX = ("CREATE INDEX IF NOT EXISTS time_idx ON " + Table.EVENTS.getName() + " (" + TransactionORM.COLUMN_CREATED_AT + ");");
    private static final String PEOPLE_TIME_INDEX = ("CREATE INDEX IF NOT EXISTS time_idx ON " + Table.PEOPLE.getName() + " (" + TransactionORM.COLUMN_CREATED_AT + ");");
    private final MPDatabaseHelper mDb;

    private static class MPDatabaseHelper extends SQLiteOpenHelper {
        private final File mDatabaseFile;

        MPDatabaseHelper(Context context, String dbName) {
            super(context, dbName, null, 4);
            this.mDatabaseFile = context.getDatabasePath(dbName);
        }

        public void deleteDatabase() {
            close();
            this.mDatabaseFile.delete();
        }

        public void onCreate(SQLiteDatabase db) {
            if (MPConfig.DEBUG) {
                Log.d("MixpanelAPI", "Creating a new Mixpanel events DB");
            }
            db.execSQL(MPDbAdapter.CREATE_EVENTS_TABLE);
            db.execSQL(MPDbAdapter.CREATE_PEOPLE_TABLE);
            db.execSQL(MPDbAdapter.EVENTS_TIME_INDEX);
            db.execSQL(MPDbAdapter.PEOPLE_TIME_INDEX);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (MPConfig.DEBUG) {
                Log.d("MixpanelAPI", "Upgrading app, replacing Mixpanel events DB");
            }
            db.execSQL("DROP TABLE IF EXISTS " + Table.EVENTS.getName());
            db.execSQL("DROP TABLE IF EXISTS " + Table.PEOPLE.getName());
            db.execSQL(MPDbAdapter.CREATE_EVENTS_TABLE);
            db.execSQL(MPDbAdapter.CREATE_PEOPLE_TABLE);
            db.execSQL(MPDbAdapter.EVENTS_TIME_INDEX);
            db.execSQL(MPDbAdapter.PEOPLE_TIME_INDEX);
        }
    }

    public enum Table {
        EVENTS("events"),
        PEOPLE("people");
        
        private final String mTableName;

        private Table(String name) {
            this.mTableName = name;
        }

        public String getName() {
            return this.mTableName;
        }
    }

    public MPDbAdapter(Context context) {
        this(context, "mixpanel");
    }

    public MPDbAdapter(Context context, String dbName) {
        this.mDb = new MPDatabaseHelper(context, dbName);
    }

    public int addJSON(JSONObject j, Table table) {
        String tableName = table.getName();
        Cursor c = null;
        int count = -1;
        try {
            SQLiteDatabase db = this.mDb.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("data", j.toString());
            cv.put(TransactionORM.COLUMN_CREATED_AT, Long.valueOf(System.currentTimeMillis()));
            db.insert(tableName, null, cv);
            c = db.rawQuery("SELECT COUNT(*) FROM " + tableName, null);
            c.moveToFirst();
            count = c.getInt(0);
        } catch (SQLiteException e) {
            Log.e("MixpanelAPI", "addJSON " + tableName + " FAILED. Deleting DB.", e);
            if (c != null) {
                c.close();
                c = null;
            }
            this.mDb.deleteDatabase();
        } finally {
            if (c != null) {
                c.close();
            }
            this.mDb.close();
        }
        return count;
    }

    public void cleanupEvents(String last_id, Table table) {
        String tableName = table.getName();
        try {
            this.mDb.getWritableDatabase().delete(tableName, "_id <= " + last_id, null);
        } catch (SQLiteException e) {
            Log.e("MixpanelAPI", "cleanupEvents " + tableName + " by id FAILED. Deleting DB.", e);
            this.mDb.deleteDatabase();
        } finally {
            this.mDb.close();
        }
    }

    public void cleanupEvents(long time, Table table) {
        String tableName = table.getName();
        try {
            this.mDb.getWritableDatabase().delete(tableName, "created_at <= " + time, null);
        } catch (SQLiteException e) {
            Log.e("MixpanelAPI", "cleanupEvents " + tableName + " by time FAILED. Deleting DB.", e);
            this.mDb.deleteDatabase();
        } finally {
            this.mDb.close();
        }
    }

    public void deleteDB() {
        this.mDb.deleteDatabase();
    }

    public String[] generateDataString(Table table) {
        Cursor c = null;
        String data = null;
        String last_id = null;
        String tableName = table.getName();
        try {
            c = this.mDb.getReadableDatabase().rawQuery("SELECT * FROM " + tableName + " ORDER BY " + TransactionORM.COLUMN_CREATED_AT + " ASC LIMIT 50", null);
            JSONArray arr = new JSONArray();
            while (c.moveToNext()) {
                if (c.isLast()) {
                    last_id = c.getString(c.getColumnIndex("_id"));
                }
                try {
                    arr.put(new JSONObject(c.getString(c.getColumnIndex("data"))));
                } catch (JSONException e) {
                }
            }
            if (arr.length() > 0) {
                data = arr.toString();
            }
            this.mDb.close();
            if (c != null) {
                c.close();
            }
        } catch (SQLiteException e2) {
            Log.e("MixpanelAPI", "generateDataString " + tableName, e2);
            last_id = null;
            data = null;
            this.mDb.close();
            if (c != null) {
                c.close();
            }
        } catch (Throwable th) {
            this.mDb.close();
            if (c != null) {
                c.close();
            }
        }
        if (last_id == null || data == null) {
            return null;
        }
        return new String[]{last_id, data};
    }
}
