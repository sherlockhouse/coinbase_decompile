package com.coinbase.android.signin.state;

import android.app.Application;
import android.view.Window;
import com.coinbase.android.identityverification.IdentityVerificationBitmapConnector;
import com.coinbase.android.identityverification.PhotoTakenConnector;
import com.coinbase.android.ui.SnackBarWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class UpfrontKycTakeDocumentPhotoPresenter_Factory implements Factory<UpfrontKycTakeDocumentPhotoPresenter> {
    private final Provider<Application> appProvider;
    private final Provider<IdentityVerificationBitmapConnector> identityVerificationBitmapConnectorProvider;
    private final Provider<PhotoTakenConnector> photoTakenConnectorProvider;
    private final Provider<UpfrontKycTakeDocumentPhotoRouter> routerProvider;
    private final Provider<UpfrontKycTakeDocumentPhotoScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;
    private final Provider<Window> windowProvider;

    public UpfrontKycTakeDocumentPhotoPresenter_Factory(Provider<UpfrontKycTakeDocumentPhotoScreen> screenProvider, Provider<UpfrontKycTakeDocumentPhotoRouter> routerProvider, Provider<IdentityVerificationBitmapConnector> identityVerificationBitmapConnectorProvider, Provider<PhotoTakenConnector> photoTakenConnectorProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Window> windowProvider, Provider<Application> appProvider) {
        this.screenProvider = screenProvider;
        this.routerProvider = routerProvider;
        this.identityVerificationBitmapConnectorProvider = identityVerificationBitmapConnectorProvider;
        this.photoTakenConnectorProvider = photoTakenConnectorProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.windowProvider = windowProvider;
        this.appProvider = appProvider;
    }

    public UpfrontKycTakeDocumentPhotoPresenter get() {
        return provideInstance(this.screenProvider, this.routerProvider, this.identityVerificationBitmapConnectorProvider, this.photoTakenConnectorProvider, this.snackBarWrapperProvider, this.windowProvider, this.appProvider);
    }

    public static UpfrontKycTakeDocumentPhotoPresenter provideInstance(Provider<UpfrontKycTakeDocumentPhotoScreen> screenProvider, Provider<UpfrontKycTakeDocumentPhotoRouter> routerProvider, Provider<IdentityVerificationBitmapConnector> identityVerificationBitmapConnectorProvider, Provider<PhotoTakenConnector> photoTakenConnectorProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Window> windowProvider, Provider<Application> appProvider) {
        return new UpfrontKycTakeDocumentPhotoPresenter((UpfrontKycTakeDocumentPhotoScreen) screenProvider.get(), (UpfrontKycTakeDocumentPhotoRouter) routerProvider.get(), (IdentityVerificationBitmapConnector) identityVerificationBitmapConnectorProvider.get(), (PhotoTakenConnector) photoTakenConnectorProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (Window) windowProvider.get(), (Application) appProvider.get());
    }

    public static UpfrontKycTakeDocumentPhotoPresenter_Factory create(Provider<UpfrontKycTakeDocumentPhotoScreen> screenProvider, Provider<UpfrontKycTakeDocumentPhotoRouter> routerProvider, Provider<IdentityVerificationBitmapConnector> identityVerificationBitmapConnectorProvider, Provider<PhotoTakenConnector> photoTakenConnectorProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Window> windowProvider, Provider<Application> appProvider) {
        return new UpfrontKycTakeDocumentPhotoPresenter_Factory(screenProvider, routerProvider, identityVerificationBitmapConnectorProvider, photoTakenConnectorProvider, snackBarWrapperProvider, windowProvider, appProvider);
    }

    public static UpfrontKycTakeDocumentPhotoPresenter newUpfrontKycTakeDocumentPhotoPresenter(UpfrontKycTakeDocumentPhotoScreen screen, UpfrontKycTakeDocumentPhotoRouter router, IdentityVerificationBitmapConnector identityVerificationBitmapConnector, PhotoTakenConnector photoTakenConnector, SnackBarWrapper snackBarWrapper, Window window, Application app) {
        return new UpfrontKycTakeDocumentPhotoPresenter(screen, router, identityVerificationBitmapConnector, photoTakenConnector, snackBarWrapper, window, app);
    }
}
