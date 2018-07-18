package com.coinbase.android.deposits.fiat;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class WithdrawFiatConfirmationRouter_Factory implements Factory<WithdrawFiatConfirmationRouter> {
    private final Provider<ActionBarController> actionBarControllerProvider;

    public WithdrawFiatConfirmationRouter_Factory(Provider<ActionBarController> actionBarControllerProvider) {
        this.actionBarControllerProvider = actionBarControllerProvider;
    }

    public WithdrawFiatConfirmationRouter get() {
        return provideInstance(this.actionBarControllerProvider);
    }

    public static WithdrawFiatConfirmationRouter provideInstance(Provider<ActionBarController> actionBarControllerProvider) {
        return new WithdrawFiatConfirmationRouter((ActionBarController) actionBarControllerProvider.get());
    }

    public static WithdrawFiatConfirmationRouter_Factory create(Provider<ActionBarController> actionBarControllerProvider) {
        return new WithdrawFiatConfirmationRouter_Factory(actionBarControllerProvider);
    }

    public static WithdrawFiatConfirmationRouter newWithdrawFiatConfirmationRouter(ActionBarController actionBarController) {
        return new WithdrawFiatConfirmationRouter(actionBarController);
    }
}
