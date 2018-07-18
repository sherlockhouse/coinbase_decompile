package com.coinbase.android.deposits.fiat;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class DepositFiatConfirmationRouter_Factory implements Factory<DepositFiatConfirmationRouter> {
    private final Provider<ActionBarController> actionBarControllerProvider;

    public DepositFiatConfirmationRouter_Factory(Provider<ActionBarController> actionBarControllerProvider) {
        this.actionBarControllerProvider = actionBarControllerProvider;
    }

    public DepositFiatConfirmationRouter get() {
        return provideInstance(this.actionBarControllerProvider);
    }

    public static DepositFiatConfirmationRouter provideInstance(Provider<ActionBarController> actionBarControllerProvider) {
        return new DepositFiatConfirmationRouter((ActionBarController) actionBarControllerProvider.get());
    }

    public static DepositFiatConfirmationRouter_Factory create(Provider<ActionBarController> actionBarControllerProvider) {
        return new DepositFiatConfirmationRouter_Factory(actionBarControllerProvider);
    }

    public static DepositFiatConfirmationRouter newDepositFiatConfirmationRouter(ActionBarController actionBarController) {
        return new DepositFiatConfirmationRouter(actionBarController);
    }
}
