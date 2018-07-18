package com.coinbase.android.deposits.fiat;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class WithdrawFiatRouter_Factory implements Factory<WithdrawFiatRouter> {
    private final Provider<ActionBarController> controllerProvider;

    public WithdrawFiatRouter_Factory(Provider<ActionBarController> controllerProvider) {
        this.controllerProvider = controllerProvider;
    }

    public WithdrawFiatRouter get() {
        return provideInstance(this.controllerProvider);
    }

    public static WithdrawFiatRouter provideInstance(Provider<ActionBarController> controllerProvider) {
        return new WithdrawFiatRouter((ActionBarController) controllerProvider.get());
    }

    public static WithdrawFiatRouter_Factory create(Provider<ActionBarController> controllerProvider) {
        return new WithdrawFiatRouter_Factory(controllerProvider);
    }

    public static WithdrawFiatRouter newWithdrawFiatRouter(ActionBarController controller) {
        return new WithdrawFiatRouter(controller);
    }
}
