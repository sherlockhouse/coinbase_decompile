package com.coinbase.android.idology;

import android.app.Application;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.GoogleApiClientWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;
import rx.functions.Func0;

public final class IdologyAddressFormPresenter_Factory implements Factory<IdologyAddressFormPresenter> {
    private final Provider<Application> appProvider;
    private final Provider<Func0<GoogleApiClientWrapper>> googleApiClientFuncProvider;
    private final Provider<IdologyAutoCompleteShownConnector> idologyAutoCompleteShownConnectorProvider;
    private final Provider<IdologyFormErrorMatcher> idologyFormErrorMatcherProvider;
    private final Provider<IdologyForm> idologyFormProvider;
    private final Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider;
    private final Provider<String> idologyTrackingContextProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<IdologyAddressFormScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;

    public IdologyAddressFormPresenter_Factory(Provider<Application> appProvider, Provider<LoginManager> loginManagerProvider, Provider<IdologyAddressFormScreen> screenProvider, Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider, Provider<IdologyForm> idologyFormProvider, Provider<IdologyFormErrorMatcher> idologyFormErrorMatcherProvider, Provider<IdologyAutoCompleteShownConnector> idologyAutoCompleteShownConnectorProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<String> idologyTrackingContextProvider, Provider<Func0<GoogleApiClientWrapper>> googleApiClientFuncProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.appProvider = appProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.screenProvider = screenProvider;
        this.idologyFormValidConnectorProvider = idologyFormValidConnectorProvider;
        this.idologyFormProvider = idologyFormProvider;
        this.idologyFormErrorMatcherProvider = idologyFormErrorMatcherProvider;
        this.idologyAutoCompleteShownConnectorProvider = idologyAutoCompleteShownConnectorProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.idologyTrackingContextProvider = idologyTrackingContextProvider;
        this.googleApiClientFuncProvider = googleApiClientFuncProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public IdologyAddressFormPresenter get() {
        return provideInstance(this.appProvider, this.loginManagerProvider, this.screenProvider, this.idologyFormValidConnectorProvider, this.idologyFormProvider, this.idologyFormErrorMatcherProvider, this.idologyAutoCompleteShownConnectorProvider, this.snackBarWrapperProvider, this.mixpanelTrackingProvider, this.idologyTrackingContextProvider, this.googleApiClientFuncProvider, this.mainSchedulerProvider);
    }

    public static IdologyAddressFormPresenter provideInstance(Provider<Application> appProvider, Provider<LoginManager> loginManagerProvider, Provider<IdologyAddressFormScreen> screenProvider, Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider, Provider<IdologyForm> idologyFormProvider, Provider<IdologyFormErrorMatcher> idologyFormErrorMatcherProvider, Provider<IdologyAutoCompleteShownConnector> idologyAutoCompleteShownConnectorProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<String> idologyTrackingContextProvider, Provider<Func0<GoogleApiClientWrapper>> googleApiClientFuncProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new IdologyAddressFormPresenter((Application) appProvider.get(), (LoginManager) loginManagerProvider.get(), (IdologyAddressFormScreen) screenProvider.get(), (IdologyFormValidConnector) idologyFormValidConnectorProvider.get(), (IdologyForm) idologyFormProvider.get(), (IdologyFormErrorMatcher) idologyFormErrorMatcherProvider.get(), (IdologyAutoCompleteShownConnector) idologyAutoCompleteShownConnectorProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (String) idologyTrackingContextProvider.get(), (Func0) googleApiClientFuncProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static IdologyAddressFormPresenter_Factory create(Provider<Application> appProvider, Provider<LoginManager> loginManagerProvider, Provider<IdologyAddressFormScreen> screenProvider, Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider, Provider<IdologyForm> idologyFormProvider, Provider<IdologyFormErrorMatcher> idologyFormErrorMatcherProvider, Provider<IdologyAutoCompleteShownConnector> idologyAutoCompleteShownConnectorProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<String> idologyTrackingContextProvider, Provider<Func0<GoogleApiClientWrapper>> googleApiClientFuncProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new IdologyAddressFormPresenter_Factory(appProvider, loginManagerProvider, screenProvider, idologyFormValidConnectorProvider, idologyFormProvider, idologyFormErrorMatcherProvider, idologyAutoCompleteShownConnectorProvider, snackBarWrapperProvider, mixpanelTrackingProvider, idologyTrackingContextProvider, googleApiClientFuncProvider, mainSchedulerProvider);
    }

    public static IdologyAddressFormPresenter newIdologyAddressFormPresenter(Application app, LoginManager loginManager, IdologyAddressFormScreen screen, IdologyFormValidConnector idologyFormValidConnector, IdologyForm idologyForm, IdologyFormErrorMatcher idologyFormErrorMatcher, IdologyAutoCompleteShownConnector idologyAutoCompleteShownConnector, SnackBarWrapper snackBarWrapper, MixpanelTracking mixpanelTracking, String idologyTrackingContext, Func0<GoogleApiClientWrapper> googleApiClientFunc, Scheduler mainScheduler) {
        return new IdologyAddressFormPresenter(app, loginManager, screen, idologyFormValidConnector, idologyForm, idologyFormErrorMatcher, idologyAutoCompleteShownConnector, snackBarWrapper, mixpanelTracking, idologyTrackingContext, googleApiClientFunc, mainScheduler);
    }
}
