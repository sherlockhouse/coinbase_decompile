package com.coinbase.android.signin;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class DeviceVerifyPresenter_Factory implements Factory<DeviceVerifyPresenter> {
    private final Provider<AppCompatActivity> activityProvider;
    private final Provider<Application> appProvider;
    private final Provider<AuthRouter> authRouterProvider;
    private final Provider<DeviceVerifyConnector> deviceVerifyConnectorProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<DeviceVerifyRouter> routerProvider;
    private final Provider<DeviceVerifyScreen> screenProvider;
    private final Provider<SignInRouter> signInRouterProvider;

    public DeviceVerifyPresenter_Factory(Provider<DeviceVerifyScreen> screenProvider, Provider<DeviceVerifyRouter> routerProvider, Provider<AuthRouter> authRouterProvider, Provider<SignInRouter> signInRouterProvider, Provider<AppCompatActivity> activityProvider, Provider<Application> appProvider, Provider<DeviceVerifyConnector> deviceVerifyConnectorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.screenProvider = screenProvider;
        this.routerProvider = routerProvider;
        this.authRouterProvider = authRouterProvider;
        this.signInRouterProvider = signInRouterProvider;
        this.activityProvider = activityProvider;
        this.appProvider = appProvider;
        this.deviceVerifyConnectorProvider = deviceVerifyConnectorProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public DeviceVerifyPresenter get() {
        return provideInstance(this.screenProvider, this.routerProvider, this.authRouterProvider, this.signInRouterProvider, this.activityProvider, this.appProvider, this.deviceVerifyConnectorProvider, this.mixpanelTrackingProvider, this.mainSchedulerProvider);
    }

    public static DeviceVerifyPresenter provideInstance(Provider<DeviceVerifyScreen> screenProvider, Provider<DeviceVerifyRouter> routerProvider, Provider<AuthRouter> authRouterProvider, Provider<SignInRouter> signInRouterProvider, Provider<AppCompatActivity> activityProvider, Provider<Application> appProvider, Provider<DeviceVerifyConnector> deviceVerifyConnectorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new DeviceVerifyPresenter((DeviceVerifyScreen) screenProvider.get(), (DeviceVerifyRouter) routerProvider.get(), (AuthRouter) authRouterProvider.get(), (SignInRouter) signInRouterProvider.get(), (AppCompatActivity) activityProvider.get(), (Application) appProvider.get(), (DeviceVerifyConnector) deviceVerifyConnectorProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static DeviceVerifyPresenter_Factory create(Provider<DeviceVerifyScreen> screenProvider, Provider<DeviceVerifyRouter> routerProvider, Provider<AuthRouter> authRouterProvider, Provider<SignInRouter> signInRouterProvider, Provider<AppCompatActivity> activityProvider, Provider<Application> appProvider, Provider<DeviceVerifyConnector> deviceVerifyConnectorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new DeviceVerifyPresenter_Factory(screenProvider, routerProvider, authRouterProvider, signInRouterProvider, activityProvider, appProvider, deviceVerifyConnectorProvider, mixpanelTrackingProvider, mainSchedulerProvider);
    }

    public static DeviceVerifyPresenter newDeviceVerifyPresenter(DeviceVerifyScreen screen, DeviceVerifyRouter router, AuthRouter authRouter, SignInRouter signInRouter, AppCompatActivity activity, Application app, DeviceVerifyConnector deviceVerifyConnector, MixpanelTracking mixpanelTracking, Scheduler mainScheduler) {
        return new DeviceVerifyPresenter(screen, router, authRouter, signInRouter, activity, app, deviceVerifyConnector, mixpanelTracking, mainScheduler);
    }
}
