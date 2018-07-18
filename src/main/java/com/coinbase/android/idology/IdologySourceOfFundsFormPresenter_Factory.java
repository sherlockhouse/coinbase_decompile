package com.coinbase.android.idology;

import com.coinbase.android.signin.IdologyOptionSelectedConnector;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class IdologySourceOfFundsFormPresenter_Factory implements Factory<IdologySourceOfFundsFormPresenter> {
    private final Provider<Scheduler> backgroundSchedulerProvider;
    private final Provider<IdologyFormErrorMatcher> idologyFormErrorMatcherProvider;
    private final Provider<IdologyForm> idologyFormProvider;
    private final Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider;
    private final Provider<IdologyOptionSelectedConnector> idologyOptionSelectedConnectorProvider;
    private final Provider<String> idologyTrackingContextProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<ProgressConnector> progressConnectorProvider;
    private final Provider<IdologySourceOfFundsFormScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;

    public IdologySourceOfFundsFormPresenter_Factory(Provider<LoginManager> loginManagerProvider, Provider<IdologySourceOfFundsFormScreen> screenProvider, Provider<IdologyForm> idologyFormProvider, Provider<IdologyFormErrorMatcher> idologyFormErrorMatcherProvider, Provider<IdologyOptionSelectedConnector> idologyOptionSelectedConnectorProvider, Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider, Provider<ProgressConnector> progressConnectorProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<String> idologyTrackingContextProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        this.loginManagerProvider = loginManagerProvider;
        this.screenProvider = screenProvider;
        this.idologyFormProvider = idologyFormProvider;
        this.idologyFormErrorMatcherProvider = idologyFormErrorMatcherProvider;
        this.idologyOptionSelectedConnectorProvider = idologyOptionSelectedConnectorProvider;
        this.idologyFormValidConnectorProvider = idologyFormValidConnectorProvider;
        this.progressConnectorProvider = progressConnectorProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.idologyTrackingContextProvider = idologyTrackingContextProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
        this.backgroundSchedulerProvider = backgroundSchedulerProvider;
    }

    public IdologySourceOfFundsFormPresenter get() {
        return provideInstance(this.loginManagerProvider, this.screenProvider, this.idologyFormProvider, this.idologyFormErrorMatcherProvider, this.idologyOptionSelectedConnectorProvider, this.idologyFormValidConnectorProvider, this.progressConnectorProvider, this.snackBarWrapperProvider, this.mixpanelTrackingProvider, this.idologyTrackingContextProvider, this.mainSchedulerProvider, this.backgroundSchedulerProvider);
    }

    public static IdologySourceOfFundsFormPresenter provideInstance(Provider<LoginManager> loginManagerProvider, Provider<IdologySourceOfFundsFormScreen> screenProvider, Provider<IdologyForm> idologyFormProvider, Provider<IdologyFormErrorMatcher> idologyFormErrorMatcherProvider, Provider<IdologyOptionSelectedConnector> idologyOptionSelectedConnectorProvider, Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider, Provider<ProgressConnector> progressConnectorProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<String> idologyTrackingContextProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new IdologySourceOfFundsFormPresenter((LoginManager) loginManagerProvider.get(), (IdologySourceOfFundsFormScreen) screenProvider.get(), (IdologyForm) idologyFormProvider.get(), (IdologyFormErrorMatcher) idologyFormErrorMatcherProvider.get(), (IdologyOptionSelectedConnector) idologyOptionSelectedConnectorProvider.get(), (IdologyFormValidConnector) idologyFormValidConnectorProvider.get(), (ProgressConnector) progressConnectorProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (String) idologyTrackingContextProvider.get(), (Scheduler) mainSchedulerProvider.get(), (Scheduler) backgroundSchedulerProvider.get());
    }

    public static IdologySourceOfFundsFormPresenter_Factory create(Provider<LoginManager> loginManagerProvider, Provider<IdologySourceOfFundsFormScreen> screenProvider, Provider<IdologyForm> idologyFormProvider, Provider<IdologyFormErrorMatcher> idologyFormErrorMatcherProvider, Provider<IdologyOptionSelectedConnector> idologyOptionSelectedConnectorProvider, Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider, Provider<ProgressConnector> progressConnectorProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<String> idologyTrackingContextProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new IdologySourceOfFundsFormPresenter_Factory(loginManagerProvider, screenProvider, idologyFormProvider, idologyFormErrorMatcherProvider, idologyOptionSelectedConnectorProvider, idologyFormValidConnectorProvider, progressConnectorProvider, snackBarWrapperProvider, mixpanelTrackingProvider, idologyTrackingContextProvider, mainSchedulerProvider, backgroundSchedulerProvider);
    }

    public static IdologySourceOfFundsFormPresenter newIdologySourceOfFundsFormPresenter(LoginManager loginManager, IdologySourceOfFundsFormScreen screen, IdologyForm idologyForm, IdologyFormErrorMatcher idologyFormErrorMatcher, IdologyOptionSelectedConnector idologyOptionSelectedConnector, IdologyFormValidConnector idologyFormValidConnector, ProgressConnector progressConnector, SnackBarWrapper snackBarWrapper, MixpanelTracking mixpanelTracking, String idologyTrackingContext, Scheduler mainScheduler, Scheduler backgroundScheduler) {
        return new IdologySourceOfFundsFormPresenter(loginManager, screen, idologyForm, idologyFormErrorMatcher, idologyOptionSelectedConnector, idologyFormValidConnector, progressConnector, snackBarWrapper, mixpanelTracking, idologyTrackingContext, mainScheduler, backgroundScheduler);
    }
}
