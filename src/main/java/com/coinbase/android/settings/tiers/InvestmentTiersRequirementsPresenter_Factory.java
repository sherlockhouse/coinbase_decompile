package com.coinbase.android.settings.tiers;

import android.app.Application;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class InvestmentTiersRequirementsPresenter_Factory implements Factory<InvestmentTiersRequirementsPresenter> {
    private final Provider<Application> applicationProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<InvestmentTiersRequirementsRouter> routerProvider;
    private final Provider<InvestmentTiersRequirementsScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;

    public InvestmentTiersRequirementsPresenter_Factory(Provider<InvestmentTiersRequirementsScreen> screenProvider, Provider<InvestmentTiersRequirementsRouter> routerProvider, Provider<LoginManager> loginManagerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Application> applicationProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.screenProvider = screenProvider;
        this.routerProvider = routerProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.applicationProvider = applicationProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public InvestmentTiersRequirementsPresenter get() {
        return provideInstance(this.screenProvider, this.routerProvider, this.loginManagerProvider, this.snackBarWrapperProvider, this.applicationProvider, this.mainSchedulerProvider);
    }

    public static InvestmentTiersRequirementsPresenter provideInstance(Provider<InvestmentTiersRequirementsScreen> screenProvider, Provider<InvestmentTiersRequirementsRouter> routerProvider, Provider<LoginManager> loginManagerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Application> applicationProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new InvestmentTiersRequirementsPresenter((InvestmentTiersRequirementsScreen) screenProvider.get(), (InvestmentTiersRequirementsRouter) routerProvider.get(), (LoginManager) loginManagerProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (Application) applicationProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static InvestmentTiersRequirementsPresenter_Factory create(Provider<InvestmentTiersRequirementsScreen> screenProvider, Provider<InvestmentTiersRequirementsRouter> routerProvider, Provider<LoginManager> loginManagerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Application> applicationProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new InvestmentTiersRequirementsPresenter_Factory(screenProvider, routerProvider, loginManagerProvider, snackBarWrapperProvider, applicationProvider, mainSchedulerProvider);
    }

    public static InvestmentTiersRequirementsPresenter newInvestmentTiersRequirementsPresenter(InvestmentTiersRequirementsScreen screen, InvestmentTiersRequirementsRouter router, LoginManager loginManager, SnackBarWrapper snackBarWrapper, Application application, Scheduler mainScheduler) {
        return new InvestmentTiersRequirementsPresenter(screen, router, loginManager, snackBarWrapper, application, mainScheduler);
    }
}
