package com.coinbase.android.task;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.util.Pair;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.BackgroundScheduler;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.Constants;
import com.coinbase.android.Log;
import com.coinbase.android.db.AccountORM;
import com.coinbase.android.db.DatabaseManager;
import com.coinbase.android.settings.AccountsUpdatedConnector;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.utils.AccountUtils;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.ApiConstants;
import com.coinbase.v2.models.account.Accounts;
import com.coinbase.v2.models.account.Data;
import com.coinbase.v2.models.price.Prices;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import retrofit2.Response;
import rx.Observable;
import rx.Scheduler;

@ActivityScope
public class SyncAccountsTask {
    Context context;
    @Inject
    protected DatabaseManager dbManager;
    @Inject
    LoginManager loginManager;
    @Inject
    AccountsUpdatedConnector mAccountsUpdatedConnector;
    @Inject
    @BackgroundScheduler
    Scheduler mBackgroundScheduler;
    private SyncAccountsListener mListener;
    @MainScheduler
    @Inject
    Scheduler mMainScheduler;
    @Inject
    MoneyFormatterUtil mMoneyFormatterUtil;

    public interface SyncAccountsListener {
        void onException();

        void onFinally();

        void onPreExecute();
    }

    SyncAccountsTask(MoneyFormatterUtil moneyFormatterUtil) {
        this.mMoneyFormatterUtil = moneyFormatterUtil;
    }

    public SyncAccountsTask(Context context, SyncAccountsListener listener) {
        this.context = context;
        this.mListener = listener;
        ((ComponentProvider) context.getApplicationContext()).applicationComponent().tasksSubcomponent().inject(this);
    }

    private void handleException() {
        if (this.mListener != null) {
            this.mListener.onException();
            this.mListener.onFinally();
        }
    }

    public void syncAccounts() {
        if (this.loginManager.isSignedIn()) {
            if (this.mListener != null) {
                this.mListener.onPreExecute();
            }
            HashMap<String, Object> options = new HashMap();
            options.put(ApiConstants.LIMIT, Integer.valueOf(100));
            Observable.combineLatest(this.loginManager.getClient().getAccountsRx(options), this.loginManager.getClient().getSpotPricesRx(this.loginManager.getCurrencyUnit().getCurrencyCode(), new HashMap()), SyncAccountsTask$$Lambda$1.lambdaFactory$()).map(SyncAccountsTask$$Lambda$2.lambdaFactory$(this)).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainScheduler).subscribe(SyncAccountsTask$$Lambda$3.lambdaFactory$(this), SyncAccountsTask$$Lambda$4.lambdaFactory$(this));
        }
    }

    static /* synthetic */ Response lambda$syncAccounts$1(SyncAccountsTask this_, Pair response) {
        Response<Accounts> accountResponse = response.first.first;
        if (accountResponse.isSuccessful()) {
            this_.reconcileAccountNativeBalance(accountResponse, response.second.first);
            this_.updateDataBase(accountResponse);
        }
        return accountResponse;
    }

    static /* synthetic */ void lambda$syncAccounts$2(SyncAccountsTask this_, Response accountResponse) {
        if (!accountResponse.isSuccessful()) {
            this_.handleException();
        } else if (this_.mListener != null) {
            this_.mListener.onFinally();
        }
    }

    void reconcileAccountNativeBalance(Response<Accounts> accountResponse, Response<Prices> spotPricesResponse) {
        if (accountResponse != null && spotPricesResponse != null) {
            List<Data> accountList = ((Accounts) accountResponse.body()).getData();
            List<com.coinbase.v2.models.price.Data> spotPriceList = ((Prices) spotPricesResponse.body()).getData();
            if (accountList != null && spotPriceList != null) {
                com.coinbase.v2.models.price.Data spotPrice;
                Map<String, com.coinbase.v2.models.price.Data> spotPriceMap = new HashMap();
                for (com.coinbase.v2.models.price.Data spotPrice2 : spotPriceList) {
                    spotPriceMap.put(spotPrice2.getBase(), spotPrice2);
                }
                for (Data account : accountList) {
                    spotPrice2 = (com.coinbase.v2.models.price.Data) spotPriceMap.get(account.getCurrency().getCode());
                    if (spotPrice2 != null) {
                        AccountUtils.setAccountNativeBalanceMoney(account, spotPrice2, this.mMoneyFormatterUtil);
                    }
                }
            }
        }
    }

    private void updateDataBase(Response<Accounts> response) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.context);
        Editor e = prefs.edit();
        List<Data> responseAccounts = ((Accounts) response.body()).getData();
        List<Data> savedAccounts = this.loginManager.getAccounts();
        SQLiteDatabase db = this.dbManager.openDatabase();
        Data activeAccount = null;
        AccountUtils.setIfEthAccountsExists(responseAccounts, prefs);
        boolean foundPrimaryAccount = false;
        try {
            for (Data account : savedAccounts) {
                Data remoteAccount = popAccountInList(responseAccounts, account.getId());
                if (remoteAccount != null) {
                    AccountORM.update(db, remoteAccount);
                    if (remoteAccount.getPrimary().booleanValue()) {
                        activeAccount = remoteAccount;
                        e.putString(Constants.KEY_ACTIVE_ACCOUNT_ID, remoteAccount.getId());
                        if (account.getNativeBalance() != null) {
                            e.putString(Constants.KEY_ACCOUNT_NATIVE_CURRENCY, remoteAccount.getNativeBalance().getCurrency());
                        }
                        e.putBoolean(Constants.KEY_ACCOUNT_VALID, true);
                        e.apply();
                        foundPrimaryAccount = true;
                    }
                } else {
                    AccountORM.delete(db, account);
                }
            }
            if (responseAccounts != null) {
                for (Data account2 : responseAccounts) {
                    if (account2.getActive().booleanValue()) {
                        AccountORM.insert(db, account2);
                        if (account2.getPrimary().booleanValue()) {
                            activeAccount = account2;
                            e.putString(Constants.KEY_ACTIVE_ACCOUNT_ID, account2.getId());
                            if (account2.getNativeBalance() != null) {
                                e.putString(Constants.KEY_ACCOUNT_NATIVE_CURRENCY, account2.getNativeBalance().getCurrency());
                            }
                            foundPrimaryAccount = true;
                        }
                        e.apply();
                    }
                }
                if (!foundPrimaryAccount) {
                    Log.e("SyncAccountsTask", "Could not find primary account");
                }
                this.dbManager.closeDatabase();
                this.mAccountsUpdatedConnector.get().onNext(activeAccount);
            }
        } finally {
            this.dbManager.closeDatabase();
            this.mAccountsUpdatedConnector.get().onNext(activeAccount);
        }
    }

    private Data popAccountInList(List<Data> accounts, String accountId) {
        if (accounts != null) {
            for (Data account : accounts) {
                if (account.getId().equals(accountId)) {
                    accounts.remove(account);
                    return account;
                }
            }
        }
        return null;
    }
}
