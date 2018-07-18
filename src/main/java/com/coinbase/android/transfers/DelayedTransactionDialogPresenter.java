package com.coinbase.android.transfers;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import com.coinbase.android.Constants;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.db.DatabaseManager;
import com.coinbase.android.db.TransactionORM;
import com.coinbase.android.event.TransferMadeEvent;
import com.coinbase.v2.models.transactions.Data;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerScope
public class DelayedTransactionDialogPresenter {
    static String ACCOUNT = "DelayedTransactionDialogFragment_Account";
    static String TRANSACTION = "DelayedTransactionDialogFragment_Transaction";
    private String mAccountId;
    private final Context mContext;
    private final DatabaseManager mDbManager;
    private final Logger mLogger = LoggerFactory.getLogger(DelayedTransactionDialogPresenter.class);
    private Data mTransaction;
    private final TransferMadeConnector mTransferMadeConnector;

    @Inject
    public DelayedTransactionDialogPresenter(DatabaseManager databaseManager, TransferMadeConnector transferMadeConnector, Application app) {
        this.mDbManager = databaseManager;
        this.mTransferMadeConnector = transferMadeConnector;
        this.mContext = app;
    }

    public void onCreate(Bundle args) {
        this.mTransaction = (Data) new Gson().fromJson(args.getString(TRANSACTION), Data.class);
        this.mAccountId = args.getString(ACCOUNT);
    }

    public void onConfirm() {
        if (this.mTransaction == null) {
            this.mLogger.error("Transaction is null in delayed transaction presenter, shouldn't happen");
            return;
        }
        this.mTransaction.setStatus("pending");
        Date date = new Date();
        this.mTransaction.setCreatedAt(new SimpleDateFormat(Constants.DATE_FORMAT).format(date));
        this.mTransaction.setId(UUID.randomUUID().toString());
        this.mTransaction.setDelayed(Boolean.valueOf(true));
        try {
            TransactionORM.insertOrUpdate(this.mDbManager.openDatabase(), this.mAccountId, this.mTransaction);
            this.mTransferMadeConnector.get().onNext(new TransferMadeEvent(this.mTransaction, null));
            this.mContext.getPackageManager().setComponentEnabledSetting(new ComponentName(this.mContext, ConnectivityChangeReceiver.class), 1, 1);
        } finally {
            this.mDbManager.closeDatabase();
        }
    }
}
