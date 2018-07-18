package com.coinbase.android.signin.state;

import android.app.Application;
import com.coinbase.android.identityverification.IdentityVerificationBitmapConnector;
import com.coinbase.android.identityverification.PhotoTakenConnector;
import com.coinbase.android.identityverification.RetakeAndContinueConnector;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.FaceDetectionUtils;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class UpfrontKycIdentityDocumentScanPresenter_Factory implements Factory<UpfrontKycIdentityDocumentScanPresenter> {
    private final Provider<Application> appProvider;
    private final Provider<Scheduler> backgroundSchedulerProvider;
    private final Provider<FaceDetectionUtils> faceDetectionUtilsProvider;
    private final Provider<IdentityVerificationBitmapConnector> identityVerificationBitmapConnectorProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<PhotoTakenConnector> photoTakenConnectorProvider;
    private final Provider<RetakeAndContinueConnector> retakeAndContinueConnectorProvider;
    private final Provider<UpfrontKycIdentityDocumentScanRouter> routerProvider;
    private final Provider<UpfrontKycIdentityDocumentScanScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;

    public UpfrontKycIdentityDocumentScanPresenter_Factory(Provider<UpfrontKycIdentityDocumentScanScreen> screenProvider, Provider<UpfrontKycIdentityDocumentScanRouter> routerProvider, Provider<RetakeAndContinueConnector> retakeAndContinueConnectorProvider, Provider<PhotoTakenConnector> photoTakenConnectorProvider, Provider<IdentityVerificationBitmapConnector> identityVerificationBitmapConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider, Provider<LoginManager> loginManagerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<FaceDetectionUtils> faceDetectionUtilsProvider, Provider<Application> appProvider) {
        this.screenProvider = screenProvider;
        this.routerProvider = routerProvider;
        this.retakeAndContinueConnectorProvider = retakeAndContinueConnectorProvider;
        this.photoTakenConnectorProvider = photoTakenConnectorProvider;
        this.identityVerificationBitmapConnectorProvider = identityVerificationBitmapConnectorProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
        this.backgroundSchedulerProvider = backgroundSchedulerProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.faceDetectionUtilsProvider = faceDetectionUtilsProvider;
        this.appProvider = appProvider;
    }

    public UpfrontKycIdentityDocumentScanPresenter get() {
        return provideInstance(this.screenProvider, this.routerProvider, this.retakeAndContinueConnectorProvider, this.photoTakenConnectorProvider, this.identityVerificationBitmapConnectorProvider, this.mainSchedulerProvider, this.backgroundSchedulerProvider, this.loginManagerProvider, this.snackBarWrapperProvider, this.faceDetectionUtilsProvider, this.appProvider);
    }

    public static UpfrontKycIdentityDocumentScanPresenter provideInstance(Provider<UpfrontKycIdentityDocumentScanScreen> screenProvider, Provider<UpfrontKycIdentityDocumentScanRouter> routerProvider, Provider<RetakeAndContinueConnector> retakeAndContinueConnectorProvider, Provider<PhotoTakenConnector> photoTakenConnectorProvider, Provider<IdentityVerificationBitmapConnector> identityVerificationBitmapConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider, Provider<LoginManager> loginManagerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<FaceDetectionUtils> faceDetectionUtilsProvider, Provider<Application> appProvider) {
        return new UpfrontKycIdentityDocumentScanPresenter((UpfrontKycIdentityDocumentScanScreen) screenProvider.get(), (UpfrontKycIdentityDocumentScanRouter) routerProvider.get(), (RetakeAndContinueConnector) retakeAndContinueConnectorProvider.get(), (PhotoTakenConnector) photoTakenConnectorProvider.get(), (IdentityVerificationBitmapConnector) identityVerificationBitmapConnectorProvider.get(), (Scheduler) mainSchedulerProvider.get(), (Scheduler) backgroundSchedulerProvider.get(), (LoginManager) loginManagerProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (FaceDetectionUtils) faceDetectionUtilsProvider.get(), (Application) appProvider.get());
    }

    public static UpfrontKycIdentityDocumentScanPresenter_Factory create(Provider<UpfrontKycIdentityDocumentScanScreen> screenProvider, Provider<UpfrontKycIdentityDocumentScanRouter> routerProvider, Provider<RetakeAndContinueConnector> retakeAndContinueConnectorProvider, Provider<PhotoTakenConnector> photoTakenConnectorProvider, Provider<IdentityVerificationBitmapConnector> identityVerificationBitmapConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider, Provider<LoginManager> loginManagerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<FaceDetectionUtils> faceDetectionUtilsProvider, Provider<Application> appProvider) {
        return new UpfrontKycIdentityDocumentScanPresenter_Factory(screenProvider, routerProvider, retakeAndContinueConnectorProvider, photoTakenConnectorProvider, identityVerificationBitmapConnectorProvider, mainSchedulerProvider, backgroundSchedulerProvider, loginManagerProvider, snackBarWrapperProvider, faceDetectionUtilsProvider, appProvider);
    }

    public static UpfrontKycIdentityDocumentScanPresenter newUpfrontKycIdentityDocumentScanPresenter(UpfrontKycIdentityDocumentScanScreen screen, UpfrontKycIdentityDocumentScanRouter router, RetakeAndContinueConnector retakeAndContinueConnector, PhotoTakenConnector photoTakenConnector, IdentityVerificationBitmapConnector identityVerificationBitmapConnector, Scheduler mainScheduler, Scheduler backgroundScheduler, LoginManager loginManager, SnackBarWrapper snackBarWrapper, FaceDetectionUtils faceDetectionUtils, Application app) {
        return new UpfrontKycIdentityDocumentScanPresenter(screen, router, retakeAndContinueConnector, photoTakenConnector, identityVerificationBitmapConnector, mainScheduler, backgroundScheduler, loginManager, snackBarWrapper, faceDetectionUtils, app);
    }
}
