package com.coinbase.android.pricechart;

import com.coinbase.android.ControllerScope;

public class PriceChartPresenterModule {
    private final PriceChartScreen mScreen;

    public PriceChartPresenterModule(PriceChartScreen screen) {
        this.mScreen = screen;
    }

    @ControllerScope
    public PriceChartScreen providesPriceChartLayoutScreen() {
        return this.mScreen;
    }
}
