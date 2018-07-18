package com.coinbase.android.signin.state;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import com.coinbase.android.identityverification.IdentityVerificationBitmapConnector;
import com.coinbase.android.ui.BackNavigationConnector;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class UpfrontKycIdentityAcceptableDocumentsPresenter_Factory implements Factory<UpfrontKycIdentityAcceptableDocumentsPresenter> {
    private final Provider<AppCompatActivity> appCompatActivityProvider;
    private final Provider<Application> appProvider;
    private final Provider<BackNavigationConnector> backNavigationConnectorProvider;
    private final Provider<IdentityVerificationBitmapConnector> bitmapConnectorProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<UpfrontKycIdentityAcceptableDocumentsRouter> routerProvider;
    private final Provider<UpfrontKycIdentityAcceptableDocumentsScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;

    public UpfrontKycIdentityAcceptableDocumentsPresenter_Factory(Provider<UpfrontKycIdentityAcceptableDocumentsScreen> screenProvider, Provider<AppCompatActivity> appCompatActivityProvider, Provider<LoginManager> loginManagerProvider, Provider<Application> appProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<BackNavigationConnector> backNavigationConnectorProvider, Provider<IdentityVerificationBitmapConnector> bitmapConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<UpfrontKycIdentityAcceptableDocumentsRouter> routerProvider) {
        this.screenProvider = screenProvider;
        this.appCompatActivityProvider = appCompatActivityProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.appProvider = appProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.backNavigationConnectorProvider = backNavigationConnectorProvider;
        this.bitmapConnectorProvider = bitmapConnectorProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
        this.routerProvider = routerProvider;
    }

    public UpfrontKycIdentityAcceptableDocumentsPresenter get() {
        return provideInstance(this.screenProvider, this.appCompatActivityProvider, this.loginManagerProvider, this.appProvider, this.snackBarWrapperProvider, this.backNavigationConnectorProvider, this.bitmapConnectorProvider, this.mainSchedulerProvider, this.routerProvider);
    }

    public static UpfrontKycIdentityAcceptableDocumentsPresenter provideInstance(Provider<UpfrontKycIdentityAcceptableDocumentsScreen> screenProvider, Provider<AppCompatActivity> appCompatActivityProvider, Provider<LoginManager> loginManagerProvider, Provider<Application> appProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<BackNavigationConnector> backNavigationConnectorProvider, Provider<IdentityVerificationBitmapConnector> bitmapConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<UpfrontKycIdentityAcceptableDocumentsRouter> routerProvider) {
        return new UpfrontKycIdentityAcceptableDocumentsPresenter((UpfrontKycIdentityAcceptableDocumentsScreen) screenProvider.get(), (AppCompatActivity) appCompatActivityProvider.get(), (LoginManager) loginManagerProvider.get(), (Application) appProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (BackNavigationConnector) backNavigationConnectorProvider.get(), (IdentityVerificationBitmapConnector) bitmapConnectorProvider.get(), (Scheduler) mainSchedulerProvider.get(), (UpfrontKycIdentityAcceptableDocumentsRouter) routerProvider.get());
    }

    public static UpfrontKycIdentityAcceptableDocumentsPresenter_Factory create(Provider<UpfrontKycIdentityAcceptableDocumentsScreen> screenProvider, Provider<AppCompatActivity> appCompatActivityProvider, Provider<LoginManager> loginManagerProvider, Provider<Application> appProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<BackNavigationConnector> backNavigationConnectorProvider, Provider<IdentityVerificationBitmapConnector> bitmapConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<UpfrontKycIdentityAcceptableDocumentsRouter> routerProvider) {
        return new UpfrontKycIdentityAcceptableDocumentsPresenter_Factory(screenProvider, appCompatActivityProvider, loginManagerProvider, appProvider, snackBarWrapperProvider, backNavigationConnectorProvider, bitmapConnectorProvider, mainSchedulerProvider, routerProvider);
    }

    public static UpfrontKycIdentityAcceptableDocumentsPresenter newUpfrontKycIdentityAcceptableDocumentsPresenter(UpfrontKycIdentityAcceptableDocumentsScreen screen, AppCompatActivity appCompatActivity, LoginManager loginManager, Application app, SnackBarWrapper snackBarWrapper, BackNavigationConnector backNavigationConnector, IdentityVerificationBitmapConnector bitmapConnector, Scheduler mainScheduler, UpfrontKycIdentityAcceptableDocumentsRouter router) {
        return new UpfrontKycIdentityAcceptableDocumentsPresenter(screen, appCompatActivity, loginManager, app, snackBarWrapper, backNavigationConnector, bitmapConnector, mainScheduler, router);
    }
}
