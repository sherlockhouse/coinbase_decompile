package com.coinbase.android.idology;

import android.app.Application;
import android.content.SharedPreferences;
import com.coinbase.android.ui.DatePickerConnector;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class IdologyNameFormPresenter_Factory implements Factory<IdologyNameFormPresenter> {
    private final Provider<Application> applicationProvider;
    private final Provider<DatePickerConnector> datePickerConnectorProvider;
    private final Provider<IdologyFormErrorMatcher> idologyFormErrorMatcherProvider;
    private final Provider<IdologyForm> idologyFormProvider;
    private final Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider;
    private final Provider<String> idologyTrackingContextProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<IdologyNameFormScreen> screenProvider;
    private final Provider<SharedPreferences> sharedPrefsProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;

    public IdologyNameFormPresenter_Factory(Provider<Application> applicationProvider, Provider<LoginManager> loginManagerProvider, Provider<IdologyNameFormScreen> screenProvider, Provider<SharedPreferences> sharedPrefsProvider, Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider, Provider<IdologyForm> idologyFormProvider, Provider<IdologyFormErrorMatcher> idologyFormErrorMatcherProvider, Provider<DatePickerConnector> datePickerConnectorProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<String> idologyTrackingContextProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.applicationProvider = applicationProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.screenProvider = screenProvider;
        this.sharedPrefsProvider = sharedPrefsProvider;
        this.idologyFormValidConnectorProvider = idologyFormValidConnectorProvider;
        this.idologyFormProvider = idologyFormProvider;
        this.idologyFormErrorMatcherProvider = idologyFormErrorMatcherProvider;
        this.datePickerConnectorProvider = datePickerConnectorProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.idologyTrackingContextProvider = idologyTrackingContextProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public IdologyNameFormPresenter get() {
        return provideInstance(this.applicationProvider, this.loginManagerProvider, this.screenProvider, this.sharedPrefsProvider, this.idologyFormValidConnectorProvider, this.idologyFormProvider, this.idologyFormErrorMatcherProvider, this.datePickerConnectorProvider, this.snackBarWrapperProvider, this.mixpanelTrackingProvider, this.idologyTrackingContextProvider, this.mainSchedulerProvider);
    }

    public static IdologyNameFormPresenter provideInstance(Provider<Application> applicationProvider, Provider<LoginManager> loginManagerProvider, Provider<IdologyNameFormScreen> screenProvider, Provider<SharedPreferences> sharedPrefsProvider, Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider, Provider<IdologyForm> idologyFormProvider, Provider<IdologyFormErrorMatcher> idologyFormErrorMatcherProvider, Provider<DatePickerConnector> datePickerConnectorProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<String> idologyTrackingContextProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new IdologyNameFormPresenter((Application) applicationProvider.get(), (LoginManager) loginManagerProvider.get(), (IdologyNameFormScreen) screenProvider.get(), (SharedPreferences) sharedPrefsProvider.get(), (IdologyFormValidConnector) idologyFormValidConnectorProvider.get(), (IdologyForm) idologyFormProvider.get(), (IdologyFormErrorMatcher) idologyFormErrorMatcherProvider.get(), (DatePickerConnector) datePickerConnectorProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (String) idologyTrackingContextProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static IdologyNameFormPresenter_Factory create(Provider<Application> applicationProvider, Provider<LoginManager> loginManagerProvider, Provider<IdologyNameFormScreen> screenProvider, Provider<SharedPreferences> sharedPrefsProvider, Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider, Provider<IdologyForm> idologyFormProvider, Provider<IdologyFormErrorMatcher> idologyFormErrorMatcherProvider, Provider<DatePickerConnector> datePickerConnectorProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<String> idologyTrackingContextProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new IdologyNameFormPresenter_Factory(applicationProvider, loginManagerProvider, screenProvider, sharedPrefsProvider, idologyFormValidConnectorProvider, idologyFormProvider, idologyFormErrorMatcherProvider, datePickerConnectorProvider, snackBarWrapperProvider, mixpanelTrackingProvider, idologyTrackingContextProvider, mainSchedulerProvider);
    }

    public static IdologyNameFormPresenter newIdologyNameFormPresenter(Application application, LoginManager loginManager, IdologyNameFormScreen screen, SharedPreferences sharedPrefs, IdologyFormValidConnector idologyFormValidConnector, IdologyForm idologyForm, IdologyFormErrorMatcher idologyFormErrorMatcher, DatePickerConnector datePickerConnector, SnackBarWrapper snackBarWrapper, MixpanelTracking mixpanelTracking, String idologyTrackingContext, Scheduler mainScheduler) {
        return new IdologyNameFormPresenter(application, loginManager, screen, sharedPrefs, idologyFormValidConnector, idologyForm, idologyFormErrorMatcher, datePickerConnector, snackBarWrapper, mixpanelTracking, idologyTrackingContext, mainScheduler);
    }
}
