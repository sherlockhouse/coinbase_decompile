package com.coinbase.android.deposits.fiat;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class DepositFiatRouter_Factory implements Factory<DepositFiatRouter> {
    private final Provider<ActionBarController> controllerProvider;

    public DepositFiatRouter_Factory(Provider<ActionBarController> controllerProvider) {
        this.controllerProvider = controllerProvider;
    }

    public DepositFiatRouter get() {
        return provideInstance(this.controllerProvider);
    }

    public static DepositFiatRouter provideInstance(Provider<ActionBarController> controllerProvider) {
        return new DepositFiatRouter((ActionBarController) controllerProvider.get());
    }

    public static DepositFiatRouter_Factory create(Provider<ActionBarController> controllerProvider) {
        return new DepositFiatRouter_Factory(controllerProvider);
    }

    public static DepositFiatRouter newDepositFiatRouter(ActionBarController controller) {
        return new DepositFiatRouter(controller);
    }
}
