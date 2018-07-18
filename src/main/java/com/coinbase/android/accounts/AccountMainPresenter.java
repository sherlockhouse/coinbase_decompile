package com.coinbase.android.accounts;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.CurrenciesUpdatedConnector;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.internal.models.currency.Data;
import javax.inject.Inject;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class AccountMainPresenter {
    private final AccountItemClickedConnector mAccountItemClickedConnector;
    private final CurrenciesUpdatedConnector mCurrenciesUpdatedConnector;
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private final AccountMainScreen mScreen;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Inject
    public AccountMainPresenter(AccountMainScreen screen, AccountItemClickedConnector accountItemClickedConnector, CurrenciesUpdatedConnector currenciesUpdatedConnector, MixpanelTracking mixpanelTracking, @MainScheduler Scheduler mainScheduler) {
        this.mScreen = screen;
        this.mAccountItemClickedConnector = accountItemClickedConnector;
        this.mCurrenciesUpdatedConnector = currenciesUpdatedConnector;
        this.mMixpanelTracking = mixpanelTracking;
        this.mMainScheduler = mainScheduler;
    }

    public void onResume() {
        this.mSubscription.add(this.mAccountItemClickedConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(AccountMainPresenter$$Lambda$1.lambdaFactory$(this)));
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_ACCOUNTS_VIEWED, new String[0]);
    }

    void onDestroy() {
        this.mSubscription.clear();
    }

    Data getCurrency(com.coinbase.v2.models.account.Data account) {
        if (account == null) {
            return null;
        }
        return this.mCurrenciesUpdatedConnector.getCurrencyByCode(account.getCurrency().getCode());
    }
}
