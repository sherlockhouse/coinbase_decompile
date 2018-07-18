package com.coinbase.android.paymentmethods;

import android.app.Application;
import android.view.LayoutInflater;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.ui.SuccessRouter;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class AddPlaidAccountPresenter_Factory implements Factory<AddPlaidAccountPresenter> {
    private final Provider<Application> contextProvider;
    private final Provider<LayoutInflater> layoutInflaterProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<AddPlaidAccountScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;
    private final Provider<SuccessRouter> successRouterProvider;

    public AddPlaidAccountPresenter_Factory(Provider<AddPlaidAccountScreen> screenProvider, Provider<LoginManager> loginManagerProvider, Provider<Scheduler> mainSchedulerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Application> contextProvider, Provider<LayoutInflater> layoutInflaterProvider, Provider<SuccessRouter> successRouterProvider) {
        this.screenProvider = screenProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.contextProvider = contextProvider;
        this.layoutInflaterProvider = layoutInflaterProvider;
        this.successRouterProvider = successRouterProvider;
    }

    public AddPlaidAccountPresenter get() {
        return provideInstance(this.screenProvider, this.loginManagerProvider, this.mainSchedulerProvider, this.snackBarWrapperProvider, this.contextProvider, this.layoutInflaterProvider, this.successRouterProvider);
    }

    public static AddPlaidAccountPresenter provideInstance(Provider<AddPlaidAccountScreen> screenProvider, Provider<LoginManager> loginManagerProvider, Provider<Scheduler> mainSchedulerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Application> contextProvider, Provider<LayoutInflater> layoutInflaterProvider, Provider<SuccessRouter> successRouterProvider) {
        return new AddPlaidAccountPresenter((AddPlaidAccountScreen) screenProvider.get(), (LoginManager) loginManagerProvider.get(), (Scheduler) mainSchedulerProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (Application) contextProvider.get(), (LayoutInflater) layoutInflaterProvider.get(), (SuccessRouter) successRouterProvider.get());
    }

    public static AddPlaidAccountPresenter_Factory create(Provider<AddPlaidAccountScreen> screenProvider, Provider<LoginManager> loginManagerProvider, Provider<Scheduler> mainSchedulerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Application> contextProvider, Provider<LayoutInflater> layoutInflaterProvider, Provider<SuccessRouter> successRouterProvider) {
        return new AddPlaidAccountPresenter_Factory(screenProvider, loginManagerProvider, mainSchedulerProvider, snackBarWrapperProvider, contextProvider, layoutInflaterProvider, successRouterProvider);
    }

    public static AddPlaidAccountPresenter newAddPlaidAccountPresenter(AddPlaidAccountScreen screen, LoginManager loginManager, Scheduler mainScheduler, SnackBarWrapper snackBarWrapper, Application context, LayoutInflater layoutInflater, SuccessRouter successRouter) {
        return new AddPlaidAccountPresenter(screen, loginManager, mainScheduler, snackBarWrapper, context, layoutInflater, successRouter);
    }
}
