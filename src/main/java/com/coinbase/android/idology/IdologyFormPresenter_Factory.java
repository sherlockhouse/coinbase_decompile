package com.coinbase.android.idology;

import android.app.Application;
import android.content.SharedPreferences;
import com.coinbase.android.signin.IdologyOptionSelectedConnector;
import com.coinbase.android.ui.DatePickerConnector;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class IdologyFormPresenter_Factory implements Factory<IdologyFormPresenter> {
    private final Provider<Application> applicationProvider;
    private final Provider<Scheduler> backgroundSchedulerProvider;
    private final Provider<DatePickerConnector> datePickerConnectorProvider;
    private final Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider;
    private final Provider<IdologyOptionSelectedConnector> idologyOptionSelectedConnectorProvider;
    private final Provider<IdologyRetryConnector> idologyRetryConnectorProvider;
    private final Provider<String> idologyTrackingContextProvider;
    private final Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<IdologyFormScreen> screenProvider;
    private final Provider<SharedPreferences> sharedPrefsProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;

    public IdologyFormPresenter_Factory(Provider<Application> applicationProvider, Provider<LoginManager> loginManagerProvider, Provider<IdologyFormScreen> screenProvider, Provider<SharedPreferences> sharedPrefsProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<IdologyOptionSelectedConnector> idologyOptionSelectedConnectorProvider, Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider, Provider<IdologyRetryConnector> idologyRetryConnectorProvider, Provider<DatePickerConnector> datePickerConnectorProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<String> idologyTrackingContextProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        this.applicationProvider = applicationProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.screenProvider = screenProvider;
        this.sharedPrefsProvider = sharedPrefsProvider;
        this.idologyVerificationConnectorProvider = idologyVerificationConnectorProvider;
        this.idologyOptionSelectedConnectorProvider = idologyOptionSelectedConnectorProvider;
        this.idologyFormValidConnectorProvider = idologyFormValidConnectorProvider;
        this.idologyRetryConnectorProvider = idologyRetryConnectorProvider;
        this.datePickerConnectorProvider = datePickerConnectorProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.idologyTrackingContextProvider = idologyTrackingContextProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
        this.backgroundSchedulerProvider = backgroundSchedulerProvider;
    }

    public IdologyFormPresenter get() {
        return provideInstance(this.applicationProvider, this.loginManagerProvider, this.screenProvider, this.sharedPrefsProvider, this.idologyVerificationConnectorProvider, this.idologyOptionSelectedConnectorProvider, this.idologyFormValidConnectorProvider, this.idologyRetryConnectorProvider, this.datePickerConnectorProvider, this.snackBarWrapperProvider, this.mixpanelTrackingProvider, this.idologyTrackingContextProvider, this.mainSchedulerProvider, this.backgroundSchedulerProvider);
    }

    public static IdologyFormPresenter provideInstance(Provider<Application> applicationProvider, Provider<LoginManager> loginManagerProvider, Provider<IdologyFormScreen> screenProvider, Provider<SharedPreferences> sharedPrefsProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<IdologyOptionSelectedConnector> idologyOptionSelectedConnectorProvider, Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider, Provider<IdologyRetryConnector> idologyRetryConnectorProvider, Provider<DatePickerConnector> datePickerConnectorProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<String> idologyTrackingContextProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new IdologyFormPresenter((Application) applicationProvider.get(), (LoginManager) loginManagerProvider.get(), (IdologyFormScreen) screenProvider.get(), (SharedPreferences) sharedPrefsProvider.get(), (IdologyVerificationConnector) idologyVerificationConnectorProvider.get(), (IdologyOptionSelectedConnector) idologyOptionSelectedConnectorProvider.get(), (IdologyFormValidConnector) idologyFormValidConnectorProvider.get(), (IdologyRetryConnector) idologyRetryConnectorProvider.get(), (DatePickerConnector) datePickerConnectorProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (String) idologyTrackingContextProvider.get(), (Scheduler) mainSchedulerProvider.get(), (Scheduler) backgroundSchedulerProvider.get());
    }

    public static IdologyFormPresenter_Factory create(Provider<Application> applicationProvider, Provider<LoginManager> loginManagerProvider, Provider<IdologyFormScreen> screenProvider, Provider<SharedPreferences> sharedPrefsProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<IdologyOptionSelectedConnector> idologyOptionSelectedConnectorProvider, Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider, Provider<IdologyRetryConnector> idologyRetryConnectorProvider, Provider<DatePickerConnector> datePickerConnectorProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<String> idologyTrackingContextProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new IdologyFormPresenter_Factory(applicationProvider, loginManagerProvider, screenProvider, sharedPrefsProvider, idologyVerificationConnectorProvider, idologyOptionSelectedConnectorProvider, idologyFormValidConnectorProvider, idologyRetryConnectorProvider, datePickerConnectorProvider, snackBarWrapperProvider, mixpanelTrackingProvider, idologyTrackingContextProvider, mainSchedulerProvider, backgroundSchedulerProvider);
    }

    public static IdologyFormPresenter newIdologyFormPresenter(Application application, LoginManager loginManager, IdologyFormScreen screen, SharedPreferences sharedPrefs, IdologyVerificationConnector idologyVerificationConnector, IdologyOptionSelectedConnector idologyOptionSelectedConnector, IdologyFormValidConnector idologyFormValidConnector, IdologyRetryConnector idologyRetryConnector, DatePickerConnector datePickerConnector, SnackBarWrapper snackBarWrapper, MixpanelTracking mixpanelTracking, String idologyTrackingContext, Scheduler mainScheduler, Scheduler backgroundScheduler) {
        return new IdologyFormPresenter(application, loginManager, screen, sharedPrefs, idologyVerificationConnector, idologyOptionSelectedConnector, idologyFormValidConnector, idologyRetryConnector, datePickerConnector, snackBarWrapper, mixpanelTracking, idologyTrackingContext, mainScheduler, backgroundScheduler);
    }
}
