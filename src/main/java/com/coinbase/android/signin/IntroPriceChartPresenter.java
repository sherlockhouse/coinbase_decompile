package com.coinbase.android.signin;

import android.app.Application;
import android.content.Context;
import com.coinbase.android.FragmentScope;
import com.coinbase.android.pricechart.PriceChartData.HighlightedPrice;
import com.coinbase.android.pricechart.PriceChartData.SpotPrice;
import com.coinbase.android.pricechart.PriceChartPeriodUpdatedConnector;
import com.coinbase.android.pricechart.PriceChartPresenter.Period;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.MoneyFormatterUtil.Options;
import com.coinbase.android.utils.MoneyUtils.Currency;
import com.coinbase.android.utils.Utils;
import java.text.DateFormat;
import java.util.Date;
import java.util.EnumSet;
import javax.inject.Inject;
import org.joda.money.Money;

@FragmentScope
public class IntroPriceChartPresenter {
    private final Context mContext;
    private final MoneyFormatterUtil mMoneyFormatterUtil;
    private Period mPeriod = Period.DAY;
    private final PriceChartPeriodUpdatedConnector mPriceChartPeriodUpdatedConnector;
    private final IntroPriceChartScreen mScreen;

    @Inject
    IntroPriceChartPresenter(IntroPriceChartScreen screen, Application app, PriceChartPeriodUpdatedConnector priceChartPeriodUpdatedConnector, MoneyFormatterUtil moneyFormatterUtil) {
        this.mScreen = screen;
        this.mContext = app;
        this.mPriceChartPeriodUpdatedConnector = priceChartPeriodUpdatedConnector;
        this.mMoneyFormatterUtil = moneyFormatterUtil;
    }

    public void onViewCreated() {
        setPeriod(Period.DAY);
    }

    void onResume() {
        determineBaseCurrency();
    }

    void updateBaseCurrency(Currency currency) {
        Utils.setSelectedCurrencyType(this.mContext, currency);
        this.mScreen.updateBaseCurrencyViews(currency);
    }

    void onPriceChartMotionEvent(boolean isTouchDown) {
        this.mScreen.showCurrentPrice(isTouchDown);
    }

    void onPriceHighlighted(HighlightedPrice highlightedPrice) {
        if (highlightedPrice != null) {
            Money price = highlightedPrice.getPrice();
            String priceString = this.mMoneyFormatterUtil.formatMoney(price, EnumSet.of(Options.EXCLUDE_CURRENCY_SYMBOL));
            String currencySymbol = this.mMoneyFormatterUtil.getCurrencySymbol(price);
            DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(this.mContext);
            DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(this.mContext);
            Date date = highlightedPrice.getDate();
            this.mScreen.setSelectedPrice(priceString, currencySymbol, dateFormat.format(date) + " " + timeFormat.format(date));
        }
    }

    void onSpotPriceUpdated(SpotPrice spotPrice) {
        if (spotPrice != null) {
            this.mScreen.setSpotPrice(this.mMoneyFormatterUtil.formatMoney(spotPrice.getSpotPrice(), EnumSet.of(Options.EXCLUDE_CURRENCY_SYMBOL)), this.mMoneyFormatterUtil.getCurrencySymbol(spotPrice.getSpotPrice()), this.mMoneyFormatterUtil.formatMoney(spotPrice.getPriceChangeDifference().toBigMoney().abs(), EnumSet.of(Options.EXCLUDE_CURRENCY_SYMBOL)), spotPrice.getPriceChangeDifference().toBigMoney().isPositiveOrZero(), spotPrice.getChangeScope());
        }
    }

    void setPeriod(Period period) {
        this.mPeriod = period;
        this.mPriceChartPeriodUpdatedConnector.get().onNext(period);
    }

    Period getPeriod() {
        return this.mPeriod;
    }

    private void determineBaseCurrency() {
        this.mScreen.updateBaseCurrencyViews(Utils.lastSelectedCurrencyType(this.mContext));
    }
}
