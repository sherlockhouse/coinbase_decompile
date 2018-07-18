package com.coinbase.android;

import android.app.Application;
import com.coinbase.android.ui.CurrenciesUpdatedConnector;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class MainPresenter_Factory implements Factory<MainPresenter> {
    private final Provider<Application> appProvider;
    private final Provider<Scheduler> backgroundSchedulerProvider;
    private final Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<MainRouter> routerProvider;

    public MainPresenter_Factory(Provider<LoginManager> loginManagerProvider, Provider<Application> appProvider, Provider<MainRouter> routerProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        this.loginManagerProvider = loginManagerProvider;
        this.appProvider = appProvider;
        this.routerProvider = routerProvider;
        this.currenciesUpdatedConnectorProvider = currenciesUpdatedConnectorProvider;
        this.backgroundSchedulerProvider = backgroundSchedulerProvider;
    }

    public MainPresenter get() {
        return provideInstance(this.loginManagerProvider, this.appProvider, this.routerProvider, this.currenciesUpdatedConnectorProvider, this.backgroundSchedulerProvider);
    }

    public static MainPresenter provideInstance(Provider<LoginManager> loginManagerProvider, Provider<Application> appProvider, Provider<MainRouter> routerProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new MainPresenter((LoginManager) loginManagerProvider.get(), (Application) appProvider.get(), (MainRouter) routerProvider.get(), (CurrenciesUpdatedConnector) currenciesUpdatedConnectorProvider.get(), (Scheduler) backgroundSchedulerProvider.get());
    }

    public static MainPresenter_Factory create(Provider<LoginManager> loginManagerProvider, Provider<Application> appProvider, Provider<MainRouter> routerProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new MainPresenter_Factory(loginManagerProvider, appProvider, routerProvider, currenciesUpdatedConnectorProvider, backgroundSchedulerProvider);
    }

    public static MainPresenter newMainPresenter(LoginManager loginManager, Application app, MainRouter router, CurrenciesUpdatedConnector currenciesUpdatedConnector, Scheduler backgroundScheduler) {
        return new MainPresenter(loginManager, app, router, currenciesUpdatedConnector, backgroundScheduler);
    }
}
