package com.coinbase.android.ui;

import com.coinbase.android.BackgroundScheduler;
import com.coinbase.android.ControllerScope;
import com.coinbase.api.internal.models.currency.Data;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class CurrencyTabPresenter {
    private final Scheduler mBackgroundScheduler;
    private final CurrenciesUpdatedConnector mCurrenciesUpdatedConnector;
    private CurrencyTabFilter mCurrencyTabFilter;
    private final Scheduler mMainScheduler;
    private final CurrencyTabScreen mScreen;
    private final SnackBarWrapper mSnackBarWrapper;
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    private final CurrencyTabSelectorConnector mTabSelectorConnector;

    @Inject
    public CurrencyTabPresenter(CurrencyTabScreen screen, SnackBarWrapper snackBarWrapper, CurrencyTabSelectorConnector tabSelectorConnector, CurrenciesUpdatedConnector currenciesUpdatedConnector, @MainScheduler Scheduler mainScheduler, @BackgroundScheduler Scheduler backgroundScheduler) {
        this.mScreen = screen;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mTabSelectorConnector = tabSelectorConnector;
        this.mCurrenciesUpdatedConnector = currenciesUpdatedConnector;
        this.mMainScheduler = mainScheduler;
        this.mBackgroundScheduler = backgroundScheduler;
    }

    void init(CurrencyTabFilter currencyFilter) {
        Observable<Void> currencyTabObservable;
        if (this.mCurrencyTabFilter == null) {
            this.mCurrencyTabFilter = currencyFilter;
        }
        if (this.mCurrencyTabFilter.filterUpdated() == null) {
            currencyTabObservable = Observable.just(null);
        } else {
            currencyTabObservable = this.mCurrencyTabFilter.filterUpdated();
        }
        this.mSubscription.clear();
        this.mSubscription.add(Observable.combineLatest(currencyTabObservable, this.mCurrenciesUpdatedConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler).map(CurrencyTabPresenter$$Lambda$1.lambdaFactory$(this)), CurrencyTabPresenter$$Lambda$2.lambdaFactory$()).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainScheduler).subscribe(CurrencyTabPresenter$$Lambda$3.lambdaFactory$(this)));
    }

    static /* synthetic */ List lambda$init$1(Void event, List currencies) {
        return currencies;
    }

    private void showCurrencies(List<Data> currencies) {
        if (currencies.isEmpty()) {
            showError();
        } else {
            this.mScreen.populateTabLayout(currencies);
        }
    }

    void onShow() {
        init(this.mCurrencyTabFilter);
    }

    void onHide() {
        this.mSubscription.clear();
    }

    Data getLastSelectedCurrency() {
        return (Data) this.mTabSelectorConnector.get().getValue();
    }

    void onTabSelected(Data currency) {
        if (currency != null) {
            this.mTabSelectorConnector.get().onNext(currency);
        }
    }

    private void showError() {
        this.mSnackBarWrapper.showGenericError();
    }
}
