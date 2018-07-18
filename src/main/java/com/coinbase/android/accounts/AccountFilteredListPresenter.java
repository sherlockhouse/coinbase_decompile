package com.coinbase.android.accounts;

import com.coinbase.android.BackgroundScheduler;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.settings.AccountsUpdatedConnector;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.task.SyncAccountsTask;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.RefreshRequestedConnector;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.v2.models.account.Data;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.Scheduler;

@ControllerScope
public class AccountFilteredListPresenter extends AccountListPresenter {
    private String mCurrencyCode = null;

    @Inject
    public AccountFilteredListPresenter(LoginManager loginManager, AccountListScreen screen, SyncAccountsTask syncAccountsTask, AccountItemClickedConnector itemClickedConnector, RefreshRequestedConnector refreshRequestedConnector, AccountsUpdatedConnector accountsUpdatedConnector, MixpanelTracking mixpanelTracking, SplitTesting splitTesting, @MainScheduler Scheduler mainScheduler, @BackgroundScheduler Scheduler backgroundScheduler) {
        super(loginManager, screen, syncAccountsTask, itemClickedConnector, refreshRequestedConnector, accountsUpdatedConnector, null, mixpanelTracking, splitTesting, mainScheduler, backgroundScheduler);
    }

    void setCurrencyCode(String currencyCode) {
        this.mCurrencyCode = currencyCode;
        updateAccountList();
    }

    protected void updateAccountList() {
        List<Data> filteredAccountList = filterAccountList(getAccountList(), this.mCurrencyCode);
        sortAccountList(filteredAccountList);
        this.mSortedAccountList.clear();
        this.mSortedAccountList.addAll(filteredAccountList);
        this.mScreen.notifyAccountListAdapterChanged();
    }

    List<Data> filterAccountList(List<Data> accountList, String currencyCode) {
        if (currencyCode == null) {
            return accountList;
        }
        List<Data> filteredList = new ArrayList();
        for (Data account : accountList) {
            if (!(account.getCurrency() == null || account.getCurrency().getCode() == null || !account.getCurrency().getCode().toLowerCase().equals(currencyCode.toLowerCase()))) {
                filteredList.add(account);
            }
        }
        return filteredList;
    }
}
