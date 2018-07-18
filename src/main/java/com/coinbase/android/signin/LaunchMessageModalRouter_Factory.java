package com.coinbase.android.signin;

import android.support.v7.app.AppCompatActivity;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class LaunchMessageModalRouter_Factory implements Factory<LaunchMessageModalRouter> {
    private final Provider<AppCompatActivity> activityProvider;
    private final Provider<LoginManager> loginManagerProvider;

    public LaunchMessageModalRouter_Factory(Provider<AppCompatActivity> activityProvider, Provider<LoginManager> loginManagerProvider) {
        this.activityProvider = activityProvider;
        this.loginManagerProvider = loginManagerProvider;
    }

    public LaunchMessageModalRouter get() {
        return provideInstance(this.activityProvider, this.loginManagerProvider);
    }

    public static LaunchMessageModalRouter provideInstance(Provider<AppCompatActivity> activityProvider, Provider<LoginManager> loginManagerProvider) {
        return new LaunchMessageModalRouter((AppCompatActivity) activityProvider.get(), (LoginManager) loginManagerProvider.get());
    }

    public static LaunchMessageModalRouter_Factory create(Provider<AppCompatActivity> activityProvider, Provider<LoginManager> loginManagerProvider) {
        return new LaunchMessageModalRouter_Factory(activityProvider, loginManagerProvider);
    }

    public static LaunchMessageModalRouter newLaunchMessageModalRouter(AppCompatActivity activity, LoginManager loginManager) {
        return new LaunchMessageModalRouter(activity, loginManager);
    }
}
