package com.coinbase.android.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.coinbase.v2.models.account.Balance;
import com.coinbase.v2.models.account.Currency;
import com.coinbase.v2.models.account.Data;
import com.coinbase.v2.models.account.Data.Type;
import com.coinbase.v2.models.account.Image;
import com.coinbase.v2.models.account.NativeBalance;
import java.util.ArrayList;
import java.util.List;

public class AccountORM {
    public static final String COLUMN_ACCOUNT_ID = "account_id";
    public static final String COLUMN_BALANCE = "balance";
    public static final String COLUMN_CURRENCY_CODE = "currency_code";
    public static final String COLUMN_CURRENCY_COLOR = "currency_color";
    public static final String COLUMN_CURRENCY_EXPONENT = "currency_exponent";
    public static final String COLUMN_CURRENCY_IMAGE = "currency_image";
    public static final String COLUMN_CURRENCY_NAME = "currency_name";
    public static final String COLUMN_NAME = "account_name";
    public static final String COLUMN_NATIVE_BALANCE = "native_balance";
    public static final String COLUMN_NATIVE_CURRENCY = "native_currency";
    public static final String COLUMN_RECEIVE_ADDRESS = "receive_address";
    public static final String COLUMN_TYPE = "account_type";
    private static final String COMMA_SEP = ", ";
    public static final String INTEGER_TYPE = " INTEGER";
    public static final String SQL_CREATE_TABLE = "CREATE TABLE Accounts (account_id TEXT NOT NULL, account_name TEXT NOT NULL, receive_address TEXT, balance TEXT, native_balance TEXT, native_currency TEXT, account_type TEXT, currency_code TEXT, currency_image TEXT, currency_name TEXT, currency_color TEXT, currency_exponent INTEGER)";
    public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS Accounts";
    public static final String TABLE_NAME = "Accounts";
    public static final String TEXT_TYPE = " TEXT";

    public static Data fromCursor(Cursor c) {
        Data result = new Data();
        result.setId(c.getString(c.getColumnIndex("account_id")));
        result.setName(c.getString(c.getColumnIndex(COLUMN_NAME)));
        String currencyCodeString = c.getString(c.getColumnIndex("currency_code"));
        if (currencyCodeString != null) {
            Currency currency = new Currency();
            currency.setCode(currencyCodeString);
            currency.setName(c.getString(c.getColumnIndex(COLUMN_CURRENCY_NAME)));
            currency.setColor(c.getString(c.getColumnIndex(COLUMN_CURRENCY_COLOR)));
            currency.setExponent(Integer.valueOf(c.getInt(c.getColumnIndex(COLUMN_CURRENCY_EXPONENT))));
            Image image = new Image();
            image.setUrl(c.getString(c.getColumnIndex(COLUMN_CURRENCY_IMAGE)));
            currency.setImage(image);
            result.setCurrency(currency);
        }
        if (c.getString(c.getColumnIndex(COLUMN_BALANCE)) != null) {
            Balance balance = new Balance();
            balance.setAmount(c.getString(c.getColumnIndex(COLUMN_BALANCE)));
            balance.setCurrency(c.getString(c.getColumnIndex("currency_code")));
            result.setBalance(balance);
        }
        String nativeBalanceString = c.getString(c.getColumnIndex(COLUMN_NATIVE_BALANCE));
        String nativeBalanceCurrency = c.getString(c.getColumnIndex("native_currency"));
        if (!(nativeBalanceString == null || nativeBalanceCurrency == null)) {
            NativeBalance nativeBalance = new NativeBalance();
            nativeBalance.setAmount(nativeBalanceString);
            nativeBalance.setCurrency(nativeBalanceCurrency);
            result.setNativeBalance(nativeBalance);
        }
        String typeString = c.getString(c.getColumnIndex("account_type"));
        if (typeString != null) {
            result.setType(Type.toType(typeString));
        }
        return result;
    }

    public static long insert(SQLiteDatabase db, Data account) {
        ContentValues values = new ContentValues();
        values.put("account_id", account.getId());
        values.put(COLUMN_NAME, account.getName());
        values.put(COLUMN_BALANCE, account.getBalance().getAmount());
        values.put("account_type", account.getType().toString());
        values.put("currency_code", account.getCurrency().getCode());
        values.put(COLUMN_CURRENCY_NAME, account.getCurrency().getName());
        values.put(COLUMN_CURRENCY_COLOR, account.getCurrency().getColor());
        values.put(COLUMN_CURRENCY_EXPONENT, account.getCurrency().getExponent());
        if (account.getCurrency().getImage() != null) {
            values.put(COLUMN_CURRENCY_IMAGE, account.getCurrency().getImage().getUrl());
        }
        if (account.getNativeBalance() != null) {
            values.put(COLUMN_NATIVE_BALANCE, account.getNativeBalance().getAmount());
            values.put("native_currency", account.getNativeBalance().getCurrency());
        }
        return db.insert(TABLE_NAME, null, values);
    }

    public static long updateOrInsert(SQLiteDatabase db, Data account) {
        ContentValues values = new ContentValues();
        values.put("account_id", account.getId());
        values.put(COLUMN_NAME, account.getName());
        values.put(COLUMN_BALANCE, account.getBalance().getAmount());
        values.put("account_type", account.getType().toString());
        values.put("currency_code", account.getCurrency().getCode());
        values.put(COLUMN_CURRENCY_NAME, account.getCurrency().getName());
        values.put(COLUMN_CURRENCY_COLOR, account.getCurrency().getColor());
        values.put(COLUMN_CURRENCY_EXPONENT, account.getCurrency().getExponent());
        if (account.getCurrency().getImage() != null) {
            values.put(COLUMN_CURRENCY_IMAGE, account.getCurrency().getImage().getUrl());
        }
        if (account.getNativeBalance() != null) {
            values.put(COLUMN_NATIVE_BALANCE, account.getNativeBalance().getAmount());
            values.put("native_currency", account.getNativeBalance().getCurrency());
        }
        return db.insertWithOnConflict(TABLE_NAME, null, values, 5);
    }

    public static void update(SQLiteDatabase db, Data account) {
        ContentValues values = new ContentValues();
        values.put("account_id", account.getId());
        values.put(COLUMN_NAME, account.getName());
        values.put(COLUMN_BALANCE, account.getBalance().getAmount());
        values.put("account_type", account.getType().toString());
        values.put("currency_code", account.getCurrency().getCode());
        values.put(COLUMN_CURRENCY_NAME, account.getCurrency().getName());
        values.put(COLUMN_CURRENCY_COLOR, account.getCurrency().getColor());
        values.put(COLUMN_CURRENCY_EXPONENT, account.getCurrency().getExponent());
        if (account.getCurrency().getImage() != null) {
            values.put(COLUMN_CURRENCY_IMAGE, account.getCurrency().getImage().getUrl());
        }
        if (account.getNativeBalance() != null) {
            values.put(COLUMN_NATIVE_BALANCE, account.getNativeBalance().getAmount());
            values.put("native_currency", account.getNativeBalance().getCurrency());
        }
        db.update(TABLE_NAME, values, "account_id = ?", new String[]{account.getId()});
    }

    public static void delete(SQLiteDatabase db, Data account) {
        new ContentValues().put("account_id", account.getId());
        db.delete(TABLE_NAME, "account_id = ?", new String[]{account.getId()});
    }

    public static String getCachedReceiveAddress(SQLiteDatabase db, String accountId) {
        SQLiteDatabase sQLiteDatabase = db;
        Cursor c = sQLiteDatabase.query(TABLE_NAME, new String[]{COLUMN_RECEIVE_ADDRESS}, "account_id = ?", new String[]{accountId}, null, null, null);
        if (c.moveToFirst()) {
            return c.getString(c.getColumnIndex(COLUMN_RECEIVE_ADDRESS));
        }
        return null;
    }

    public static List<Data> list(SQLiteDatabase db) {
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, "account_id DESC");
        ArrayList<Data> result = new ArrayList();
        c.moveToFirst();
        while (!c.isAfterLast()) {
            result.add(fromCursor(c));
            c.moveToNext();
        }
        return result;
    }

    public static Data find(SQLiteDatabase db, String accountId) {
        SQLiteDatabase sQLiteDatabase = db;
        Cursor c = sQLiteDatabase.query(TABLE_NAME, null, "account_id = ?", new String[]{accountId}, null, null, "account_id DESC");
        if (c.moveToFirst()) {
            return fromCursor(c);
        }
        return null;
    }

    public static long clear(SQLiteDatabase db) {
        return (long) db.delete(TABLE_NAME, null, null);
    }

    public void setReceiveAddress(SQLiteDatabase db, String accountId, String receiveAddress) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_RECEIVE_ADDRESS, receiveAddress);
        db.update(TABLE_NAME, cv, "account_id = ?", new String[]{accountId});
    }
}
