package com.coinbase.android.transactions;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Pair;
import com.coinbase.android.BackgroundScheduler;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.db.DatabaseManager;
import com.coinbase.android.db.TransactionORM;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.RefreshRequestedConnector;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.ApiConstants;
import com.coinbase.v2.models.account.Data;
import com.coinbase.v2.models.transactions.Transactions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import retrofit2.Response;
import rx.Observable;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class TransactionListPresenter {
    private boolean isFetching = false;
    private final Scheduler mBackgroundScheduler;
    private final Context mContext;
    private final DatabaseManager mDbManager;
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private String mNextUri = null;
    private final RefreshRequestedConnector mRefreshRequestedConnector;
    private final TransactionListScreen mScreen;
    private Data mSelectedAccount;
    private final SnackBarWrapper mSnackBarWrapper;
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    private final TransactionDetailButtonConnector mTransactionDetailButtonConnector;
    private List<com.coinbase.v2.models.transactions.Data> mTransactionList = new ArrayList();
    private int retryCount;

    @Inject
    public TransactionListPresenter(LoginManager loginManager, DatabaseManager dbManager, TransactionListScreen screen, Application application, SnackBarWrapper snackBarWrapper, RefreshRequestedConnector refreshRequestedConnector, TransactionDetailButtonConnector transactionDetailButtonConector, @MainScheduler Scheduler mainScheduler, @BackgroundScheduler Scheduler backgroundScheduler) {
        this.mLoginManager = loginManager;
        this.mDbManager = dbManager;
        this.mScreen = screen;
        this.mContext = application;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mRefreshRequestedConnector = refreshRequestedConnector;
        this.mTransactionDetailButtonConnector = transactionDetailButtonConector;
        this.mMainScheduler = mainScheduler;
        this.mBackgroundScheduler = backgroundScheduler;
    }

    void onInit() {
        this.mSubscription.add(this.mRefreshRequestedConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(TransactionListPresenter$$Lambda$1.lambdaFactory$(this)));
        this.mSubscription.add(this.mTransactionDetailButtonConnector.get().onBackpressureLatest().filter(TransactionListPresenter$$Lambda$2.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(TransactionListPresenter$$Lambda$3.lambdaFactory$(this)));
    }

    static /* synthetic */ Boolean lambda$onInit$1(ActionType actionType) {
        boolean z = actionType == ActionType.CLOSE || actionType == ActionType.CANCEL;
        return Boolean.valueOf(z);
    }

    public void onDestroy() {
        this.mSubscription.clear();
    }

    List<com.coinbase.v2.models.transactions.Data> getTransactionDataList() {
        return this.mTransactionList;
    }

    void setSelectedAccount(Data account) {
        this.mSelectedAccount = account;
    }

    Data getSelectedAccount() {
        return this.mSelectedAccount;
    }

    void loadTransactions() {
        if (this.mSelectedAccount != null && !this.isFetching) {
            startFetchingTransactions();
            this.mNextUri = null;
            fetchTransactions();
        }
    }

    void loadMoreTransactions() {
        if (this.mSelectedAccount != null && !this.isFetching && this.mNextUri != null) {
            startFetchingTransactions();
            fetchNextTransactions();
        }
    }

    boolean canLoadMoreTransactions() {
        return this.mNextUri != null;
    }

    void onRefreshClicked() {
        this.mRefreshRequestedConnector.get().onNext(null);
    }

    void onTransactionItemClicked(com.coinbase.v2.models.transactions.Data transaction) {
        this.mScreen.showDetailLayout(transaction);
    }

    private void retryTransactions() {
        if (this.mNextUri == null) {
            fetchTransactions();
        } else {
            fetchNextTransactions();
        }
    }

    private void startFetchingTransactions() {
        this.isFetching = true;
        this.mScreen.startFetchingTransactions();
    }

    private void fetchingTransactionsComplete() {
        this.mScreen.fetchingTransactionsComplete();
        this.isFetching = false;
    }

    private void fetchTransactions() {
        this.mSubscription.add(this.mLoginManager.getClient().getTransactionsRx(this.mSelectedAccount.getId(), null, Arrays.asList(new String[]{"from", "to", "buy", "sell", "request", "send", "transfer", ApiConstants.FIAT_DEPOSIT, ApiConstants.FIAT_WITHDRAWAL, ApiConstants.EXCHANGE_DEPOSIT, ApiConstants.EXCHANGE_WITHDRAWAL, ApiConstants.VAULT_WITHDRAWAL})).map(TransactionListPresenter$$Lambda$4.lambdaFactory$(this)).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainScheduler).subscribe(TransactionListPresenter$$Lambda$5.lambdaFactory$(this), TransactionListPresenter$$Lambda$6.lambdaFactory$(this)));
    }

    static /* synthetic */ Pair lambda$fetchTransactions$3(TransactionListPresenter this_, Pair pair) {
        Response<Transactions> response = pair.first;
        if (response.isSuccessful()) {
            this_.clearTransactionsORM();
            this_.updateTransactionsORM(((Transactions) response.body()).getData());
        }
        return pair;
    }

    static /* synthetic */ void lambda$fetchTransactions$4(TransactionListPresenter this_, Pair pair) {
        Response<Transactions> response = pair.first;
        if (response.isSuccessful()) {
            this_.mTransactionList.clear();
        }
        this_.handleResponse(response);
    }

    private void fetchNextTransactions() {
        this.mSubscription.add(this.mLoginManager.getClient().getNextTransactionsRx(this.mNextUri).map(TransactionListPresenter$$Lambda$7.lambdaFactory$(this)).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainScheduler).subscribe(TransactionListPresenter$$Lambda$8.lambdaFactory$(this), TransactionListPresenter$$Lambda$9.lambdaFactory$(this)));
    }

    static /* synthetic */ Pair lambda$fetchNextTransactions$6(TransactionListPresenter this_, Pair pair) {
        Response<Transactions> response = pair.first;
        if (response.isSuccessful()) {
            this_.updateTransactionsORM(((Transactions) response.body()).getData());
        }
        return pair;
    }

    private void updateTransactionsORM(List<com.coinbase.v2.models.transactions.Data> transactions) {
        SQLiteDatabase db = this.mDbManager.openDatabase();
        try {
            db.beginTransaction();
            if (transactions != null) {
                for (com.coinbase.v2.models.transactions.Data transaction : transactions) {
                    TransactionORM.insertOrUpdate(db, this.mSelectedAccount.getId(), transaction);
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            this.mDbManager.closeDatabase();
        }
    }

    private void clearTransactionsORM() {
        SQLiteDatabase db = this.mDbManager.openDatabase();
        db.beginTransaction();
        TransactionORM.clear(db, this.mSelectedAccount.getId());
        db.setTransactionSuccessful();
        db.endTransaction();
        this.mDbManager.closeDatabase();
    }

    private void handleResponse(Response<Transactions> response) {
        if (response.isSuccessful()) {
            this.retryCount = 0;
            this.mTransactionList.addAll(((Transactions) response.body()).getData());
            if (((Transactions) response.body()).getPagination() != null) {
                this.mNextUri = ((Transactions) response.body()).getPagination().getNextUri();
            } else {
                this.mNextUri = null;
            }
            this.mScreen.notifyDataSetChanged();
        } else {
            this.mSubscription.add(Observable.timer(500, TimeUnit.MILLISECONDS).observeOn(this.mMainScheduler).subscribe(TransactionListPresenter$$Lambda$10.lambdaFactory$(this)));
            showErrorMessage();
        }
        fetchingTransactionsComplete();
    }

    static /* synthetic */ void lambda$handleResponse$9(TransactionListPresenter this_, Long t) {
        if (this_.retryCount < 3) {
            this_.retryCount++;
            this_.retryTransactions();
        }
    }

    private void handleErrorResponse(Throwable throwable) {
        if (!(throwable == null || throwable.getMessage().equalsIgnoreCase("Canceled"))) {
            showErrorMessage();
        }
        fetchingTransactionsComplete();
    }

    private void showErrorMessage() {
        if (Utils.isConnectedOrConnecting(this.mContext)) {
            this.mSnackBarWrapper.show((int) R.string.transactions_refresh_error);
        } else {
            this.mSnackBarWrapper.show((int) R.string.transactions_internet_error);
        }
    }
}
