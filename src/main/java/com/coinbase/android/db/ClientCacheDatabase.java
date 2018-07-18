package com.coinbase.android.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClientCacheDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "coinbase_client_cache";
    public static final int DATABASE_VERSION = 26;

    public ClientCacheDatabase(Context context) {
        super(context, DATABASE_NAME, null, 26);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TransactionORM.SQL_CREATE_TABLE);
        db.execSQL(AccountORM.SQL_CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TransactionORM.SQL_DROP_TABLE);
        db.execSQL(AccountORM.SQL_DROP_TABLE);
        db.execSQL("DROP TABLE IF EXISTS AccountChanges");
        db.execSQL("DROP TABLE IF EXISTS DelayedTransactions");
        db.execSQL(TransactionORM.SQL_CREATE_TABLE);
        db.execSQL(AccountORM.SQL_CREATE_TABLE);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
