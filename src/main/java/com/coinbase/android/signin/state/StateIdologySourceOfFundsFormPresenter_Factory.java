package com.coinbase.android.signin.state;

import com.coinbase.android.idology.IdologyFailureRouter;
import com.coinbase.android.idology.IdologyFormValidConnector;
import com.coinbase.android.idology.IdologyRetryConnector;
import com.coinbase.android.idology.IdologyTrackingContextProvider;
import com.coinbase.android.idology.IdologyVerificationConnector;
import com.coinbase.android.idology.ProgressConnector;
import com.coinbase.android.settings.idology.IdologyRouter;
import com.coinbase.android.signin.AuthRouter;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class StateIdologySourceOfFundsFormPresenter_Factory implements Factory<StateIdologySourceOfFundsFormPresenter> {
    private final Provider<AuthRouter> authRouterProvider;
    private final Provider<IdologyFailureRouter> idologyFailureRouterProvider;
    private final Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider;
    private final Provider<IdologyRetryConnector> idologyRetryConnectorProvider;
    private final Provider<IdologyRouter> idologyRouterProvider;
    private final Provider<IdologyTrackingContextProvider> idologyTrackingContextProvider;
    private final Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<ProgressConnector> progressConnectorProvider;
    private final Provider<StateIdologySourceOfFundsScreen> screenProvider;

    public StateIdologySourceOfFundsFormPresenter_Factory(Provider<StateIdologySourceOfFundsScreen> screenProvider, Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<IdologyRetryConnector> idologyRetryConnectorProvider, Provider<ProgressConnector> progressConnectorProvider, Provider<IdologyRouter> idologyRouterProvider, Provider<IdologyFailureRouter> idologyFailureRouterProvider, Provider<AuthRouter> authRouterProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<IdologyTrackingContextProvider> idologyTrackingContextProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.screenProvider = screenProvider;
        this.idologyFormValidConnectorProvider = idologyFormValidConnectorProvider;
        this.idologyVerificationConnectorProvider = idologyVerificationConnectorProvider;
        this.idologyRetryConnectorProvider = idologyRetryConnectorProvider;
        this.progressConnectorProvider = progressConnectorProvider;
        this.idologyRouterProvider = idologyRouterProvider;
        this.idologyFailureRouterProvider = idologyFailureRouterProvider;
        this.authRouterProvider = authRouterProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.idologyTrackingContextProvider = idologyTrackingContextProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public StateIdologySourceOfFundsFormPresenter get() {
        return provideInstance(this.screenProvider, this.idologyFormValidConnectorProvider, this.idologyVerificationConnectorProvider, this.idologyRetryConnectorProvider, this.progressConnectorProvider, this.idologyRouterProvider, this.idologyFailureRouterProvider, this.authRouterProvider, this.mixpanelTrackingProvider, this.idologyTrackingContextProvider, this.mainSchedulerProvider);
    }

    public static StateIdologySourceOfFundsFormPresenter provideInstance(Provider<StateIdologySourceOfFundsScreen> screenProvider, Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<IdologyRetryConnector> idologyRetryConnectorProvider, Provider<ProgressConnector> progressConnectorProvider, Provider<IdologyRouter> idologyRouterProvider, Provider<IdologyFailureRouter> idologyFailureRouterProvider, Provider<AuthRouter> authRouterProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<IdologyTrackingContextProvider> idologyTrackingContextProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new StateIdologySourceOfFundsFormPresenter((StateIdologySourceOfFundsScreen) screenProvider.get(), (IdologyFormValidConnector) idologyFormValidConnectorProvider.get(), (IdologyVerificationConnector) idologyVerificationConnectorProvider.get(), (IdologyRetryConnector) idologyRetryConnectorProvider.get(), (ProgressConnector) progressConnectorProvider.get(), (IdologyRouter) idologyRouterProvider.get(), (IdologyFailureRouter) idologyFailureRouterProvider.get(), (AuthRouter) authRouterProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (IdologyTrackingContextProvider) idologyTrackingContextProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static StateIdologySourceOfFundsFormPresenter_Factory create(Provider<StateIdologySourceOfFundsScreen> screenProvider, Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<IdologyRetryConnector> idologyRetryConnectorProvider, Provider<ProgressConnector> progressConnectorProvider, Provider<IdologyRouter> idologyRouterProvider, Provider<IdologyFailureRouter> idologyFailureRouterProvider, Provider<AuthRouter> authRouterProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<IdologyTrackingContextProvider> idologyTrackingContextProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new StateIdologySourceOfFundsFormPresenter_Factory(screenProvider, idologyFormValidConnectorProvider, idologyVerificationConnectorProvider, idologyRetryConnectorProvider, progressConnectorProvider, idologyRouterProvider, idologyFailureRouterProvider, authRouterProvider, mixpanelTrackingProvider, idologyTrackingContextProvider, mainSchedulerProvider);
    }

    public static StateIdologySourceOfFundsFormPresenter newStateIdologySourceOfFundsFormPresenter(StateIdologySourceOfFundsScreen screen, IdologyFormValidConnector idologyFormValidConnector, IdologyVerificationConnector idologyVerificationConnector, IdologyRetryConnector idologyRetryConnector, ProgressConnector progressConnector, IdologyRouter idologyRouter, IdologyFailureRouter idologyFailureRouter, AuthRouter authRouter, MixpanelTracking mixpanelTracking, IdologyTrackingContextProvider idologyTrackingContextProvider, Scheduler mainScheduler) {
        return new StateIdologySourceOfFundsFormPresenter(screen, idologyFormValidConnector, idologyVerificationConnector, idologyRetryConnector, progressConnector, idologyRouter, idologyFailureRouter, authRouter, mixpanelTracking, idologyTrackingContextProvider, mainScheduler);
    }
}
