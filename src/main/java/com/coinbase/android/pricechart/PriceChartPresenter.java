package com.coinbase.android.pricechart;

import android.app.Application;
import android.content.Context;
import android.util.Pair;
import com.coinbase.android.BackgroundScheduler;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.Log;
import com.coinbase.android.R;
import com.coinbase.android.dashboard.SpotPriceUpdatedConnector;
import com.coinbase.android.pricechart.PriceChartData.SpotPrice;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.MoneyFormatterUtil.Options;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.ApiConstants;
import com.coinbase.api.internal.models.priceCharts.PriceChart;
import com.coinbase.v2.models.price.Data;
import com.coinbase.v2.models.price.Price;
import com.github.mikephil.charting.formatter.XAxisValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import org.joda.money.BigMoney;
import org.joda.money.BigMoneyProvider;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.DateTime;
import retrofit2.Response;
import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class PriceChartPresenter {
    private static final long SPOT_PRICE_UPDATE_PERIOD = 30000;
    private static final String TAG = PriceChartPresenter.class.getSimpleName();
    private final Scheduler mBackgroundScheduler;
    private String mBaseCurrencyCode;
    private final Context mContext;
    private CurrencyUnit mCurrency;
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private final MoneyFormatterUtil mMoneyFormatterUtil;
    private Period mPeriod;
    private final CompositeSubscription mPeriodSubscription = new CompositeSubscription();
    private final PriceChartDataUpdatedConnector mPriceChartDataUpdatedConnector;
    private final PriceChartPeriodUpdatedConnector mPriceChartPeriodUpdatedConnector;
    private final PriceChartScreen mPriceChartScreen;
    private final CompositeSubscription mPriceChartSubscription = new CompositeSubscription();
    private boolean mShouldPollSpotPrice = false;
    private final CompositeSubscription mSpotPriceTimerSubscription = new CompositeSubscription();
    private final SpotPriceUpdatedConnector mSpotPriceUpdatedConnector;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    private class DateValueFormatter implements XAxisValueFormatter {
        private DateTime[] mDates;
        private Period mPeriod;

        DateValueFormatter(Period period, DateTime[] dates) {
            this.mPeriod = period;
            this.mDates = dates;
        }

        public String getXValue(String original, int index, ViewPortHandler viewPortHandler) {
            SimpleDateFormat dateFormat = null;
            Locale current = PriceChartPresenter.this.mContext.getResources().getConfiguration().locale;
            switch (this.mPeriod) {
                case DAY:
                    dateFormat = new SimpleDateFormat("HH:mm", current);
                    break;
                case HOUR:
                    dateFormat = new SimpleDateFormat("HH:mm", current);
                    break;
                case WEEK:
                    dateFormat = new SimpleDateFormat("EEE", current);
                    break;
                case MONTH:
                    dateFormat = new SimpleDateFormat("M/d", current);
                    break;
                case YEAR:
                    dateFormat = new SimpleDateFormat("MMM", current);
                    break;
                case ALL:
                    dateFormat = new SimpleDateFormat("M/yy", current);
                    break;
            }
            return dateFormat.format(this.mDates[(this.mDates.length - 1) - index].toDate()).replace("AM", "am").replace("PM", "pm");
        }
    }

    public enum Period {
        HOUR("hour"),
        DAY("day"),
        WEEK("week"),
        MONTH("month"),
        YEAR("year"),
        ALL("all");
        
        String value;

        private Period(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    @Inject
    public PriceChartPresenter(LoginManager loginManager, Application application, PriceChartScreen screen, PriceChartDataUpdatedConnector priceChartDataUpdatedConnector, PriceChartPeriodUpdatedConnector priceChartPeriodUpdatedConnector, MoneyFormatterUtil moneyFormatterUtil, SpotPriceUpdatedConnector spotPriceUpdatedConnector, @MainScheduler Scheduler mainScheduler, @BackgroundScheduler Scheduler backgroundScheduler) {
        this.mLoginManager = loginManager;
        this.mContext = application;
        this.mPriceChartScreen = screen;
        this.mPriceChartDataUpdatedConnector = priceChartDataUpdatedConnector;
        this.mPriceChartPeriodUpdatedConnector = priceChartPeriodUpdatedConnector;
        this.mMoneyFormatterUtil = moneyFormatterUtil;
        this.mSpotPriceUpdatedConnector = spotPriceUpdatedConnector;
        this.mMainScheduler = mainScheduler;
        this.mBackgroundScheduler = backgroundScheduler;
    }

    void setCurrencyCode(String baseCurrencyCode, Period period) {
        if (baseCurrencyCode != null) {
            this.mBaseCurrencyCode = baseCurrencyCode;
            determineCurrency();
            if (period != null) {
                displayChart(period);
            }
        }
    }

    void listenForPeriodUpdate() {
        this.mShouldPollSpotPrice = true;
        setSubscription();
    }

    void onDestroy() {
        this.mPriceChartSubscription.clear();
        this.mPeriodSubscription.clear();
        this.mSpotPriceTimerSubscription.clear();
        this.mSubscription.clear();
    }

    Money getMoney(float amount) {
        return Money.of(this.mCurrency, (double) amount, RoundingMode.HALF_EVEN);
    }

    DateValueFormatter getDateFormatter(DateTime[] dates) {
        return new DateValueFormatter(this.mPeriod, dates);
    }

    String getFormattedPriceMarkerText(float amount) {
        return this.mMoneyFormatterUtil.formatMoney(getMoney(amount), EnumSet.of(Options.ROUND_2_DIGITS));
    }

    private void setSubscription() {
        this.mPeriodSubscription.clear();
        this.mSpotPriceTimerSubscription.clear();
        this.mPeriodSubscription.add(this.mPriceChartPeriodUpdatedConnector.get().onBackpressureLatest().distinctUntilChanged().observeOn(this.mMainScheduler).subscribe(PriceChartPresenter$$Lambda$1.lambdaFactory$(this)));
    }

    private void displayChart(Period period) {
        if (period != null) {
            this.mPeriod = period;
            this.mPriceChartScreen.setChartVisibility();
            determineCurrency();
            fetchPriceChartData();
            this.mPriceChartSubscription.clear();
            this.mPriceChartSubscription.add(this.mPriceChartDataUpdatedConnector.get(this.mBaseCurrencyCode, period).onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(PriceChartPresenter$$Lambda$2.lambdaFactory$(this)));
        }
    }

    static /* synthetic */ void lambda$displayChart$1(PriceChartPresenter this_, PriceChartData priceChartData) {
        this_.mPriceChartScreen.hidePriceChartProgress();
        this_.mPriceChartScreen.loadChartData(priceChartData);
        if (this_.mShouldPollSpotPrice) {
            List<Float> prices = priceChartData.getPriceList();
            if (prices != null && !prices.isEmpty()) {
                Float first = (Float) prices.get(0);
                if (first != null) {
                    this_.startPeriodicSpotPricePoll(first + "");
                }
            }
        }
    }

    private void fetchPriceChartData() {
        if (this.mCurrency == null || this.mPeriod == null || this.mBaseCurrencyCode == null) {
            handleGetPricesFailure();
            return;
        }
        HashMap<String, Object> params = new HashMap();
        params.put(ApiConstants.PERIOD, this.mPeriod.getValue());
        this.mSubscription.add(Observable.combineLatest(this.mLoginManager.getClient().getPriceChartRx(params, this.mBaseCurrencyCode, this.mCurrency.getCurrencyCode()), this.mSpotPriceUpdatedConnector.fetch(this.mBaseCurrencyCode, this.mCurrency.getCurrencyCode()), PriceChartPresenter$$Lambda$3.lambdaFactory$()).map(PriceChartPresenter$$Lambda$4.lambdaFactory$(this)).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainScheduler).first().subscribe(PriceChartPresenter$$Lambda$5.lambdaFactory$(this), PriceChartPresenter$$Lambda$6.lambdaFactory$(this)));
    }

    static /* synthetic */ Response lambda$fetchPriceChartData$3(PriceChartPresenter this_, Pair response) {
        Response<PriceChart> priceChartResponse = response.first.first;
        if (!priceChartResponse.isSuccessful()) {
            return priceChartResponse;
        }
        Response<Price> spotPriceResponse = response.second.first;
        if (!spotPriceResponse.isSuccessful()) {
            return spotPriceResponse;
        }
        List<com.coinbase.api.internal.models.priceCharts.Price> prices = ((PriceChart) priceChartResponse.body()).getData().getPrices();
        PriceChartData priceChartData = this_.buildPriceChartData(prices);
        if (priceChartData == null) {
            return Response.success(null);
        }
        SpotPrice spotPrice = null;
        if (!(prices == null || prices.isEmpty())) {
            spotPrice = this_.getSpotPrice(((Price) spotPriceResponse.body()).getData(), ((com.coinbase.api.internal.models.priceCharts.Price) prices.get(prices.size() - 1)).getPrice());
        }
        if (spotPrice == null) {
            return Response.success(null);
        }
        priceChartData.setSpotPrice(spotPrice);
        return Response.success(priceChartData);
    }

    static /* synthetic */ void lambda$fetchPriceChartData$4(PriceChartPresenter this_, Response response) {
        if (response.isSuccessful() && response.body() != null && (response.body() instanceof PriceChartData)) {
            this_.mPriceChartScreen.hidePriceChartProgress();
            this_.mPriceChartDataUpdatedConnector.get(this_.mBaseCurrencyCode, this_.mPeriod).onNext((PriceChartData) response.body());
            return;
        }
        this_.handleGetPricesFailure();
    }

    private void handleGetPricesFailure() {
        this.mPriceChartScreen.hidePriceChartProgress();
        this.mPriceChartScreen.handleFailureError();
    }

    private void determineCurrency() {
        this.mCurrency = this.mLoginManager.getCurrencyUnit();
        this.mPriceChartScreen.setCurrency(this.mCurrency);
    }

    List<com.coinbase.api.internal.models.priceCharts.Price> getSampledPriceList(List<com.coinbase.api.internal.models.priceCharts.Price> prices) {
        if (prices == null) {
            return null;
        }
        List<com.coinbase.api.internal.models.priceCharts.Price> updatedPriceList = new ArrayList();
        switch (this.mPeriod) {
            case DAY:
                try {
                    updatedPriceList.addAll(prices.subList(1, prices.size()));
                    return updatedPriceList;
                } catch (NullPointerException e) {
                    Log.e(TAG, "Stopped race condition on activity", e);
                    return updatedPriceList;
                }
            case HOUR:
                updatedPriceList.addAll(sample(prices, 60));
                return updatedPriceList;
            default:
                updatedPriceList.addAll(prices);
                return updatedPriceList;
        }
    }

    PriceChartData buildPriceChartData(List<com.coinbase.api.internal.models.priceCharts.Price> priceList) {
        if (priceList == null) {
            return null;
        }
        List<com.coinbase.api.internal.models.priceCharts.Price> updatedPriceList = getSampledPriceList(priceList);
        if (updatedPriceList != null) {
            return PriceChartData.newInstance(updatedPriceList);
        }
        return null;
    }

    List<com.coinbase.api.internal.models.priceCharts.Price> sample(List<com.coinbase.api.internal.models.priceCharts.Price> data, int resultSize) {
        int dataSize = data.size();
        int stride = dataSize / resultSize;
        if (stride < 1) {
            stride = 1;
        }
        List<com.coinbase.api.internal.models.priceCharts.Price> result = new ArrayList();
        for (int i = 0; i < dataSize; i += stride) {
            result.add(data.get(i));
        }
        if (!result.isEmpty()) {
            com.coinbase.api.internal.models.priceCharts.Price first = (com.coinbase.api.internal.models.priceCharts.Price) data.get(data.size() - 1);
            if (!((com.coinbase.api.internal.models.priceCharts.Price) result.get(result.size() - 1)).equals(first)) {
                result.add(first);
            }
        }
        return result;
    }

    SpotPrice getSpotPrice(Data spotPriceData, String firstPriceStr) {
        if (spotPriceData == null || firstPriceStr == null) {
            return null;
        }
        try {
            BigMoneyProvider spotPriceMoney = getSpotPriceMoney(spotPriceData);
            String changeScope = getSpotPriceChangeScopeText();
            BigMoney firstPrice = this.mMoneyFormatterUtil.moneyFromValue(this.mCurrency.getCurrencyCode(), firstPriceStr).toBigMoney();
            BigMoney priceChangeDifference = getPriceChangeDifference(spotPriceMoney, firstPrice);
            return new SpotPrice(spotPriceMoney, priceChangeDifference, getPriceChangePercentage(priceChangeDifference, firstPrice), changeScope, this.mPeriod);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    BigMoneyProvider getSpotPriceMoney(Data spotPriceData) {
        return this.mMoneyFormatterUtil.moneyFromValue(spotPriceData.getCurrency(), spotPriceData.getAmount());
    }

    BigMoney getPriceChangeDifference(BigMoneyProvider spotPriceMoney, BigMoney firstPrice) {
        if (spotPriceMoney == null || firstPrice == null) {
            return null;
        }
        return spotPriceMoney.toBigMoney().minus(firstPrice);
    }

    float getPriceChangePercentage(BigMoney priceDifference, BigMoney firstPrice) {
        if (priceDifference == null || firstPrice == null) {
            return 0.0f;
        }
        return (priceDifference.getAmount().floatValue() / firstPrice.getAmount().floatValue()) * 100.0f;
    }

    String getSpotPriceChangeScopeText() {
        if (this.mContext == null) {
            return null;
        }
        switch (this.mPeriod) {
            case DAY:
                return this.mContext.getString(R.string.change_day_label);
            case HOUR:
                return this.mContext.getString(R.string.change_hour_label);
            case WEEK:
                return this.mContext.getString(R.string.change_week_label);
            case MONTH:
                return this.mContext.getString(R.string.change_month_label);
            case YEAR:
                return this.mContext.getString(R.string.change_year_label);
            case ALL:
                return this.mContext.getString(R.string.change_all_label);
            default:
                return null;
        }
    }

    CurrencyUnit getCurrency() {
        return this.mCurrency;
    }

    String getBaseCurrencyCode() {
        return this.mBaseCurrencyCode;
    }

    Period getPeriod() {
        return this.mPeriod;
    }

    private void startPeriodicSpotPricePoll(String firstPriceStr) {
        this.mSpotPriceTimerSubscription.clear();
        this.mSpotPriceTimerSubscription.add(getPeriodicSpotPriceSubscription(firstPriceStr));
    }

    private Subscription getPeriodicSpotPriceSubscription(String firstPriceStr) {
        return Observable.timer(SPOT_PRICE_UPDATE_PERIOD, TimeUnit.MILLISECONDS).subscribe(PriceChartPresenter$$Lambda$7.lambdaFactory$(this, firstPriceStr), PriceChartPresenter$$Lambda$8.lambdaFactory$());
    }

    static /* synthetic */ void lambda$getPeriodicSpotPriceSubscription$8(PriceChartPresenter this_, String firstPriceStr, Long v) {
        this_.mSpotPriceTimerSubscription.clear();
        this_.mSpotPriceTimerSubscription.add(this_.mSpotPriceUpdatedConnector.fetch(this_.mBaseCurrencyCode, this_.mCurrency.getCurrencyCode()).observeOn(this_.mMainScheduler).subscribe(PriceChartPresenter$$Lambda$9.lambdaFactory$(this_, firstPriceStr), PriceChartPresenter$$Lambda$10.lambdaFactory$(this_, firstPriceStr)));
    }

    static /* synthetic */ void lambda$null$6(PriceChartPresenter this_, String firstPriceStr, Pair pair) {
        this_.mSpotPriceTimerSubscription.clear();
        this_.mSpotPriceTimerSubscription.add(this_.getPeriodicSpotPriceSubscription(firstPriceStr));
        this_.mPriceChartScreen.updateSpotPrice(this_.getSpotPrice(((Price) ((Response) pair.first).body()).getData(), firstPriceStr));
    }

    static /* synthetic */ void lambda$null$7(PriceChartPresenter this_, String firstPriceStr, Throwable t) {
        android.util.Log.i("Coinbase", "Error", t);
        this_.mSpotPriceTimerSubscription.clear();
        this_.mSpotPriceTimerSubscription.add(this_.getPeriodicSpotPriceSubscription(firstPriceStr));
    }

    static /* synthetic */ void lambda$getPeriodicSpotPriceSubscription$9(Throwable t) {
    }
}
