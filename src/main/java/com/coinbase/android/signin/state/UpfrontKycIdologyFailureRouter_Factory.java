package com.coinbase.android.signin.state;

import com.coinbase.android.settings.idology.IdologyArgsBuilder;
import com.coinbase.android.signin.AuthManager;
import com.coinbase.android.signin.AuthRouter;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class UpfrontKycIdologyFailureRouter_Factory implements Factory<UpfrontKycIdologyFailureRouter> {
    private final Provider<AuthManager> authManagerProvider;
    private final Provider<AuthRouter> authRouterProvider;
    private final Provider<ActionBarController> controllerProvider;
    private final Provider<IdologyArgsBuilder> idologyArgsBuilderProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<SplitTesting> splitTestingProvider;

    public UpfrontKycIdologyFailureRouter_Factory(Provider<ActionBarController> controllerProvider, Provider<IdologyArgsBuilder> idologyArgsBuilderProvider, Provider<LoginManager> loginManagerProvider, Provider<AuthManager> authManagerProvider, Provider<SplitTesting> splitTestingProvider, Provider<AuthRouter> authRouterProvider) {
        this.controllerProvider = controllerProvider;
        this.idologyArgsBuilderProvider = idologyArgsBuilderProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.authManagerProvider = authManagerProvider;
        this.splitTestingProvider = splitTestingProvider;
        this.authRouterProvider = authRouterProvider;
    }

    public UpfrontKycIdologyFailureRouter get() {
        return provideInstance(this.controllerProvider, this.idologyArgsBuilderProvider, this.loginManagerProvider, this.authManagerProvider, this.splitTestingProvider, this.authRouterProvider);
    }

    public static UpfrontKycIdologyFailureRouter provideInstance(Provider<ActionBarController> controllerProvider, Provider<IdologyArgsBuilder> idologyArgsBuilderProvider, Provider<LoginManager> loginManagerProvider, Provider<AuthManager> authManagerProvider, Provider<SplitTesting> splitTestingProvider, Provider<AuthRouter> authRouterProvider) {
        return new UpfrontKycIdologyFailureRouter((ActionBarController) controllerProvider.get(), (IdologyArgsBuilder) idologyArgsBuilderProvider.get(), (LoginManager) loginManagerProvider.get(), (AuthManager) authManagerProvider.get(), (SplitTesting) splitTestingProvider.get(), (AuthRouter) authRouterProvider.get());
    }

    public static UpfrontKycIdologyFailureRouter_Factory create(Provider<ActionBarController> controllerProvider, Provider<IdologyArgsBuilder> idologyArgsBuilderProvider, Provider<LoginManager> loginManagerProvider, Provider<AuthManager> authManagerProvider, Provider<SplitTesting> splitTestingProvider, Provider<AuthRouter> authRouterProvider) {
        return new UpfrontKycIdologyFailureRouter_Factory(controllerProvider, idologyArgsBuilderProvider, loginManagerProvider, authManagerProvider, splitTestingProvider, authRouterProvider);
    }

    public static UpfrontKycIdologyFailureRouter newUpfrontKycIdologyFailureRouter(ActionBarController controller, IdologyArgsBuilder idologyArgsBuilder, LoginManager loginManager, AuthManager authManager, SplitTesting splitTesting, AuthRouter authRouter) {
        return new UpfrontKycIdologyFailureRouter(controller, idologyArgsBuilder, loginManager, authManager, splitTesting, authRouter);
    }
}
