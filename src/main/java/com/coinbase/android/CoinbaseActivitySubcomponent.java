package com.coinbase.android;

import com.coinbase.android.pin.PINPromptActivity;
import com.coinbase.android.signin.IntroPriceChartFragmentSubcomponent;
import com.coinbase.android.signin.IntroPriceChartPresenterModule;

@ActivityScope
public interface CoinbaseActivitySubcomponent {
    void inject(CoinbaseActivity coinbaseActivity);

    void inject(PINPromptActivity pINPromptActivity);

    IntroPriceChartFragmentSubcomponent priceChartFragmentSubcomponent(IntroPriceChartPresenterModule introPriceChartPresenterModule);
}
