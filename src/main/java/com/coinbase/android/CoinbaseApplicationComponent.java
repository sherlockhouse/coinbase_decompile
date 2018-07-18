package com.coinbase.android;

import com.coinbase.android.featureflag.FeatureFlags;
import com.coinbase.android.pin.PINManager;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.ui.AnimationUtilsWrapper;
import com.coinbase.android.ui.BaseViewModule;
import com.coinbase.android.ui.CurrenciesUpdatedConnector;

public interface CoinbaseApplicationComponent {
    AdapterSubcomponent adapterSubcomponent();

    AnimationUtilsWrapper animationUtilsWrapper();

    CoinbaseActivityMystiqueSubcomponent coinbaseActivityMystiqueSubcomponent(BaseActivityModule baseActivityModule, BaseViewModule baseViewModule, ActionBarModule actionBarModule, NonBottomNavActivityModule nonBottomNavActivityModule);

    CoinbaseActivitySubcomponent coinbaseActivitySubcomponent(BaseActivityModule baseActivityModule);

    CoinbaseServiceSubcomponent coinbaseServiceSubcomponent();

    CurrenciesUpdatedConnector currenciesUpdatedConnector();

    FeatureFlags features();

    FragmentSubcomponent fragmentSubcomponent();

    GenericActivitySubcomponent genericActivitySubcomponent();

    void inject(CoinbaseApplication coinbaseApplication);

    MainActivityPresenterSubcomponent mainActivityPresenterSubcomponent(MainPresenterModule mainPresenterModule, BaseActivityModule baseActivityModule);

    MainActivitySubcomponent mainActivitySubcomponent(BaseActivityModule baseActivityModule, BaseViewModule baseViewModule, BottomNavActivityModule bottomNavActivityModule);

    PINManager pinManager();

    SplitTesting splitTesting();

    TasksSubcomponent tasksSubcomponent();
}
