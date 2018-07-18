package com.coinbase.android.identityverification;

import android.app.Application;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.FaceDetectionUtils;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class JumioDocumentScanPresenter_Factory implements Factory<JumioDocumentScanPresenter> {
    private final Provider<Application> appProvider;
    private final Provider<Scheduler> backgroundSchedulerProvider;
    private final Provider<FaceDetectionUtils> faceDetectionUtilsProvider;
    private final Provider<IdentityVerificationBitmapConnector> identityVerificationBitmapConnectorProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<PhotoTakenConnector> photoTakenConnectorProvider;
    private final Provider<RetakeAndContinueConnector> retakeAndContinueConnectorProvider;
    private final Provider<InAppIdentityDocumentScanRouter> routerProvider;
    private final Provider<JumioDocumentScanScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;
    private final Provider<SplitTesting> splitTestingProvider;

    public JumioDocumentScanPresenter_Factory(Provider<JumioDocumentScanScreen> screenProvider, Provider<InAppIdentityDocumentScanRouter> routerProvider, Provider<RetakeAndContinueConnector> retakeAndContinueConnectorProvider, Provider<PhotoTakenConnector> photoTakenConnectorProvider, Provider<IdentityVerificationBitmapConnector> identityVerificationBitmapConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider, Provider<LoginManager> loginManagerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<FaceDetectionUtils> faceDetectionUtilsProvider, Provider<Application> appProvider, Provider<SplitTesting> splitTestingProvider) {
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
        this.splitTestingProvider = splitTestingProvider;
    }

    public JumioDocumentScanPresenter get() {
        return provideInstance(this.screenProvider, this.routerProvider, this.retakeAndContinueConnectorProvider, this.photoTakenConnectorProvider, this.identityVerificationBitmapConnectorProvider, this.mainSchedulerProvider, this.backgroundSchedulerProvider, this.loginManagerProvider, this.snackBarWrapperProvider, this.faceDetectionUtilsProvider, this.appProvider, this.splitTestingProvider);
    }

    public static JumioDocumentScanPresenter provideInstance(Provider<JumioDocumentScanScreen> screenProvider, Provider<InAppIdentityDocumentScanRouter> routerProvider, Provider<RetakeAndContinueConnector> retakeAndContinueConnectorProvider, Provider<PhotoTakenConnector> photoTakenConnectorProvider, Provider<IdentityVerificationBitmapConnector> identityVerificationBitmapConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider, Provider<LoginManager> loginManagerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<FaceDetectionUtils> faceDetectionUtilsProvider, Provider<Application> appProvider, Provider<SplitTesting> splitTestingProvider) {
        return new JumioDocumentScanPresenter((JumioDocumentScanScreen) screenProvider.get(), (InAppIdentityDocumentScanRouter) routerProvider.get(), (RetakeAndContinueConnector) retakeAndContinueConnectorProvider.get(), (PhotoTakenConnector) photoTakenConnectorProvider.get(), (IdentityVerificationBitmapConnector) identityVerificationBitmapConnectorProvider.get(), (Scheduler) mainSchedulerProvider.get(), (Scheduler) backgroundSchedulerProvider.get(), (LoginManager) loginManagerProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (FaceDetectionUtils) faceDetectionUtilsProvider.get(), (Application) appProvider.get(), (SplitTesting) splitTestingProvider.get());
    }

    public static JumioDocumentScanPresenter_Factory create(Provider<JumioDocumentScanScreen> screenProvider, Provider<InAppIdentityDocumentScanRouter> routerProvider, Provider<RetakeAndContinueConnector> retakeAndContinueConnectorProvider, Provider<PhotoTakenConnector> photoTakenConnectorProvider, Provider<IdentityVerificationBitmapConnector> identityVerificationBitmapConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider, Provider<LoginManager> loginManagerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<FaceDetectionUtils> faceDetectionUtilsProvider, Provider<Application> appProvider, Provider<SplitTesting> splitTestingProvider) {
        return new JumioDocumentScanPresenter_Factory(screenProvider, routerProvider, retakeAndContinueConnectorProvider, photoTakenConnectorProvider, identityVerificationBitmapConnectorProvider, mainSchedulerProvider, backgroundSchedulerProvider, loginManagerProvider, snackBarWrapperProvider, faceDetectionUtilsProvider, appProvider, splitTestingProvider);
    }

    public static JumioDocumentScanPresenter newJumioDocumentScanPresenter(JumioDocumentScanScreen screen, InAppIdentityDocumentScanRouter router, RetakeAndContinueConnector retakeAndContinueConnector, PhotoTakenConnector photoTakenConnector, IdentityVerificationBitmapConnector identityVerificationBitmapConnector, Scheduler mainScheduler, Scheduler backgroundScheduler, LoginManager loginManager, SnackBarWrapper snackBarWrapper, FaceDetectionUtils faceDetectionUtils, Application app, SplitTesting splitTesting) {
        return new JumioDocumentScanPresenter(screen, router, retakeAndContinueConnector, photoTakenConnector, identityVerificationBitmapConnector, mainScheduler, backgroundScheduler, loginManager, snackBarWrapper, faceDetectionUtils, app, splitTesting);
    }
}
