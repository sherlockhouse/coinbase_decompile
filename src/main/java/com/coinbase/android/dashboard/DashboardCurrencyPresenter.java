package com.coinbase.android.dashboard;

import android.app.Application;
import android.content.Context;
import com.coinbase.android.BackgroundScheduler;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.accounts.AccountItemClickedConnector;
import com.coinbase.android.buysell.BuyRouter;
import com.coinbase.android.buysell.BuyRouter.BuySource;
import com.coinbase.android.buysell.SellRouter;
import com.coinbase.android.buysell.SellRouter.SellSource;
import com.coinbase.android.pricechart.PriceChartData.HighlightedPrice;
import com.coinbase.android.pricechart.PriceChartData.SpotPrice;
import com.coinbase.android.pricechart.PriceChartPeriodUpdatedConnector;
import com.coinbase.android.pricechart.PriceChartPresenter.Period;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.MoneyFormatterUtil.Options;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.internal.models.currency.Data;
import java.text.DateFormat;
import java.util.Date;
import java.util.EnumSet;
import javax.inject.Inject;
import org.joda.money.BigMoney;
import org.joda.money.BigMoneyProvider;
import org.joda.money.CurrencyUnit;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class DashboardCurrencyPresenter {
    static final String CRYPTOCURRENCY_DATA = "cryptocurrency_data";
    private static final String CURRENCY_PRICE_CHANGE_FORMAT = "%1$,.2f%% (%2$s) %3$s";
    private final AccountItemClickedConnector mAccountItemClickedConnector;
    private final Scheduler mBackgroundScheduler;
    private final BuyRouter mBuyRouter;
    private final Context mContext;
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private final MoneyFormatterUtil mMoneyFormatterUtil;
    private Period mPeriod = Period.DAY;
    private final PriceChartPeriodUpdatedConnector mPriceChartPeriodUpdatedConnector;
    private final DashboardCurrencyScreen mScreen;
    private Data mSelectedCurrency;
    private final SellRouter mSellRouter;
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    private final DashboardTabPeriodSelectionConnector mTabPeriodSelectionConnector;

    @Inject
    public DashboardCurrencyPresenter(Application application, DashboardCurrencyScreen screen, DashboardTabPeriodSelectionConnector tabPeriodSelectionConnector, PriceChartPeriodUpdatedConnector priceChartPeriodUpdatedConnector, AccountItemClickedConnector accountItemClickedConnector, BuyRouter buyRouter, SellRouter sellRouter, MixpanelTracking mixpanelTracking, MoneyFormatterUtil moneyFormatterUtil, @MainScheduler Scheduler mainScheduler, @BackgroundScheduler Scheduler backgroundScheduler) {
        this.mContext = application;
        this.mScreen = screen;
        this.mTabPeriodSelectionConnector = tabPeriodSelectionConnector;
        this.mPriceChartPeriodUpdatedConnector = priceChartPeriodUpdatedConnector;
        this.mAccountItemClickedConnector = accountItemClickedConnector;
        this.mBuyRouter = buyRouter;
        this.mSellRouter = sellRouter;
        this.mMixpanelTracking = mixpanelTracking;
        this.mMoneyFormatterUtil = moneyFormatterUtil;
        this.mMainScheduler = mainScheduler;
        this.mBackgroundScheduler = backgroundScheduler;
    }

    void onResume() {
        this.mSubscription.add(this.mTabPeriodSelectionConnector.get().onBackpressureLatest().distinctUntilChanged().observeOn(this.mBackgroundScheduler).subscribe(DashboardCurrencyPresenter$$Lambda$1.lambdaFactory$(this)));
        this.mSubscription.add(this.mAccountItemClickedConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(DashboardCurrencyPresenter$$Lambda$2.lambdaFactory$(this)));
        updatePriceChart();
        if (this.mSelectedCurrency != null) {
            this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_CHART_VIEWED, "currency", this.mSelectedCurrency.getCode());
        }
    }

    static /* synthetic */ void lambda$onResume$0(DashboardCurrencyPresenter this_, TabPeriod tabPeriod) {
        this_.mPeriod = tabPeriod.getPriceChartPeriod();
        this_.updatePriceChart();
    }

    void onDestroy() {
        this.mSubscription.clear();
    }

    void onBuyButtonClicked() {
        if (this.mSelectedCurrency != null) {
            this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_CHART_TAPPED_BUY_BUTTON, "currency", this.mSelectedCurrency.getCode());
        }
        this.mBuyRouter.routeToBuyModal(this.mSelectedCurrency.getCode(), BuySource.CHARTS);
    }

    void onSellButtonClicked() {
        if (this.mSelectedCurrency != null) {
            this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_CHART_TAPPED_SELL_BUTTON, "currency", this.mSelectedCurrency.getCode());
        }
        this.mSellRouter.routeToSellModal(this.mSelectedCurrency.getCode(), SellSource.CHARTS);
    }

    void onPriceHighlighted(HighlightedPrice highlightedPrice) {
        if (highlightedPrice != null) {
            this.mScreen.setSelectedPrice(getFormattedPrice(highlightedPrice.getPrice()), getFormattedDate(highlightedPrice.getDate()));
        }
    }

    void onSpotPriceUpdated(SpotPrice spotPrice) {
        if (spotPrice != null) {
            if (spotPrice.getPeriod() == Period.ALL) {
                this.mScreen.setSpotPrice(getFormattedPrice(spotPrice.getSpotPrice()), spotPrice.getChangeScope(), R.color.currency_price_highlight);
            } else {
                this.mScreen.setSpotPrice(getFormattedPrice(spotPrice.getSpotPrice()), getFormattedPriceChange(spotPrice), getPriceChangeTextColor(spotPrice));
            }
        }
    }

    void onPriceChartMotionEvent(Boolean isTouchDown) {
        this.mScreen.showCurrentPrice(isTouchDown.booleanValue());
    }

    void onCurrencyUpdated(CurrencyUnit currencyUnit) {
        this.mScreen.setCurrencyUnit(currencyUnit);
    }

    void setScrollingEnabled(Boolean isEnabled) {
        this.mScreen.setScrollingEnabled(isEnabled);
    }

    Period getPeriod() {
        return this.mPeriod;
    }

    void setSelectedCurrency(Data currency) {
        this.mSelectedCurrency = currency;
    }

    private void updatePriceChart() {
        if (this.mPeriod != null) {
            this.mPriceChartPeriodUpdatedConnector.get().onNext(this.mPeriod);
        }
    }

    String getFormattedPrice(BigMoneyProvider price) {
        if (price == null) {
            return null;
        }
        return this.mMoneyFormatterUtil.formatMoney(price, EnumSet.of(Options.EXCLUDE_CURRENCY_SYMBOL));
    }

    String getFormattedDate(Date date) {
        if (date == null) {
            return null;
        }
        DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(this.mContext);
        return android.text.format.DateFormat.getDateFormat(this.mContext).format(date) + " " + timeFormat.format(date);
    }

    String getFormattedPriceChange(SpotPrice spotPrice) {
        if (spotPrice == null) {
            return null;
        }
        StringBuilder priceChangeStr = new StringBuilder();
        String priceChange = "";
        BigMoney priceChangeDiff = spotPrice.getPriceChangeDifference();
        if (spotPrice.getPriceChangeDifference() != null) {
            priceChange = this.mMoneyFormatterUtil.formatMoney(priceChangeDiff.toBigMoney().abs());
            if (priceChangeDiff.isPositive()) {
                priceChangeStr.append("+");
            }
        }
        priceChangeStr.append(String.format(CURRENCY_PRICE_CHANGE_FORMAT, new Object[]{Float.valueOf(spotPrice.getPriceChangePercentage()), priceChange, spotPrice.getChangeScope()}));
        return priceChangeStr.toString();
    }

    int getPriceChangeTextColor(SpotPrice spotPrice) {
        int textColorRes = R.color.dashboard_currency_price_perc_negative_text;
        if (spotPrice == null) {
            return R.color.dashboard_currency_price_perc_negative_text;
        }
        if (spotPrice.getPriceChangeDifference() != null && spotPrice.getPriceChangeDifference().toBigMoney().isPositiveOrZero()) {
            textColorRes = R.color.dashboard_currency_price_perc_positive_text;
        }
        return textColorRes;
    }
}
