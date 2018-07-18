package com.coinbase.android.signin.state;

import com.coinbase.android.idology.IdologyFailureRouter;
import com.coinbase.android.idology.IdologyFormValidConnector;
import com.coinbase.android.idology.IdologyRetryConnector;
import com.coinbase.android.idology.IdologyTrackingContextProvider;
import com.coinbase.android.idology.IdologyVerificationConnector;
import com.coinbase.android.settings.idology.IdologyRouter;
import com.coinbase.android.signin.AuthRouter;
import com.coinbase.android.signin.SignInRouter;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class StateIdologyRetryFormPresenter_Factory implements Factory<StateIdologyRetryFormPresenter> {
    private final Provider<AuthRouter> authRouterProvider;
    private final Provider<IdologyFailureRouter> idologyFailureRouterProvider;
    private final Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider;
    private final Provider<IdologyRetryConnector> idologyRetryConnectorProvider;
    private final Provider<IdologyRouter> idologyRouterProvider;
    private final Provider<IdologyTrackingContextProvider> idologyTrackingContextProvider;
    private final Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<StateIdologyRetryFormScreen> screenProvider;
    private final Provider<SignInRouter> signInRouterProvider;

    public StateIdologyRetryFormPresenter_Factory(Provider<StateIdologyRetryFormScreen> screenProvider, Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<IdologyRetryConnector> idologyRetryConnectorProvider, Provider<IdologyRouter> idologyRouterProvider, Provider<IdologyFailureRouter> idologyFailureRouterProvider, Provider<SignInRouter> signInRouterProvider, Provider<AuthRouter> authRouterProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<IdologyTrackingContextProvider> idologyTrackingContextProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.screenProvider = screenProvider;
        this.idologyFormValidConnectorProvider = idologyFormValidConnectorProvider;
        this.idologyVerificationConnectorProvider = idologyVerificationConnectorProvider;
        this.idologyRetryConnectorProvider = idologyRetryConnectorProvider;
        this.idologyRouterProvider = idologyRouterProvider;
        this.idologyFailureRouterProvider = idologyFailureRouterProvider;
        this.signInRouterProvider = signInRouterProvider;
        this.authRouterProvider = authRouterProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.idologyTrackingContextProvider = idologyTrackingContextProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public StateIdologyRetryFormPresenter get() {
        return provideInstance(this.screenProvider, this.idologyFormValidConnectorProvider, this.idologyVerificationConnectorProvider, this.idologyRetryConnectorProvider, this.idologyRouterProvider, this.idologyFailureRouterProvider, this.signInRouterProvider, this.authRouterProvider, this.mixpanelTrackingProvider, this.idologyTrackingContextProvider, this.mainSchedulerProvider);
    }

    public static StateIdologyRetryFormPresenter provideInstance(Provider<StateIdologyRetryFormScreen> screenProvider, Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<IdologyRetryConnector> idologyRetryConnectorProvider, Provider<IdologyRouter> idologyRouterProvider, Provider<IdologyFailureRouter> idologyFailureRouterProvider, Provider<SignInRouter> signInRouterProvider, Provider<AuthRouter> authRouterProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<IdologyTrackingContextProvider> idologyTrackingContextProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new StateIdologyRetryFormPresenter((StateIdologyRetryFormScreen) screenProvider.get(), (IdologyFormValidConnector) idologyFormValidConnectorProvider.get(), (IdologyVerificationConnector) idologyVerificationConnectorProvider.get(), (IdologyRetryConnector) idologyRetryConnectorProvider.get(), (IdologyRouter) idologyRouterProvider.get(), (IdologyFailureRouter) idologyFailureRouterProvider.get(), (SignInRouter) signInRouterProvider.get(), (AuthRouter) authRouterProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (IdologyTrackingContextProvider) idologyTrackingContextProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static StateIdologyRetryFormPresenter_Factory create(Provider<StateIdologyRetryFormScreen> screenProvider, Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<IdologyRetryConnector> idologyRetryConnectorProvider, Provider<IdologyRouter> idologyRouterProvider, Provider<IdologyFailureRouter> idologyFailureRouterProvider, Provider<SignInRouter> signInRouterProvider, Provider<AuthRouter> authRouterProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<IdologyTrackingContextProvider> idologyTrackingContextProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new StateIdologyRetryFormPresenter_Factory(screenProvider, idologyFormValidConnectorProvider, idologyVerificationConnectorProvider, idologyRetryConnectorProvider, idologyRouterProvider, idologyFailureRouterProvider, signInRouterProvider, authRouterProvider, mixpanelTrackingProvider, idologyTrackingContextProvider, mainSchedulerProvider);
    }

    public static StateIdologyRetryFormPresenter newStateIdologyRetryFormPresenter(StateIdologyRetryFormScreen screen, IdologyFormValidConnector idologyFormValidConnector, IdologyVerificationConnector idologyVerificationConnector, IdologyRetryConnector idologyRetryConnector, IdologyRouter idologyRouter, IdologyFailureRouter idologyFailureRouter, SignInRouter signInRouter, AuthRouter authRouter, MixpanelTracking mixpanelTracking, IdologyTrackingContextProvider idologyTrackingContext, Scheduler mainScheduler) {
        return new StateIdologyRetryFormPresenter(screen, idologyFormValidConnector, idologyVerificationConnector, idologyRetryConnector, idologyRouter, idologyFailureRouter, signInRouter, authRouter, mixpanelTracking, idologyTrackingContext, mainScheduler);
    }
}
