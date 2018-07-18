package com.coinbase.android.pricechart;

import com.coinbase.android.pricechart.PriceChartData.SpotPrice;
import org.joda.money.CurrencyUnit;

public interface PriceChartScreen {
    void handleFailureError();

    void hidePriceChartProgress();

    void loadChartData(PriceChartData priceChartData);

    void setChartVisibility();

    void setCurrency(CurrencyUnit currencyUnit);

    void updateSpotPrice(SpotPrice spotPrice);
}
