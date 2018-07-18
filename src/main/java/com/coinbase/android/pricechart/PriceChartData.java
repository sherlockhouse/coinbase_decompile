package com.coinbase.android.pricechart;

import com.coinbase.android.pricechart.PriceChartPresenter.Period;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.internal.models.priceCharts.Price;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.joda.money.BigMoney;
import org.joda.money.BigMoneyProvider;
import org.joda.money.Money;
import org.joda.time.DateTime;

public class PriceChartData {
    private DateTime[] mDates;
    private int mMaxPriceIndex;
    private int mMinPriceIndex;
    private List<Float> mPriceList;
    private SpotPrice mSpotPrice;

    public static class HighlightedPrice {
        private Date mDate;
        private Money mPrice;

        public HighlightedPrice(Money price, Date date) {
            this.mPrice = price;
            this.mDate = date;
        }

        public Money getPrice() {
            return this.mPrice;
        }

        public Date getDate() {
            return this.mDate;
        }
    }

    public static class SpotPrice {
        private String mChangeScope;
        private Period mPeriod;
        private BigMoney mPriceChangeDifference;
        private float mPriceChangePercentage;
        private BigMoneyProvider mSpotPrice;

        public SpotPrice(BigMoneyProvider spotPrice, BigMoney priceChangeDifference, float priceChangePercentage, String changeScope, Period period) {
            this.mSpotPrice = spotPrice;
            this.mPriceChangeDifference = priceChangeDifference;
            this.mPriceChangePercentage = priceChangePercentage;
            this.mChangeScope = changeScope;
            this.mPeriod = period;
        }

        public BigMoneyProvider getSpotPrice() {
            return this.mSpotPrice;
        }

        public BigMoney getPriceChangeDifference() {
            return this.mPriceChangeDifference;
        }

        public float getPriceChangePercentage() {
            return this.mPriceChangePercentage;
        }

        public String getChangeScope() {
            return this.mChangeScope;
        }

        public Period getPeriod() {
            return this.mPeriod;
        }
    }

    public static PriceChartData newInstance(List<Price> priceList) {
        if (priceList == null) {
            return null;
        }
        List<Float> priceFloatList = new ArrayList();
        DateTime[] dates = new DateTime[priceList.size()];
        int minIndex = -1;
        int maxIndex = -1;
        float minValue = Float.MAX_VALUE;
        float maxValue = Float.MIN_VALUE;
        for (int i = 0; i < priceList.size(); i++) {
            Price price = (Price) priceList.get((priceList.size() - i) - 1);
            if (!(price == null || price.getPrice() == null || price.getTime() == null)) {
                try {
                    float priceFloat = Float.parseFloat(price.getPrice());
                    priceFloatList.add(Float.valueOf(priceFloat));
                    if (priceFloat < minValue) {
                        minValue = priceFloat;
                        minIndex = i;
                    }
                    if (priceFloat > maxValue) {
                        maxValue = priceFloat;
                        maxIndex = i;
                    }
                    dates[i] = Utils.getDateTimeFrom(((Price) priceList.get(i)).getTime());
                } catch (NumberFormatException e) {
                }
            }
        }
        return new PriceChartData(priceFloatList, dates, minIndex, maxIndex);
    }

    private PriceChartData(List<Float> priceList, DateTime[] dates, int minPriceIndex, int maxPriceIndex) {
        this.mPriceList = priceList;
        this.mDates = dates;
        this.mMinPriceIndex = minPriceIndex;
        this.mMaxPriceIndex = maxPriceIndex;
    }

    PriceChartData() {
    }

    public List<Float> getPriceList() {
        return this.mPriceList;
    }

    public DateTime[] getDates() {
        return this.mDates;
    }

    public int getMinPriceIndex() {
        return this.mMinPriceIndex;
    }

    public int getMaxPriceIndex() {
        return this.mMaxPriceIndex;
    }

    public void setSpotPrice(SpotPrice spotPrice) {
        this.mSpotPrice = spotPrice;
    }

    public SpotPrice getSpotPrice() {
        return this.mSpotPrice;
    }
}
