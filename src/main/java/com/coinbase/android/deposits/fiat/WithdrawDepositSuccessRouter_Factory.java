package com.coinbase.android.deposits.fiat;

import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.BottomNavigationConnector;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class WithdrawDepositSuccessRouter_Factory implements Factory<WithdrawDepositSuccessRouter> {
    private final Provider<ActionBarController> actionBarControllerProvider;
    private final Provider<BottomNavigationConnector> bottomNavigationConnectorProvider;

    public WithdrawDepositSuccessRouter_Factory(Provider<ActionBarController> actionBarControllerProvider, Provider<BottomNavigationConnector> bottomNavigationConnectorProvider) {
        this.actionBarControllerProvider = actionBarControllerProvider;
        this.bottomNavigationConnectorProvider = bottomNavigationConnectorProvider;
    }

    public WithdrawDepositSuccessRouter get() {
        return provideInstance(this.actionBarControllerProvider, this.bottomNavigationConnectorProvider);
    }

    public static WithdrawDepositSuccessRouter provideInstance(Provider<ActionBarController> actionBarControllerProvider, Provider<BottomNavigationConnector> bottomNavigationConnectorProvider) {
        return new WithdrawDepositSuccessRouter((ActionBarController) actionBarControllerProvider.get(), (BottomNavigationConnector) bottomNavigationConnectorProvider.get());
    }

    public static WithdrawDepositSuccessRouter_Factory create(Provider<ActionBarController> actionBarControllerProvider, Provider<BottomNavigationConnector> bottomNavigationConnectorProvider) {
        return new WithdrawDepositSuccessRouter_Factory(actionBarControllerProvider, bottomNavigationConnectorProvider);
    }

    public static WithdrawDepositSuccessRouter newWithdrawDepositSuccessRouter(ActionBarController actionBarController, BottomNavigationConnector bottomNavigationConnector) {
        return new WithdrawDepositSuccessRouter(actionBarController, bottomNavigationConnector);
    }
}
