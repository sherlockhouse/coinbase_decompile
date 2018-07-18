package com.coinbase.android.wbl;

import android.app.Application;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AvailableBalanceAppBarPresenter_Factory implements Factory<AvailableBalanceAppBarPresenter> {
    private final Provider<Application> appProvider;
    private final Provider<AvailableBalanceAppBarScreen> availableBalanceAppBarScreenProvider;
    private final Provider<AvailableBalanceCalculator> availableBalanceCalculatorProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<MoneyFormatterUtil> moneyFormatterUtilProvider;
    private final Provider<AvailableBalanceRouter> routerProvider;

    public AvailableBalanceAppBarPresenter_Factory(Provider<Application> appProvider, Provider<AvailableBalanceAppBarScreen> availableBalanceAppBarScreenProvider, Provider<AvailableBalanceRouter> routerProvider, Provider<AvailableBalanceCalculator> availableBalanceCalculatorProvider, Provider<LoginManager> loginManagerProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider) {
        this.appProvider = appProvider;
        this.availableBalanceAppBarScreenProvider = availableBalanceAppBarScreenProvider;
        this.routerProvider = routerProvider;
        this.availableBalanceCalculatorProvider = availableBalanceCalculatorProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.moneyFormatterUtilProvider = moneyFormatterUtilProvider;
    }

    public AvailableBalanceAppBarPresenter get() {
        return provideInstance(this.appProvider, this.availableBalanceAppBarScreenProvider, this.routerProvider, this.availableBalanceCalculatorProvider, this.loginManagerProvider, this.moneyFormatterUtilProvider);
    }

    public static AvailableBalanceAppBarPresenter provideInstance(Provider<Application> appProvider, Provider<AvailableBalanceAppBarScreen> availableBalanceAppBarScreenProvider, Provider<AvailableBalanceRouter> routerProvider, Provider<AvailableBalanceCalculator> availableBalanceCalculatorProvider, Provider<LoginManager> loginManagerProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider) {
        return new AvailableBalanceAppBarPresenter((Application) appProvider.get(), (AvailableBalanceAppBarScreen) availableBalanceAppBarScreenProvider.get(), (AvailableBalanceRouter) routerProvider.get(), (AvailableBalanceCalculator) availableBalanceCalculatorProvider.get(), (LoginManager) loginManagerProvider.get(), (MoneyFormatterUtil) moneyFormatterUtilProvider.get());
    }

    public static AvailableBalanceAppBarPresenter_Factory create(Provider<Application> appProvider, Provider<AvailableBalanceAppBarScreen> availableBalanceAppBarScreenProvider, Provider<AvailableBalanceRouter> routerProvider, Provider<AvailableBalanceCalculator> availableBalanceCalculatorProvider, Provider<LoginManager> loginManagerProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider) {
        return new AvailableBalanceAppBarPresenter_Factory(appProvider, availableBalanceAppBarScreenProvider, routerProvider, availableBalanceCalculatorProvider, loginManagerProvider, moneyFormatterUtilProvider);
    }

    public static AvailableBalanceAppBarPresenter newAvailableBalanceAppBarPresenter(Application app, AvailableBalanceAppBarScreen availableBalanceAppBarScreen, AvailableBalanceRouter router, AvailableBalanceCalculator availableBalanceCalculator, LoginManager loginManager, MoneyFormatterUtil moneyFormatterUtil) {
        return new AvailableBalanceAppBarPresenter(app, availableBalanceAppBarScreen, router, availableBalanceCalculator, loginManager, moneyFormatterUtil);
    }
}
