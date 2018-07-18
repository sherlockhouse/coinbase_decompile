package com.coinbase.android.signin;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class CredentialsApiRxWrapper_Factory implements Factory<CredentialsApiRxWrapper> {
    private final Provider<AppCompatActivity> activityProvider;
    private final Provider<Application> appProvider;

    public CredentialsApiRxWrapper_Factory(Provider<AppCompatActivity> activityProvider, Provider<Application> appProvider) {
        this.activityProvider = activityProvider;
        this.appProvider = appProvider;
    }

    public CredentialsApiRxWrapper get() {
        return provideInstance(this.activityProvider, this.appProvider);
    }

    public static CredentialsApiRxWrapper provideInstance(Provider<AppCompatActivity> activityProvider, Provider<Application> appProvider) {
        return new CredentialsApiRxWrapper((AppCompatActivity) activityProvider.get(), (Application) appProvider.get());
    }

    public static CredentialsApiRxWrapper_Factory create(Provider<AppCompatActivity> activityProvider, Provider<Application> appProvider) {
        return new CredentialsApiRxWrapper_Factory(activityProvider, appProvider);
    }

    public static CredentialsApiRxWrapper newCredentialsApiRxWrapper(AppCompatActivity activity, Application app) {
        return new CredentialsApiRxWrapper(activity, app);
    }
}
