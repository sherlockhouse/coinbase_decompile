package com.coinbase.android.signin;

import android.support.v7.app.AppCompatActivity;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.utils.ActivityPermissionCheckUtils;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class AuthRouter_Factory implements Factory<AuthRouter> {
    private final Provider<AppCompatActivity> appCompatActivityProvider;
    private final Provider<AuthCompletionFactory> authCompletionFactoryProvider;
    private final Provider<AuthManager> authManagerProvider;
    private final Provider<ActionBarController> controllerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<ActivityPermissionCheckUtils> permissionCheckUtilsProvider;

    public AuthRouter_Factory(Provider<ActionBarController> controllerProvider, Provider<AuthManager> authManagerProvider, Provider<ActivityPermissionCheckUtils> permissionCheckUtilsProvider, Provider<AppCompatActivity> appCompatActivityProvider, Provider<AuthCompletionFactory> authCompletionFactoryProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.controllerProvider = controllerProvider;
        this.authManagerProvider = authManagerProvider;
        this.permissionCheckUtilsProvider = permissionCheckUtilsProvider;
        this.appCompatActivityProvider = appCompatActivityProvider;
        this.authCompletionFactoryProvider = authCompletionFactoryProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public AuthRouter get() {
        return provideInstance(this.controllerProvider, this.authManagerProvider, this.permissionCheckUtilsProvider, this.appCompatActivityProvider, this.authCompletionFactoryProvider, this.mainSchedulerProvider);
    }

    public static AuthRouter provideInstance(Provider<ActionBarController> controllerProvider, Provider<AuthManager> authManagerProvider, Provider<ActivityPermissionCheckUtils> permissionCheckUtilsProvider, Provider<AppCompatActivity> appCompatActivityProvider, Provider<AuthCompletionFactory> authCompletionFactoryProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new AuthRouter((ActionBarController) controllerProvider.get(), (AuthManager) authManagerProvider.get(), (ActivityPermissionCheckUtils) permissionCheckUtilsProvider.get(), (AppCompatActivity) appCompatActivityProvider.get(), (AuthCompletionFactory) authCompletionFactoryProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static AuthRouter_Factory create(Provider<ActionBarController> controllerProvider, Provider<AuthManager> authManagerProvider, Provider<ActivityPermissionCheckUtils> permissionCheckUtilsProvider, Provider<AppCompatActivity> appCompatActivityProvider, Provider<AuthCompletionFactory> authCompletionFactoryProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new AuthRouter_Factory(controllerProvider, authManagerProvider, permissionCheckUtilsProvider, appCompatActivityProvider, authCompletionFactoryProvider, mainSchedulerProvider);
    }

    public static AuthRouter newAuthRouter(ActionBarController controller, AuthManager authManager, ActivityPermissionCheckUtils permissionCheckUtils, AppCompatActivity appCompatActivity, AuthCompletionFactory authCompletionFactory, Scheduler mainScheduler) {
        return new AuthRouter(controller, authManager, permissionCheckUtils, appCompatActivity, authCompletionFactory, mainScheduler);
    }
}
