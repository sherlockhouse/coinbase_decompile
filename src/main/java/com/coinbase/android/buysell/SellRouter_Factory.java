package com.coinbase.android.buysell;

import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class SellRouter_Factory implements Factory<SellRouter> {
    private final Provider<ActionBarController> controllerProvider;
    private final Provider<SplitTesting> splitTestingProvider;

    public SellRouter_Factory(Provider<ActionBarController> controllerProvider, Provider<SplitTesting> splitTestingProvider) {
        this.controllerProvider = controllerProvider;
        this.splitTestingProvider = splitTestingProvider;
    }

    public SellRouter get() {
        return provideInstance(this.controllerProvider, this.splitTestingProvider);
    }

    public static SellRouter provideInstance(Provider<ActionBarController> controllerProvider, Provider<SplitTesting> splitTestingProvider) {
        return new SellRouter((ActionBarController) controllerProvider.get(), (SplitTesting) splitTestingProvider.get());
    }

    public static SellRouter_Factory create(Provider<ActionBarController> controllerProvider, Provider<SplitTesting> splitTestingProvider) {
        return new SellRouter_Factory(controllerProvider, splitTestingProvider);
    }

    public static SellRouter newSellRouter(ActionBarController controller, SplitTesting splitTesting) {
        return new SellRouter(controller, splitTesting);
    }
}
