package com.coinbase.android.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import com.coinbase.api.internal.ApiConstants;
import com.coinbase.v2.models.transactions.Amount;
import com.coinbase.v2.models.transactions.Data;
import com.coinbase.v2.models.transactions.Details;
import com.coinbase.v2.models.transactions.Entity;
import com.coinbase.v2.models.transactions.NativeAmount;
import com.coinbase.v2.models.transactions.PaymentMethod;
import com.coinbase.v2.models.transactions.Trade;
import java.util.ArrayList;
import java.util.List;

public class TransactionORM implements BaseColumns {
    public static final String COLUMN_ACCOUNT_ID = "account_id";
    public static final String COLUMN_AMOUNT_CURRENCY = "amount_currency";
    public static final String COLUMN_AMOUNT_STRING = "amount";
    public static final String COLUMN_AVATAR_URL = "avatar_url";
    public static final String COLUMN_CONFIRMATIONS = "confirmations";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_DELAYED = "delayed";
    public static final String COLUMN_DELAYED_SEND_DATE = "delayed_send_date";
    public static final String COLUMN_DETAILS_PAYMENT_METHOD_NAME = "deatils_payment_method_name";
    public static final String COLUMN_DETAILS_SUBTITLE = "details_subtitle";
    public static final String COLUMN_DETAILS_TITLE = "details_title";
    public static final String COLUMN_NATIVE_AMOUNT_CURRENCY = "native_amount_currency";
    public static final String COLUMN_NATIVE_AMOUNT_STRING = "native_amount";
    public static final String COLUMN_NOTES = "notes";
    public static final String COLUMN_PAYMENT_METHOD_ID = "payment_method_id";
    public static final String COLUMN_RESOURCE = "resource";
    public static final String COLUMN_RESOURCE_ADDRESS = "resource_address";
    public static final String COLUMN_RESOURCE_EMAIL = "resource_email";
    public static final String COLUMN_RESOURCE_ID = "resource_id";
    public static final String COLUMN_RESOURCE_NAME = "resource_name";
    public static final String COLUMN_RESOURCE_RESOURCE = "resource_resource";
    public static final String COLUMN_RESOURCE_TYPE = "resource_type";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_TRANSACTION_ID = "transaction_id";
    public static final String COLUMN_TYPE = "type";
    private static final String COMMA_SEP = ", ";
    public static final String INTEGER_TYPE = " INTEGER";
    public static final String SQL_CREATE_TABLE = "CREATE TABLE Transactions (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, account_id TEXT, transaction_id TEXT, delayed_send_date TEXT, amount TEXT, amount_currency TEXT, native_amount TEXT, native_amount_currency TEXT, resource_type TEXT, resource_id TEXT, resource_name TEXT, resource_email TEXT, resource_address TEXT, resource_resource TEXT, payment_method_id TEXT, type TEXT, resource TEXT, confirmations INTEGER, status TEXT, notes TEXT, delayed INTEGER, details_title INTEGER, details_subtitle INTEGER, deatils_payment_method_name INTEGER, avatar_url TEXT, created_at TEXT)";
    public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS Transactions";
    private static final String TABLE_NAME = "Transactions";
    public static final String TEXT_TYPE = " TEXT";

    public static ContentValues toContentValues(String accountId, Data tx) {
        ContentValues values = new ContentValues();
        values.put("account_id", accountId);
        values.put(COLUMN_TRANSACTION_ID, tx.getId());
        if (tx.getDelayedSendDate() != null) {
            values.put(COLUMN_DELAYED_SEND_DATE, tx.getDelayedSendDate());
        }
        Amount amount = tx.getAmount();
        if (amount != null) {
            values.put("amount", amount.getAmount());
            values.put(COLUMN_AMOUNT_CURRENCY, amount.getCurrency());
        }
        NativeAmount nativeAmount = tx.getNativeAmount();
        if (nativeAmount != null) {
            values.put(COLUMN_NATIVE_AMOUNT_STRING, nativeAmount.getAmount());
            values.put(COLUMN_NATIVE_AMOUNT_CURRENCY, nativeAmount.getCurrency());
        }
        values.put(COLUMN_NOTES, tx.getDescription());
        values.put(COLUMN_CREATED_AT, tx.getCreatedAt());
        if (tx.getTo() != null) {
            values.put(COLUMN_RESOURCE_TYPE, "to");
            setEntityValues(values, tx.getTo());
        } else if (tx.getFrom() != null) {
            values.put(COLUMN_RESOURCE_TYPE, "from");
            setEntityValues(values, tx.getFrom());
        } else if (tx.getBuy() != null) {
            values.put(COLUMN_RESOURCE_TYPE, "buy");
            setTradeValues(values, tx.getBuy());
        } else if (tx.getSell() != null) {
            values.put(COLUMN_RESOURCE_TYPE, "sell");
            setTradeValues(values, tx.getSell());
        } else if (tx.getRequest() != null) {
            values.put(COLUMN_RESOURCE_TYPE, "request");
            setTradeValues(values, tx.getRequest());
        } else if (tx.getSend() != null) {
            values.put(COLUMN_RESOURCE_TYPE, "send");
            setTradeValues(values, tx.getSend());
        } else if (tx.getTransfer() != null) {
            values.put(COLUMN_RESOURCE_TYPE, "transfer");
            setTradeValues(values, tx.getTransfer());
        } else if (tx.getFiatDeposit() != null) {
            values.put(COLUMN_RESOURCE_TYPE, ApiConstants.FIAT_DEPOSIT);
            setTradeValues(values, tx.getFiatDeposit());
        } else if (tx.getFiatWithdrawal() != null) {
            values.put(COLUMN_RESOURCE_TYPE, ApiConstants.FIAT_WITHDRAWAL);
            setTradeValues(values, tx.getFiatWithdrawal());
        } else if (tx.getExchangeDeposit() != null) {
            values.put(COLUMN_RESOURCE_TYPE, ApiConstants.EXCHANGE_DEPOSIT);
            setTradeValues(values, tx.getExchangeDeposit());
        } else if (tx.getExchangeWithdrawal() != null) {
            values.put(COLUMN_RESOURCE_TYPE, ApiConstants.EXCHANGE_WITHDRAWAL);
            setTradeValues(values, tx.getExchangeWithdrawal());
        } else if (tx.getVaultWithdrawal() != null) {
            values.put(COLUMN_RESOURCE_TYPE, ApiConstants.VAULT_WITHDRAWAL);
            setTradeValues(values, tx.getVaultWithdrawal());
        }
        values.put(COLUMN_RESOURCE, tx.getResource());
        String type = tx.getType();
        if (type == null) {
            type = ApiConstants.NONE;
        }
        values.put("type", type);
        String status = tx.getStatus();
        if (status == null) {
            status = "pending";
        }
        values.put("status", status);
        values.put(COLUMN_DELAYED, Boolean.valueOf(tx.getDelayed() == null ? false : tx.getDelayed().booleanValue()));
        if (tx.getDetails() != null) {
            values.put(COLUMN_DETAILS_TITLE, tx.getDetails().getTitle());
            values.put(COLUMN_DETAILS_SUBTITLE, tx.getDetails().getSubtitle());
            if (tx.getDetails().getPaymetnMethodName() != null) {
                values.put(COLUMN_DETAILS_PAYMENT_METHOD_NAME, tx.getDetails().getPaymetnMethodName());
            }
        }
        return values;
    }

    private static void setEntityValues(ContentValues values, Entity entity) {
        values.put(COLUMN_RESOURCE_RESOURCE, entity.getResource());
        values.put(COLUMN_RESOURCE_ID, entity.getId());
        values.put(COLUMN_RESOURCE_EMAIL, entity.getEmail());
        values.put(COLUMN_RESOURCE_NAME, entity.getName());
        values.put(COLUMN_RESOURCE_ADDRESS, entity.getAddress());
        values.put(COLUMN_AVATAR_URL, entity.getAvatarUrl());
    }

    private static void setTradeValues(ContentValues values, Trade trade) {
        values.put(COLUMN_RESOURCE_RESOURCE, trade.getResource());
        values.put(COLUMN_RESOURCE_ID, trade.getId());
        if (trade.getPaymentMethod() != null) {
            values.put(COLUMN_PAYMENT_METHOD_ID, trade.getPaymentMethod().getId());
        }
    }

    public static Data fromCursor(Cursor c) {
        Data result = new Data();
        result.setId(c.getString(c.getColumnIndex(COLUMN_TRANSACTION_ID)));
        String resourceType = c.getString(c.getColumnIndex(COLUMN_RESOURCE_TYPE));
        if (resourceType != null) {
            if (!resourceType.equals("to") && !resourceType.equals("from")) {
                if (resourceType.equals("buy") || resourceType.equals("sell") || resourceType.equals("request") || resourceType.equals("send") || resourceType.equals("transfer") || resourceType.equals(ApiConstants.FIAT_DEPOSIT) || resourceType.equals(ApiConstants.FIAT_WITHDRAWAL) || resourceType.equals(ApiConstants.EXCHANGE_DEPOSIT) || resourceType.equals(ApiConstants.EXCHANGE_WITHDRAWAL) || resourceType.equals(ApiConstants.VAULT_WITHDRAWAL)) {
                    Trade trade = new Trade();
                    trade.setId(c.getString(c.getColumnIndex(COLUMN_RESOURCE_ID)));
                    trade.setResource(c.getString(c.getColumnIndex(COLUMN_RESOURCE_RESOURCE)));
                    PaymentMethod paymentMethod = new PaymentMethod();
                    paymentMethod.setId(c.getString(c.getColumnIndex(COLUMN_PAYMENT_METHOD_ID)));
                    trade.setPaymentMethod(paymentMethod);
                    Object obj = -1;
                    switch (resourceType.hashCode()) {
                        case -895345771:
                            if (resourceType.equals(ApiConstants.FIAT_DEPOSIT)) {
                                obj = 5;
                                break;
                            }
                            break;
                        case -239241086:
                            if (resourceType.equals(ApiConstants.EXCHANGE_DEPOSIT)) {
                                obj = 7;
                                break;
                            }
                            break;
                        case 97926:
                            if (resourceType.equals("buy")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 3526482:
                            if (resourceType.equals("sell")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 3526536:
                            if (resourceType.equals("send")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 378019921:
                            if (resourceType.equals(ApiConstants.EXCHANGE_WITHDRAWAL)) {
                                obj = 8;
                                break;
                            }
                            break;
                        case 759513182:
                            if (resourceType.equals(ApiConstants.FIAT_WITHDRAWAL)) {
                                obj = 6;
                                break;
                            }
                            break;
                        case 993996002:
                            if (resourceType.equals(ApiConstants.VAULT_WITHDRAWAL)) {
                                obj = 9;
                                break;
                            }
                            break;
                        case 1095692943:
                            if (resourceType.equals("request")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 1280882667:
                            if (resourceType.equals("transfer")) {
                                obj = 4;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            result.setBuy(trade);
                            break;
                        case 1:
                            result.setSell(trade);
                            break;
                        case 2:
                            result.setRequest(trade);
                            break;
                        case 3:
                            result.setSend(trade);
                            break;
                        case 4:
                            result.setTransfer(trade);
                            break;
                        case 5:
                            result.setFiatDeposit(trade);
                            break;
                        case 6:
                            result.setFiatWithdrawal(trade);
                            break;
                        case 7:
                            result.setExchangeDeposit(trade);
                            break;
                        case 8:
                            result.setExchangeWithdrawal(trade);
                            break;
                        case 9:
                            result.setVaultWithdrawal(trade);
                            break;
                        default:
                            break;
                    }
                }
            }
            Entity entity = new Entity();
            entity.setId(c.getString(c.getColumnIndex(COLUMN_RESOURCE_ID)));
            entity.setName(c.getString(c.getColumnIndex(COLUMN_RESOURCE_NAME)));
            entity.setEmail(c.getString(c.getColumnIndex(COLUMN_RESOURCE_EMAIL)));
            entity.setAddress(c.getString(c.getColumnIndex(COLUMN_RESOURCE_ADDRESS)));
            entity.setResource(c.getString(c.getColumnIndex(COLUMN_RESOURCE_RESOURCE)));
            entity.setAvatarUrl(c.getString(c.getColumnIndex(COLUMN_AVATAR_URL)));
            if (resourceType.equals("to")) {
                result.setTo(entity);
            } else {
                result.setFrom(entity);
            }
        }
        result.setCreatedAt(c.getString(c.getColumnIndex(COLUMN_CREATED_AT)));
        String currencyCode = c.getString(c.getColumnIndex(COLUMN_AMOUNT_CURRENCY));
        String amountString = c.getString(c.getColumnIndex("amount"));
        if (!(currencyCode == null || amountString == null)) {
            Amount amount = new Amount();
            amount.setAmount(amountString);
            amount.setCurrency(currencyCode);
            result.setAmount(amount);
        }
        String delayedDate = c.getString(c.getColumnIndex(COLUMN_DELAYED_SEND_DATE));
        if (delayedDate != null) {
            result.setDelayedSendDate(delayedDate);
        }
        String NativeCurrencyCode = c.getString(c.getColumnIndex(COLUMN_NATIVE_AMOUNT_CURRENCY));
        String NativeAmountString = c.getString(c.getColumnIndex(COLUMN_NATIVE_AMOUNT_STRING));
        if (!(NativeCurrencyCode == null || NativeAmountString == null)) {
            NativeAmount nativeAmount = new NativeAmount();
            nativeAmount.setAmount(NativeAmountString);
            nativeAmount.setCurrency(NativeCurrencyCode);
            result.setNativeAmount(nativeAmount);
        }
        result.setResource(c.getString(c.getColumnIndex(COLUMN_RESOURCE)));
        result.setDescription(c.getString(c.getColumnIndex(COLUMN_NOTES)));
        result.setType(c.getString(c.getColumnIndex("type")));
        result.setStatus(c.getString(c.getColumnIndex("status")));
        result.setDelayed(Boolean.valueOf(c.getInt(c.getColumnIndex(COLUMN_DELAYED)) != 0));
        Details details = new Details();
        details.setTitle(c.getString(c.getColumnIndex(COLUMN_DETAILS_TITLE)));
        details.setSubtitle(c.getString(c.getColumnIndex(COLUMN_DETAILS_SUBTITLE)));
        details.setPaymentMethodName(c.getString(c.getColumnIndex(COLUMN_DETAILS_PAYMENT_METHOD_NAME)));
        result.setDetails(details);
        return result;
    }

    public static void insertOrUpdate(SQLiteDatabase db, String accountId, Data tx) {
        db.delete(TABLE_NAME, "transaction_id = ?", new String[]{tx.getId()});
        long result = db.insert(TABLE_NAME, "_id", toContentValues(accountId, tx));
    }

    public static Data find(SQLiteDatabase db, String id) {
        SQLiteDatabase sQLiteDatabase = db;
        Cursor c = sQLiteDatabase.query(TABLE_NAME, null, "transaction_id = ?", new String[]{id}, null, null, "created_at DESC");
        if (c.moveToFirst()) {
            return fromCursor(c);
        }
        return null;
    }

    public static long delete(SQLiteDatabase db, Data tx) {
        return (long) db.delete(TABLE_NAME, "transaction_id = ?", new String[]{tx.getId()});
    }

    public static long clear(SQLiteDatabase db, String accountId) {
        return (long) db.delete(TABLE_NAME, "account_id = ? AND delayed = ?", new String[]{accountId, "0"});
    }

    public static List<Data> getDelayedTransactions(SQLiteDatabase db, String accountId) {
        SQLiteDatabase sQLiteDatabase = db;
        Cursor c = sQLiteDatabase.query(TABLE_NAME, null, "account_id = ? AND delayed = ?", new String[]{accountId, "1"}, null, null, "created_at DESC");
        ArrayList<Data> result = new ArrayList();
        c.moveToFirst();
        while (!c.isAfterLast()) {
            result.add(fromCursor(c));
            c.moveToNext();
        }
        return result;
    }

    public static List<Data> getTransactions(SQLiteDatabase db, String accountId) {
        SQLiteDatabase sQLiteDatabase = db;
        Cursor c = sQLiteDatabase.query(TABLE_NAME, null, "account_id = ? AND delayed = ?", new String[]{accountId, "0"}, null, null, "created_at DESC");
        ArrayList<Data> result = new ArrayList();
        c.moveToFirst();
        while (!c.isAfterLast()) {
            result.add(fromCursor(c));
            c.moveToNext();
        }
        return result;
    }
}
