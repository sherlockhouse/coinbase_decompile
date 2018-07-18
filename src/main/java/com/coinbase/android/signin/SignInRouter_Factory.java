package com.coinbase.android.signin;

import android.support.v7.app.AppCompatActivity;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class SignInRouter_Factory implements Factory<SignInRouter> {
    private final Provider<ActionBarController> actionBarControllerProvider;
    private final Provider<AppCompatActivity> activityProvider;
    private final Provider<LoginManager> loginManagerProvider;

    public SignInRouter_Factory(Provider<AppCompatActivity> activityProvider, Provider<LoginManager> loginManagerProvider, Provider<ActionBarController> actionBarControllerProvider) {
        this.activityProvider = activityProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.actionBarControllerProvider = actionBarControllerProvider;
    }

    public SignInRouter get() {
        return provideInstance(this.activityProvider, this.loginManagerProvider, this.actionBarControllerProvider);
    }

    public static SignInRouter provideInstance(Provider<AppCompatActivity> activityProvider, Provider<LoginManager> loginManagerProvider, Provider<ActionBarController> actionBarControllerProvider) {
        return new SignInRouter((AppCompatActivity) activityProvider.get(), (LoginManager) loginManagerProvider.get(), (ActionBarController) actionBarControllerProvider.get());
    }

    public static SignInRouter_Factory create(Provider<AppCompatActivity> activityProvider, Provider<LoginManager> loginManagerProvider, Provider<ActionBarController> actionBarControllerProvider) {
        return new SignInRouter_Factory(activityProvider, loginManagerProvider, actionBarControllerProvider);
    }

    public static SignInRouter newSignInRouter(AppCompatActivity activity, LoginManager loginManager, ActionBarController actionBarController) {
        return new SignInRouter(activity, loginManager, actionBarController);
    }
}
