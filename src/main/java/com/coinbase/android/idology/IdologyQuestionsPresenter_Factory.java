package com.coinbase.android.idology;

import android.app.Application;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class IdologyQuestionsPresenter_Factory implements Factory<IdologyQuestionsPresenter> {
    private final Provider<Application> applicationProvider;
    private final Provider<IdologyAnswerListValidConnector> idologyAnswerListValidConnectorProvider;
    private final Provider<IdologyRetryConnector> idologyRetryConnectorProvider;
    private final Provider<String> idologyTrackingContextProvider;
    private final Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<IdologyQuestionsScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;

    public IdologyQuestionsPresenter_Factory(Provider<Application> applicationProvider, Provider<LoginManager> loginManagerProvider, Provider<IdologyQuestionsScreen> screenProvider, Provider<IdologyAnswerListValidConnector> idologyAnswerListValidConnectorProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<IdologyRetryConnector> idologyRetryConnectorProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<String> idologyTrackingContextProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.applicationProvider = applicationProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.screenProvider = screenProvider;
        this.idologyAnswerListValidConnectorProvider = idologyAnswerListValidConnectorProvider;
        this.idologyVerificationConnectorProvider = idologyVerificationConnectorProvider;
        this.idologyRetryConnectorProvider = idologyRetryConnectorProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.idologyTrackingContextProvider = idologyTrackingContextProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public IdologyQuestionsPresenter get() {
        return provideInstance(this.applicationProvider, this.loginManagerProvider, this.screenProvider, this.idologyAnswerListValidConnectorProvider, this.idologyVerificationConnectorProvider, this.idologyRetryConnectorProvider, this.snackBarWrapperProvider, this.mixpanelTrackingProvider, this.idologyTrackingContextProvider, this.mainSchedulerProvider);
    }

    public static IdologyQuestionsPresenter provideInstance(Provider<Application> applicationProvider, Provider<LoginManager> loginManagerProvider, Provider<IdologyQuestionsScreen> screenProvider, Provider<IdologyAnswerListValidConnector> idologyAnswerListValidConnectorProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<IdologyRetryConnector> idologyRetryConnectorProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<String> idologyTrackingContextProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new IdologyQuestionsPresenter((Application) applicationProvider.get(), (LoginManager) loginManagerProvider.get(), (IdologyQuestionsScreen) screenProvider.get(), (IdologyAnswerListValidConnector) idologyAnswerListValidConnectorProvider.get(), (IdologyVerificationConnector) idologyVerificationConnectorProvider.get(), (IdologyRetryConnector) idologyRetryConnectorProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (String) idologyTrackingContextProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static IdologyQuestionsPresenter_Factory create(Provider<Application> applicationProvider, Provider<LoginManager> loginManagerProvider, Provider<IdologyQuestionsScreen> screenProvider, Provider<IdologyAnswerListValidConnector> idologyAnswerListValidConnectorProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<IdologyRetryConnector> idologyRetryConnectorProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<String> idologyTrackingContextProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new IdologyQuestionsPresenter_Factory(applicationProvider, loginManagerProvider, screenProvider, idologyAnswerListValidConnectorProvider, idologyVerificationConnectorProvider, idologyRetryConnectorProvider, snackBarWrapperProvider, mixpanelTrackingProvider, idologyTrackingContextProvider, mainSchedulerProvider);
    }

    public static IdologyQuestionsPresenter newIdologyQuestionsPresenter(Application application, LoginManager loginManager, IdologyQuestionsScreen screen, IdologyAnswerListValidConnector idologyAnswerListValidConnector, IdologyVerificationConnector idologyVerificationConnector, IdologyRetryConnector idologyRetryConnector, SnackBarWrapper snackBarWrapper, MixpanelTracking mixpanelTracking, String idologyTrackingContext, Scheduler mainScheduler) {
        return new IdologyQuestionsPresenter(application, loginManager, screen, idologyAnswerListValidConnector, idologyVerificationConnector, idologyRetryConnector, snackBarWrapper, mixpanelTracking, idologyTrackingContext, mainScheduler);
    }
}
