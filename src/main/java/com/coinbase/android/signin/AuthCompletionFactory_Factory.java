package com.coinbase.android.signin;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class AuthCompletionFactory_Factory implements Factory<AuthCompletionFactory> {
    private final Provider<ActionBarController> actionBarControllerProvider;
    private final Provider<AppCompatActivity> appCompatActivityProvider;
    private final Provider<AuthManager> authManagerProvider;
    private final Provider<CredentialsApiRxWrapper> credentialsApiRxWrapperProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;

    public AuthCompletionFactory_Factory(Provider<ActionBarController> actionBarControllerProvider, Provider<SharedPreferences> sharedPreferencesProvider, Provider<AppCompatActivity> appCompatActivityProvider, Provider<CredentialsApiRxWrapper> credentialsApiRxWrapperProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> mainSchedulerProvider, Provider<AuthManager> authManagerProvider) {
        this.actionBarControllerProvider = actionBarControllerProvider;
        this.sharedPreferencesProvider = sharedPreferencesProvider;
        this.appCompatActivityProvider = appCompatActivityProvider;
        this.credentialsApiRxWrapperProvider = credentialsApiRxWrapperProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
        this.authManagerProvider = authManagerProvider;
    }

    public AuthCompletionFactory get() {
        return provideInstance(this.actionBarControllerProvider, this.sharedPreferencesProvider, this.appCompatActivityProvider, this.credentialsApiRxWrapperProvider, this.mixpanelTrackingProvider, this.mainSchedulerProvider, this.authManagerProvider);
    }

    public static AuthCompletionFactory provideInstance(Provider<ActionBarController> actionBarControllerProvider, Provider<SharedPreferences> sharedPreferencesProvider, Provider<AppCompatActivity> appCompatActivityProvider, Provider<CredentialsApiRxWrapper> credentialsApiRxWrapperProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> mainSchedulerProvider, Provider<AuthManager> authManagerProvider) {
        return new AuthCompletionFactory((ActionBarController) actionBarControllerProvider.get(), (SharedPreferences) sharedPreferencesProvider.get(), (AppCompatActivity) appCompatActivityProvider.get(), (CredentialsApiRxWrapper) credentialsApiRxWrapperProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (Scheduler) mainSchedulerProvider.get(), (AuthManager) authManagerProvider.get());
    }

    public static AuthCompletionFactory_Factory create(Provider<ActionBarController> actionBarControllerProvider, Provider<SharedPreferences> sharedPreferencesProvider, Provider<AppCompatActivity> appCompatActivityProvider, Provider<CredentialsApiRxWrapper> credentialsApiRxWrapperProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> mainSchedulerProvider, Provider<AuthManager> authManagerProvider) {
        return new AuthCompletionFactory_Factory(actionBarControllerProvider, sharedPreferencesProvider, appCompatActivityProvider, credentialsApiRxWrapperProvider, mixpanelTrackingProvider, mainSchedulerProvider, authManagerProvider);
    }

    public static AuthCompletionFactory newAuthCompletionFactory(ActionBarController actionBarController, SharedPreferences sharedPreferences, AppCompatActivity appCompatActivity, CredentialsApiRxWrapper credentialsApiRxWrapper, MixpanelTracking mixpanelTracking, Scheduler mainScheduler, AuthManager authManager) {
        return new AuthCompletionFactory(actionBarController, sharedPreferences, appCompatActivity, credentialsApiRxWrapper, mixpanelTracking, mainScheduler, authManager);
    }
}
