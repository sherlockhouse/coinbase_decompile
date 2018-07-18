package com.coinbase.android.identityverification;

import android.app.Application;
import android.view.Window;
import com.coinbase.android.ui.SnackBarWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class TakeDocumentPhotoPresenter_Factory implements Factory<TakeDocumentPhotoPresenter> {
    private final Provider<Application> appProvider;
    private final Provider<IdentityVerificationBitmapConnector> identityVerificationBitmapConnectorProvider;
    private final Provider<PhotoTakenConnector> photoTakenConnectorProvider;
    private final Provider<TakeDocumentPhotoScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;
    private final Provider<Window> windowProvider;

    public TakeDocumentPhotoPresenter_Factory(Provider<TakeDocumentPhotoScreen> screenProvider, Provider<IdentityVerificationBitmapConnector> identityVerificationBitmapConnectorProvider, Provider<PhotoTakenConnector> photoTakenConnectorProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Window> windowProvider, Provider<Application> appProvider) {
        this.screenProvider = screenProvider;
        this.identityVerificationBitmapConnectorProvider = identityVerificationBitmapConnectorProvider;
        this.photoTakenConnectorProvider = photoTakenConnectorProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.windowProvider = windowProvider;
        this.appProvider = appProvider;
    }

    public TakeDocumentPhotoPresenter get() {
        return provideInstance(this.screenProvider, this.identityVerificationBitmapConnectorProvider, this.photoTakenConnectorProvider, this.snackBarWrapperProvider, this.windowProvider, this.appProvider);
    }

    public static TakeDocumentPhotoPresenter provideInstance(Provider<TakeDocumentPhotoScreen> screenProvider, Provider<IdentityVerificationBitmapConnector> identityVerificationBitmapConnectorProvider, Provider<PhotoTakenConnector> photoTakenConnectorProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Window> windowProvider, Provider<Application> appProvider) {
        return new TakeDocumentPhotoPresenter((TakeDocumentPhotoScreen) screenProvider.get(), (IdentityVerificationBitmapConnector) identityVerificationBitmapConnectorProvider.get(), (PhotoTakenConnector) photoTakenConnectorProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (Window) windowProvider.get(), (Application) appProvider.get());
    }

    public static TakeDocumentPhotoPresenter_Factory create(Provider<TakeDocumentPhotoScreen> screenProvider, Provider<IdentityVerificationBitmapConnector> identityVerificationBitmapConnectorProvider, Provider<PhotoTakenConnector> photoTakenConnectorProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Window> windowProvider, Provider<Application> appProvider) {
        return new TakeDocumentPhotoPresenter_Factory(screenProvider, identityVerificationBitmapConnectorProvider, photoTakenConnectorProvider, snackBarWrapperProvider, windowProvider, appProvider);
    }

    public static TakeDocumentPhotoPresenter newTakeDocumentPhotoPresenter(TakeDocumentPhotoScreen screen, IdentityVerificationBitmapConnector identityVerificationBitmapConnector, PhotoTakenConnector photoTakenConnector, SnackBarWrapper snackBarWrapper, Window window, Application app) {
        return new TakeDocumentPhotoPresenter(screen, identityVerificationBitmapConnector, photoTakenConnector, snackBarWrapper, window, app);
    }
}
