package com.coinbase.android.signin;

import android.app.Application;
import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class TwoFactorRouter_Factory implements Factory<TwoFactorRouter> {
    private final Provider<Application> appProvider;
    private final Provider<AuthRouter> authRouterProvider;
    private final Provider<Scheduler> backgroundSchedulerProvider;
    private final Provider<ActionBarController> controllerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;

    public TwoFactorRouter_Factory(Provider<ActionBarController> controllerProvider, Provider<Application> appProvider, Provider<AuthRouter> authRouterProvider, Provider<Scheduler> backgroundSchedulerProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.controllerProvider = controllerProvider;
        this.appProvider = appProvider;
        this.authRouterProvider = authRouterProvider;
        this.backgroundSchedulerProvider = backgroundSchedulerProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public TwoFactorRouter get() {
        return provideInstance(this.controllerProvider, this.appProvider, this.authRouterProvider, this.backgroundSchedulerProvider, this.mainSchedulerProvider);
    }

    public static TwoFactorRouter provideInstance(Provider<ActionBarController> controllerProvider, Provider<Application> appProvider, Provider<AuthRouter> authRouterProvider, Provider<Scheduler> backgroundSchedulerProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new TwoFactorRouter((ActionBarController) controllerProvider.get(), (Application) appProvider.get(), (AuthRouter) authRouterProvider.get(), (Scheduler) backgroundSchedulerProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static TwoFactorRouter_Factory create(Provider<ActionBarController> controllerProvider, Provider<Application> appProvider, Provider<AuthRouter> authRouterProvider, Provider<Scheduler> backgroundSchedulerProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new TwoFactorRouter_Factory(controllerProvider, appProvider, authRouterProvider, backgroundSchedulerProvider, mainSchedulerProvider);
    }

    public static TwoFactorRouter newTwoFactorRouter(ActionBarController controller, Application app, AuthRouter authRouter, Scheduler backgroundScheduler, Scheduler mainScheduler) {
        return new TwoFactorRouter(controller, app, authRouter, backgroundScheduler, mainScheduler);
    }
}
