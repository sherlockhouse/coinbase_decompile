package com.coinbase.android.accounts;

import com.coinbase.android.BackgroundScheduler;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.settings.AccountsUpdatedConnector;
import com.coinbase.android.splittesting.FlavorSplitTestConstants;
import com.coinbase.android.splittesting.SplitTestConstants;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.task.SyncAccountsTask;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.RefreshRequestedConnector;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.v2.models.account.Data;
import com.coinbase.v2.models.account.Data.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class AccountListPresenter {
    private final List<Type> ACCOUNT_TYPE_SORT_ORDER = Arrays.asList(new Type[]{Type.WALLET, Type.VAULT, Type.MULTISIG_VAULT, Type.FORK_MULTISIG_VAULT, Type.MULTISIG, Type.FIAT, Type.UNKNOWN});
    private final List<Type> ACCOUNT_TYPE_SORT_ORDER_WBL = Arrays.asList(new Type[]{Type.FIAT, Type.WALLET, Type.VAULT, Type.MULTISIG_VAULT, Type.FORK_MULTISIG_VAULT, Type.MULTISIG, Type.UNKNOWN});
    private final AccountListConnector mAccountListConnector;
    private final AccountsUpdatedConnector mAccountsUpdatedConnector;
    private final Scheduler mBackgroundScheduler;
    private final AccountItemClickedConnector mItemClickedConnector;
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private final RefreshRequestedConnector mRefreshRequestedConnector;
    protected final AccountListScreen mScreen;
    protected List<Data> mSortedAccountList = new ArrayList();
    private final SplitTesting mSplitTesting;
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    private final SyncAccountsTask mSyncAccountTask;

    @Inject
    public AccountListPresenter(LoginManager loginManager, AccountListScreen screen, SyncAccountsTask syncAccountsTask, AccountItemClickedConnector itemClickedConnector, RefreshRequestedConnector refreshRequestedConnector, AccountsUpdatedConnector accountsUpdatedConnector, AccountListConnector accountListConnector, MixpanelTracking mixpanelTracking, SplitTesting splitTesting, @MainScheduler Scheduler mainScheduler, @BackgroundScheduler Scheduler backgroundScheduler) {
        this.mLoginManager = loginManager;
        this.mScreen = screen;
        this.mSyncAccountTask = syncAccountsTask;
        this.mItemClickedConnector = itemClickedConnector;
        this.mRefreshRequestedConnector = refreshRequestedConnector;
        this.mAccountsUpdatedConnector = accountsUpdatedConnector;
        this.mAccountListConnector = accountListConnector;
        this.mMixpanelTracking = mixpanelTracking;
        this.mSplitTesting = splitTesting;
        this.mMainScheduler = mainScheduler;
        this.mBackgroundScheduler = backgroundScheduler;
    }

    void onResume() {
        refreshAccountList();
        this.mSubscription.add(this.mRefreshRequestedConnector.get().onBackpressureLatest().observeOn(this.mBackgroundScheduler).subscribe(AccountListPresenter$$Lambda$1.lambdaFactory$(this)));
        this.mSubscription.add(this.mAccountsUpdatedConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(AccountListPresenter$$Lambda$2.lambdaFactory$(this)));
        if (this.mAccountListConnector != null) {
            this.mSubscription.add(this.mAccountListConnector.get().onBackpressureLatest().distinctUntilChanged().observeOn(this.mMainScheduler).subscribe(AccountListPresenter$$Lambda$3.lambdaFactory$(this)));
        }
    }

    void onDestroy() {
        this.mSubscription.clear();
    }

    List<Data> getSortedAccountList() {
        return this.mSortedAccountList;
    }

    void onAccountItemClicked(Data account) {
        if (!(account == null || account.getCurrency() == null || account.getCurrency().getCode() == null)) {
            this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_ACCOUNTS_TAPPED_ACCOUNT, account.getCurrency().getCode());
        }
        this.mItemClickedConnector.get().onNext(account);
    }

    void refreshAccountList() {
        this.mSyncAccountTask.syncAccounts();
    }

    protected List<Data> getAccountList() {
        return this.mLoginManager.getAccounts();
    }

    protected void updateAccountList() {
        List<Data> accountList = getAccountList();
        sortAccountList(accountList);
        this.mAccountListConnector.get().onNext(accountList);
    }

    private void setAccountList(List<Data> accountList) {
        this.mSortedAccountList.clear();
        this.mSortedAccountList.addAll(accountList);
        this.mScreen.notifyAccountListAdapterChanged();
    }

    protected void sortAccountList(List<Data> accountList) {
        if (accountList != null) {
            boolean isInWblExperiment = this.mSplitTesting.isInGroup(SplitTestConstants.WBL_SPLIT_TEST, FlavorSplitTestConstants.WBL_ENABLED) || this.mSplitTesting.isInGroup(SplitTestConstants.WBL_EXISTING_USER_SPLIT_TEST, FlavorSplitTestConstants.WBL_ENABLED);
            Collections.sort(accountList, AccountListPresenter$$Lambda$4.lambdaFactory$(isInWblExperiment ? this.ACCOUNT_TYPE_SORT_ORDER_WBL : this.ACCOUNT_TYPE_SORT_ORDER));
        }
    }

    static /* synthetic */ int lambda$sortAccountList$3(List sortOrder, Data lhs, Data rhs) {
        if (lhs.getCurrency() != null && lhs.getCurrency().getName() != null && rhs.getCurrency() != null && rhs.getCurrency().getName() != null && !lhs.getCurrency().getName().equals(rhs.getCurrency().getName()) && lhs.getType() != Type.FIAT && rhs.getType() != Type.FIAT) {
            return lhs.getCurrency().getName().compareTo(rhs.getCurrency().getName());
        }
        if (lhs.getType() != rhs.getType()) {
            return Integer.valueOf(sortOrder.indexOf(lhs.getType())).compareTo(Integer.valueOf(sortOrder.indexOf(rhs.getType())));
        }
        return lhs.getName().toLowerCase().compareTo(rhs.getName().toLowerCase());
    }
}
