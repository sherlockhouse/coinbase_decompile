package com.coinbase.android.identityverification;

import android.app.Application;
import com.coinbase.android.ui.BackNavigationConnector;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class InAppIdentityAcceptableDocumentsPresenter_Factory implements Factory<InAppIdentityAcceptableDocumentsPresenter> {
    private final Provider<Application> appProvider;
    private final Provider<BackNavigationConnector> backNavigationConnectorProvider;
    private final Provider<IdentityVerificationBitmapConnector> bitmapConnectorProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<InAppIdentityAcceptableDocumentsRouter> routerProvider;
    private final Provider<IdentityAcceptableDocumentsScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;

    public InAppIdentityAcceptableDocumentsPresenter_Factory(Provider<IdentityAcceptableDocumentsScreen> screenProvider, Provider<LoginManager> loginManagerProvider, Provider<Application> appProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<BackNavigationConnector> backNavigationConnectorProvider, Provider<IdentityVerificationBitmapConnector> bitmapConnectorProvider, Provider<InAppIdentityAcceptableDocumentsRouter> routerProvider) {
        this.screenProvider = screenProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.appProvider = appProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.backNavigationConnectorProvider = backNavigationConnectorProvider;
        this.bitmapConnectorProvider = bitmapConnectorProvider;
        this.routerProvider = routerProvider;
    }

    public InAppIdentityAcceptableDocumentsPresenter get() {
        return provideInstance(this.screenProvider, this.loginManagerProvider, this.appProvider, this.snackBarWrapperProvider, this.backNavigationConnectorProvider, this.bitmapConnectorProvider, this.routerProvider);
    }

    public static InAppIdentityAcceptableDocumentsPresenter provideInstance(Provider<IdentityAcceptableDocumentsScreen> screenProvider, Provider<LoginManager> loginManagerProvider, Provider<Application> appProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<BackNavigationConnector> backNavigationConnectorProvider, Provider<IdentityVerificationBitmapConnector> bitmapConnectorProvider, Provider<InAppIdentityAcceptableDocumentsRouter> routerProvider) {
        return new InAppIdentityAcceptableDocumentsPresenter((IdentityAcceptableDocumentsScreen) screenProvider.get(), (LoginManager) loginManagerProvider.get(), (Application) appProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (BackNavigationConnector) backNavigationConnectorProvider.get(), (IdentityVerificationBitmapConnector) bitmapConnectorProvider.get(), (InAppIdentityAcceptableDocumentsRouter) routerProvider.get());
    }

    public static InAppIdentityAcceptableDocumentsPresenter_Factory create(Provider<IdentityAcceptableDocumentsScreen> screenProvider, Provider<LoginManager> loginManagerProvider, Provider<Application> appProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<BackNavigationConnector> backNavigationConnectorProvider, Provider<IdentityVerificationBitmapConnector> bitmapConnectorProvider, Provider<InAppIdentityAcceptableDocumentsRouter> routerProvider) {
        return new InAppIdentityAcceptableDocumentsPresenter_Factory(screenProvider, loginManagerProvider, appProvider, snackBarWrapperProvider, backNavigationConnectorProvider, bitmapConnectorProvider, routerProvider);
    }

    public static InAppIdentityAcceptableDocumentsPresenter newInAppIdentityAcceptableDocumentsPresenter(IdentityAcceptableDocumentsScreen screen, LoginManager loginManager, Application app, SnackBarWrapper snackBarWrapper, BackNavigationConnector backNavigationConnector, IdentityVerificationBitmapConnector bitmapConnector, InAppIdentityAcceptableDocumentsRouter router) {
        return new InAppIdentityAcceptableDocumentsPresenter(screen, loginManager, app, snackBarWrapper, backNavigationConnector, bitmapConnector, router);
    }
}
