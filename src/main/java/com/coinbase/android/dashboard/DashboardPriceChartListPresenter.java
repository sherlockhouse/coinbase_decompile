package com.coinbase.android.dashboard;

import android.app.Application;
import android.content.Context;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.pricechart.PriceChartData.SpotPrice;
import com.coinbase.android.ui.CurrenciesUpdatedConnector;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.api.internal.models.currency.Data;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.joda.money.BigMoney;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class DashboardPriceChartListPresenter {
    private static final String CURRENCY_PRICE_CHANGE_FORMAT = "%1$,.2f%% (%2$s)";
    private final Context mContext;
    private final CurrenciesUpdatedConnector mCurrenciesUpdatedConnector;
    private List<Data> mCurrencyList = new ArrayList();
    private final DashboardRefreshConnector mDashboardRefreshConnector;
    private final DashboardPriceChartItemClickedConnector mItemClickedConnector;
    private final Scheduler mMainScheduler;
    private final MoneyFormatterUtil mMoneyFormatterUtil;
    private final DashboardPriceChartListScreen mScreen;
    private final SnackBarWrapper mSnackBarWrapper;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Inject
    public DashboardPriceChartListPresenter(Application application, DashboardPriceChartListScreen screen, SnackBarWrapper snackBarWrapper, CurrenciesUpdatedConnector currenciesUpdatedConnector, DashboardPriceChartItemClickedConnector itemClickedConnector, DashboardRefreshConnector dashboardRefreshConnector, MoneyFormatterUtil moneyFormatterUtil, @MainScheduler Scheduler mainScheduler) {
        this.mContext = application;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mScreen = screen;
        this.mCurrenciesUpdatedConnector = currenciesUpdatedConnector;
        this.mItemClickedConnector = itemClickedConnector;
        this.mDashboardRefreshConnector = dashboardRefreshConnector;
        this.mMoneyFormatterUtil = moneyFormatterUtil;
        this.mMainScheduler = mainScheduler;
    }

    void onResume() {
        this.mSubscription.add(this.mCurrenciesUpdatedConnector.get().observeOn(this.mMainScheduler).subscribe(DashboardPriceChartListPresenter$$Lambda$1.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$onResume$0(DashboardPriceChartListPresenter this_, List currencies) {
        if (currencies.isEmpty()) {
            this_.showError();
            return;
        }
        this_.mCurrencyList.clear();
        this_.mCurrencyList.addAll(currencies);
        this_.mScreen.notifyDataSetChanged();
    }

    void onDestroy() {
        this.mSubscription.clear();
    }

    List<Data> getCurrencyList() {
        return this.mCurrencyList;
    }

    String getFormattedSpotPrice(SpotPrice spotPrice) {
        if (spotPrice == null || spotPrice.getSpotPrice() == null) {
            return null;
        }
        return this.mMoneyFormatterUtil.formatMoney(spotPrice.getSpotPrice());
    }

    String getPriceChange(SpotPrice spotPrice) {
        if (spotPrice == null) {
            return null;
        }
        StringBuilder priceChangeStr = new StringBuilder();
        String priceChange = "";
        BigMoney priceChangeDiff = spotPrice.getPriceChangeDifference();
        if (spotPrice.getPriceChangeDifference() != null) {
            priceChange = this.mMoneyFormatterUtil.formatMoney(spotPrice.getPriceChangeDifference().toBigMoney().abs());
            if (priceChangeDiff.isPositive()) {
                priceChangeStr.append("+");
            }
        }
        priceChangeStr.append(String.format(CURRENCY_PRICE_CHANGE_FORMAT, new Object[]{Float.valueOf(spotPrice.getPriceChangePercentage()), priceChange}));
        return priceChangeStr.toString();
    }

    boolean isPriceChangePositive(SpotPrice spotPrice) {
        if (spotPrice == null || spotPrice.getPriceChangeDifference() == null) {
            return false;
        }
        return spotPrice.getPriceChangeDifference().toBigMoney().isPositiveOrZero();
    }

    void onPriceChartItemClicked(Data currency) {
        this.mItemClickedConnector.get().onNext(currency);
    }

    void onRefresh() {
        this.mDashboardRefreshConnector.get().onNext(null);
    }

    private void showError() {
        this.mSnackBarWrapper.showGenericError();
    }
}
