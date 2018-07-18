package com.coinbase.android.buysell;

import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class BuyRouter_Factory implements Factory<BuyRouter> {
    private final Provider<Buy3dsVerificationConnector> buy3dsVerificationConnectorProvider;
    private final Provider<ActionBarController> controllerProvider;
    private final Provider<SplitTesting> splitTestingProvider;

    public BuyRouter_Factory(Provider<ActionBarController> controllerProvider, Provider<SplitTesting> splitTestingProvider, Provider<Buy3dsVerificationConnector> buy3dsVerificationConnectorProvider) {
        this.controllerProvider = controllerProvider;
        this.splitTestingProvider = splitTestingProvider;
        this.buy3dsVerificationConnectorProvider = buy3dsVerificationConnectorProvider;
    }

    public BuyRouter get() {
        return provideInstance(this.controllerProvider, this.splitTestingProvider, this.buy3dsVerificationConnectorProvider);
    }

    public static BuyRouter provideInstance(Provider<ActionBarController> controllerProvider, Provider<SplitTesting> splitTestingProvider, Provider<Buy3dsVerificationConnector> buy3dsVerificationConnectorProvider) {
        return new BuyRouter((ActionBarController) controllerProvider.get(), (SplitTesting) splitTestingProvider.get(), (Buy3dsVerificationConnector) buy3dsVerificationConnectorProvider.get());
    }

    public static BuyRouter_Factory create(Provider<ActionBarController> controllerProvider, Provider<SplitTesting> splitTestingProvider, Provider<Buy3dsVerificationConnector> buy3dsVerificationConnectorProvider) {
        return new BuyRouter_Factory(controllerProvider, splitTestingProvider, buy3dsVerificationConnectorProvider);
    }

    public static BuyRouter newBuyRouter(ActionBarController controller, SplitTesting splitTesting, Buy3dsVerificationConnector buy3dsVerificationConnector) {
        return new BuyRouter(controller, splitTesting, buy3dsVerificationConnector);
    }
}
