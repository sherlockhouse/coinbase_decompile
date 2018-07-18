package com.coinbase.android.signin;

import com.coinbase.android.FragmentScope;

public class IntroPriceChartPresenterModule {
    private final IntroPriceChartScreen mScreen;

    public IntroPriceChartPresenterModule(IntroPriceChartScreen screen) {
        this.mScreen = screen;
    }

    @FragmentScope
    public IntroPriceChartScreen providesPriceChartScreen() {
        return this.mScreen;
    }
}
