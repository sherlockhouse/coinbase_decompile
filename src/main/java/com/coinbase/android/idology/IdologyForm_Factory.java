package com.coinbase.android.idology;

import android.app.Application;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class IdologyForm_Factory implements Factory<IdologyForm> {
    private final Provider<Application> appProvider;
    private final Provider<IdologyFormErrorMatcher> idologyFormErrorMatcherProvider;
    private final Provider<IdologyRetryConnector> idologyRetryConnectorProvider;
    private final Provider<String> idologyTrackingContextProvider;
    private final Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<ProgressConnector> progressConnectorProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;

    public IdologyForm_Factory(Provider<Application> appProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<LoginManager> loginManagerProvider, Provider<Scheduler> mainSchedulerProvider, Provider<ProgressConnector> progressConnectorProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<IdologyRetryConnector> idologyRetryConnectorProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<IdologyFormErrorMatcher> idologyFormErrorMatcherProvider, Provider<String> idologyTrackingContextProvider) {
        this.appProvider = appProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
        this.progressConnectorProvider = progressConnectorProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.idologyRetryConnectorProvider = idologyRetryConnectorProvider;
        this.idologyVerificationConnectorProvider = idologyVerificationConnectorProvider;
        this.idologyFormErrorMatcherProvider = idologyFormErrorMatcherProvider;
        this.idologyTrackingContextProvider = idologyTrackingContextProvider;
    }

    public IdologyForm get() {
        return provideInstance(this.appProvider, this.mixpanelTrackingProvider, this.loginManagerProvider, this.mainSchedulerProvider, this.progressConnectorProvider, this.snackBarWrapperProvider, this.idologyRetryConnectorProvider, this.idologyVerificationConnectorProvider, this.idologyFormErrorMatcherProvider, this.idologyTrackingContextProvider);
    }

    public static IdologyForm provideInstance(Provider<Application> appProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<LoginManager> loginManagerProvider, Provider<Scheduler> mainSchedulerProvider, Provider<ProgressConnector> progressConnectorProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<IdologyRetryConnector> idologyRetryConnectorProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<IdologyFormErrorMatcher> idologyFormErrorMatcherProvider, Provider<String> idologyTrackingContextProvider) {
        return new IdologyForm((Application) appProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (LoginManager) loginManagerProvider.get(), (Scheduler) mainSchedulerProvider.get(), (ProgressConnector) progressConnectorProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (IdologyRetryConnector) idologyRetryConnectorProvider.get(), (IdologyVerificationConnector) idologyVerificationConnectorProvider.get(), (IdologyFormErrorMatcher) idologyFormErrorMatcherProvider.get(), (String) idologyTrackingContextProvider.get());
    }

    public static IdologyForm_Factory create(Provider<Application> appProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<LoginManager> loginManagerProvider, Provider<Scheduler> mainSchedulerProvider, Provider<ProgressConnector> progressConnectorProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<IdologyRetryConnector> idologyRetryConnectorProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<IdologyFormErrorMatcher> idologyFormErrorMatcherProvider, Provider<String> idologyTrackingContextProvider) {
        return new IdologyForm_Factory(appProvider, mixpanelTrackingProvider, loginManagerProvider, mainSchedulerProvider, progressConnectorProvider, snackBarWrapperProvider, idologyRetryConnectorProvider, idologyVerificationConnectorProvider, idologyFormErrorMatcherProvider, idologyTrackingContextProvider);
    }

    public static IdologyForm newIdologyForm(Application app, MixpanelTracking mixpanelTracking, LoginManager loginManager, Scheduler mainScheduler, ProgressConnector progressConnector, SnackBarWrapper snackBarWrapper, IdologyRetryConnector idologyRetryConnector, IdologyVerificationConnector idologyVerificationConnector, IdologyFormErrorMatcher idologyFormErrorMatcher, String idologyTrackingContext) {
        return new IdologyForm(app, mixpanelTracking, loginManager, mainScheduler, progressConnector, snackBarWrapper, idologyRetryConnector, idologyVerificationConnector, idologyFormErrorMatcher, idologyTrackingContext);
    }
}
