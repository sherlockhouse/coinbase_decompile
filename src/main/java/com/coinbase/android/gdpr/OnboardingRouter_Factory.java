package com.coinbase.android.gdpr;

import com.coinbase.android.ui.ActionBarController;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class OnboardingRouter_Factory implements Factory<OnboardingRouter> {
    private final Provider<ActionBarController> controllerProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<OnboardingUpdatedConnector> onboardingUpdatedConnectorProvider;

    public OnboardingRouter_Factory(Provider<LoginManager> loginManagerProvider, Provider<ActionBarController> controllerProvider, Provider<OnboardingUpdatedConnector> onboardingUpdatedConnectorProvider) {
        this.loginManagerProvider = loginManagerProvider;
        this.controllerProvider = controllerProvider;
        this.onboardingUpdatedConnectorProvider = onboardingUpdatedConnectorProvider;
    }

    public OnboardingRouter get() {
        return provideInstance(this.loginManagerProvider, this.controllerProvider, this.onboardingUpdatedConnectorProvider);
    }

    public static OnboardingRouter provideInstance(Provider<LoginManager> loginManagerProvider, Provider<ActionBarController> controllerProvider, Provider<OnboardingUpdatedConnector> onboardingUpdatedConnectorProvider) {
        return new OnboardingRouter((LoginManager) loginManagerProvider.get(), (ActionBarController) controllerProvider.get(), (OnboardingUpdatedConnector) onboardingUpdatedConnectorProvider.get());
    }

    public static OnboardingRouter_Factory create(Provider<LoginManager> loginManagerProvider, Provider<ActionBarController> controllerProvider, Provider<OnboardingUpdatedConnector> onboardingUpdatedConnectorProvider) {
        return new OnboardingRouter_Factory(loginManagerProvider, controllerProvider, onboardingUpdatedConnectorProvider);
    }

    public static OnboardingRouter newOnboardingRouter(LoginManager loginManager, ActionBarController controller, OnboardingUpdatedConnector onboardingUpdatedConnector) {
        return new OnboardingRouter(loginManager, controller, onboardingUpdatedConnector);
    }
}
