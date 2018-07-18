package com.coinbase.android.db;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import java.util.concurrent.atomic.AtomicInteger;

public class DatabaseManager {
    private ClientCacheDatabase mClientCacheDatabase;
    private SQLiteDatabase mDatabase;
    private AtomicInteger mOpenCounter = new AtomicInteger();

    public DatabaseManager(Application applicationContext) {
        this.mClientCacheDatabase = new ClientCacheDatabase(applicationContext);
    }

    public synchronized SQLiteDatabase openDatabase() {
        if (this.mOpenCounter.incrementAndGet() == 1) {
            this.mDatabase = this.mClientCacheDatabase.getWritableDatabase();
        }
        return this.mDatabase;
    }

    public synchronized void closeDatabase() {
        if (this.mOpenCounter.decrementAndGet() == 0) {
            if (this.mDatabase.inTransaction()) {
                this.mDatabase.endTransaction();
            }
            this.mDatabase.close();
        }
    }

    public boolean isDatabaseOpen() {
        return this.mOpenCounter.intValue() > 0;
    }
}
