package com.coinbase.android.transfers;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Log;
import com.coinbase.CallbackWithRetrofit;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.MainActivity;
import com.coinbase.android.R;
import com.coinbase.android.ServiceScope;
import com.coinbase.android.db.AccountORM;
import com.coinbase.android.db.DatabaseManager;
import com.coinbase.android.db.TransactionORM;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.TransactionUtils;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.LoginManager;
import com.coinbase.v2.models.account.Data;
import com.coinbase.v2.models.transactions.Transaction;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javax.inject.Inject;
import org.joda.money.BigMoney;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

@ServiceScope
public class DelayedTxSenderService extends Service {
    static final String GROUP_DELAYED_NOTIFICATION = "group_key_emails";
    private static int NOTIF_ID = -1;
    @Inject
    DatabaseManager mDbManager;
    @Inject
    LoginManager mLoginManager;
    @Inject
    MoneyFormatterUtil mMoneyFormatterUtil;
    int successCount = 0;
    boolean threadRunning = false;
    int transactionsCount = 0;

    public void onCreate() {
        super.onCreate();
        ((ComponentProvider) getApplicationContext()).applicationComponent().coinbaseServiceSubcomponent().inject(this);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!this.threadRunning) {
            new Thread(new Runnable() {
                public void run() {
                    DelayedTxSenderService.this.tryToSendAll();
                    DelayedTxSenderService.this.stopSelf();
                }
            }).start();
        }
        return 2;
    }

    private void tryToSendAll() {
        try {
            SQLiteDatabase db = this.mDbManager.openDatabase();
            List<Data> accounts = AccountORM.list(db);
            this.successCount = 0;
            this.transactionsCount = 0;
            for (Data account : accounts) {
                this.transactionsCount += TransactionORM.getDelayedTransactions(db, account.getId()).size();
            }
            this.mDbManager.closeDatabase();
            for (Data account2 : accounts) {
                List<com.coinbase.v2.models.transactions.Data> transactions = TransactionORM.getDelayedTransactions(this.mDbManager.openDatabase(), account2.getId());
                this.mDbManager.closeDatabase();
                for (com.coinbase.v2.models.transactions.Data tx : transactions) {
                    handleTransactionRequest(tx, account2);
                }
            }
        } catch (Exception e) {
        }
    }

    private void handleTransactionRequest(final com.coinbase.v2.models.transactions.Data tx, final Data account) {
        try {
            HashMap<String, Object> params = new HashMap();
            String toId = null;
            if (tx.getTo().getId() != null) {
                toId = tx.getTo().getId();
            } else if (tx.getTo().getEmail() != null) {
                toId = tx.getTo().getEmail();
            } else if (tx.getTo().getAddress() != null) {
                toId = tx.getTo().getAddress();
            }
            if (toId == null) {
                SQLiteDatabase db = this.mDbManager.openDatabase();
                db.beginTransaction();
                Log.e("Error", "Send delayed transaction error: to parameter is missing or invalid, deleting transaction");
                TransactionORM.delete(db, tx);
                db.setTransactionSuccessful();
                db.endTransaction();
                this.mDbManager.closeDatabase();
                return;
            }
            params.put("to", toId);
            params.put("amount", tx.getAmount().getAmount());
            params.put("currency", tx.getAmount().getCurrency());
            if (tx.getTo() == null) {
                this.mLoginManager.getClient().requestMoney(account.getId(), params, new CallbackWithRetrofit<Transaction>() {
                    public void onResponse(Call<Transaction> call, Response<Transaction> response, Retrofit retrofit) {
                        if (response.isSuccessful()) {
                            DelayedTxSenderService.this.handleRequestSuccess(((Transaction) response.body()).getData(), tx, account.getId());
                        } else {
                            Log.e("Error", "Send delayed transaction error: " + Utils.getErrorMessage(response, retrofit));
                        }
                        DelayedTxSenderService.this.checkCompletion();
                    }

                    public void onFailure(Call<Transaction> call, Throwable t) {
                    }
                });
            } else {
                this.mLoginManager.getClient().sendMoney(account.getId(), params, new CallbackWithRetrofit<Transaction>() {
                    public void onResponse(Call<Transaction> call, Response<Transaction> response, Retrofit retrofit) {
                        if (response.isSuccessful()) {
                            DelayedTxSenderService.this.handleRequestSuccess(((Transaction) response.body()).getData(), tx, account.getId());
                        } else {
                            Log.e("Error", "Send delayed transaction error: " + Utils.getErrorMessage(response, retrofit));
                        }
                        DelayedTxSenderService.this.checkCompletion();
                    }

                    public void onFailure(Call<Transaction> call, Throwable t) {
                    }
                });
            }
        } catch (Exception ex) {
            Log.e("DelayedTxSenderService", "Failed to ic_send delayed tx", ex);
        }
    }

    private void handleRequestSuccess(com.coinbase.v2.models.transactions.Data newTransaction, com.coinbase.v2.models.transactions.Data transaction, String accountId) {
        this.successCount++;
        SQLiteDatabase db = this.mDbManager.openDatabase();
        db.beginTransaction();
        TransactionORM.delete(db, transaction);
        TransactionORM.insertOrUpdate(db, accountId, newTransaction);
        db.setTransactionSuccessful();
        db.endTransaction();
        this.mDbManager.closeDatabase();
    }

    private void checkCompletion() {
        if (this.successCount >= this.transactionsCount) {
            getPackageManager().setComponentEnabledSetting(new ComponentName(this, ConnectivityChangeReceiver.class), 2, 1);
        }
    }

    private void showNotification(String errors, com.coinbase.v2.models.transactions.Data tx) {
        boolean isRequest;
        String title;
        String description;
        if (tx.getTo() == null) {
            isRequest = true;
        } else {
            isRequest = false;
        }
        if (errors == null) {
            if (isRequest) {
                title = getString(R.string.delayed_notification_success_request);
            } else {
                title = getString(R.string.delayed_notification_success_send);
            }
            description = getString(R.string.delayed_notification_success_subtitle);
        } else {
            if (isRequest) {
                title = getString(R.string.delayed_notification_failure_request);
            } else {
                title = getString(R.string.delayed_notification_failure_send);
            }
            description = errors;
        }
        String otherUser = isRequest ? tx.getFrom().getId() : tx.getTo().getId();
        BigMoney amount = TransactionUtils.moneyFromAmount(tx.getAmount());
        Builder mBuilder = new Builder(this).setSmallIcon(R.drawable.ic_notif_delayed).setContentTitle(String.format(title, new Object[]{otherUser, this.mMoneyFormatterUtil.formatMoney(amount)})).setGroup(GROUP_DELAYED_NOTIFICATION).setContentText(description).setAutoCancel(true);
        mBuilder.setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0));
        NotificationManager mNotificationManager = (NotificationManager) getSystemService("notification");
        if (NOTIF_ID == -1) {
            NOTIF_ID = new Random().nextInt();
        }
        mNotificationManager.notify(NOTIF_ID, mBuilder.build());
    }
}
