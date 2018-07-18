package com.coinbase.android.signin;

import android.support.v7.app.AppCompatActivity;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class EmailVerifiedRouter_Factory implements Factory<EmailVerifiedRouter> {
    private final Provider<AppCompatActivity> activityProvider;

    public EmailVerifiedRouter_Factory(Provider<AppCompatActivity> activityProvider) {
        this.activityProvider = activityProvider;
    }

    public EmailVerifiedRouter get() {
        return provideInstance(this.activityProvider);
    }

    public static EmailVerifiedRouter provideInstance(Provider<AppCompatActivity> activityProvider) {
        return new EmailVerifiedRouter((AppCompatActivity) activityProvider.get());
    }

    public static EmailVerifiedRouter_Factory create(Provider<AppCompatActivity> activityProvider) {
        return new EmailVerifiedRouter_Factory(activityProvider);
    }

    public static EmailVerifiedRouter newEmailVerifiedRouter(AppCompatActivity activity) {
        return new EmailVerifiedRouter(activity);
    }
}
