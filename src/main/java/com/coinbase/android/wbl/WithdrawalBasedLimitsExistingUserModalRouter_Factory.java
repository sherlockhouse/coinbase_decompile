package com.coinbase.android.wbl;

import android.content.SharedPreferences;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class WithdrawalBasedLimitsExistingUserModalRouter_Factory implements Factory<WithdrawalBasedLimitsExistingUserModalRouter> {
    private final Provider<ActionBarController> actionBarControllerProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;
    private final Provider<SplitTesting> splitTestingProvider;

    public WithdrawalBasedLimitsExistingUserModalRouter_Factory(Provider<SharedPreferences> sharedPreferencesProvider, Provider<LoginManager> loginManagerProvider, Provider<ActionBarController> actionBarControllerProvider, Provider<SplitTesting> splitTestingProvider) {
        this.sharedPreferencesProvider = sharedPreferencesProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.actionBarControllerProvider = actionBarControllerProvider;
        this.splitTestingProvider = splitTestingProvider;
    }

    public WithdrawalBasedLimitsExistingUserModalRouter get() {
        return provideInstance(this.sharedPreferencesProvider, this.loginManagerProvider, this.actionBarControllerProvider, this.splitTestingProvider);
    }

    public static WithdrawalBasedLimitsExistingUserModalRouter provideInstance(Provider<SharedPreferences> sharedPreferencesProvider, Provider<LoginManager> loginManagerProvider, Provider<ActionBarController> actionBarControllerProvider, Provider<SplitTesting> splitTestingProvider) {
        return new WithdrawalBasedLimitsExistingUserModalRouter((SharedPreferences) sharedPreferencesProvider.get(), (LoginManager) loginManagerProvider.get(), (ActionBarController) actionBarControllerProvider.get(), (SplitTesting) splitTestingProvider.get());
    }

    public static WithdrawalBasedLimitsExistingUserModalRouter_Factory create(Provider<SharedPreferences> sharedPreferencesProvider, Provider<LoginManager> loginManagerProvider, Provider<ActionBarController> actionBarControllerProvider, Provider<SplitTesting> splitTestingProvider) {
        return new WithdrawalBasedLimitsExistingUserModalRouter_Factory(sharedPreferencesProvider, loginManagerProvider, actionBarControllerProvider, splitTestingProvider);
    }

    public static WithdrawalBasedLimitsExistingUserModalRouter newWithdrawalBasedLimitsExistingUserModalRouter(SharedPreferences sharedPreferences, LoginManager loginManager, ActionBarController actionBarController, SplitTesting splitTesting) {
        return new WithdrawalBasedLimitsExistingUserModalRouter(sharedPreferences, loginManager, actionBarController, splitTesting);
    }
}
