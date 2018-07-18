package com.coinbase.android.signin.state;

import com.coinbase.android.idology.IdologyFormValidConnector;
import com.coinbase.android.idology.IdologyVerificationConnector;
import com.coinbase.android.idology.ProgressConnector;
import com.coinbase.android.settings.idology.IdologyRouter;
import com.coinbase.android.signin.SignInRouter;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class StateIdologyNameFormPresenter_Factory implements Factory<StateIdologyNameFormPresenter> {
    private final Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider;
    private final Provider<IdologyRouter> idologyRouterProvider;
    private final Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<ProgressConnector> progressConnectorProvider;
    private final Provider<StateIdologyFormScreen> screenProvider;
    private final Provider<SignInRouter> signInRouterProvider;

    public StateIdologyNameFormPresenter_Factory(Provider<StateIdologyFormScreen> screenProvider, Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<ProgressConnector> progressConnectorProvider, Provider<IdologyRouter> idologyRouterProvider, Provider<SignInRouter> signInRouterProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.screenProvider = screenProvider;
        this.idologyFormValidConnectorProvider = idologyFormValidConnectorProvider;
        this.idologyVerificationConnectorProvider = idologyVerificationConnectorProvider;
        this.progressConnectorProvider = progressConnectorProvider;
        this.idologyRouterProvider = idologyRouterProvider;
        this.signInRouterProvider = signInRouterProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public StateIdologyNameFormPresenter get() {
        return provideInstance(this.screenProvider, this.idologyFormValidConnectorProvider, this.idologyVerificationConnectorProvider, this.progressConnectorProvider, this.idologyRouterProvider, this.signInRouterProvider, this.mixpanelTrackingProvider, this.mainSchedulerProvider);
    }

    public static StateIdologyNameFormPresenter provideInstance(Provider<StateIdologyFormScreen> screenProvider, Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<ProgressConnector> progressConnectorProvider, Provider<IdologyRouter> idologyRouterProvider, Provider<SignInRouter> signInRouterProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new StateIdologyNameFormPresenter((StateIdologyFormScreen) screenProvider.get(), (IdologyFormValidConnector) idologyFormValidConnectorProvider.get(), (IdologyVerificationConnector) idologyVerificationConnectorProvider.get(), (ProgressConnector) progressConnectorProvider.get(), (IdologyRouter) idologyRouterProvider.get(), (SignInRouter) signInRouterProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static StateIdologyNameFormPresenter_Factory create(Provider<StateIdologyFormScreen> screenProvider, Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<ProgressConnector> progressConnectorProvider, Provider<IdologyRouter> idologyRouterProvider, Provider<SignInRouter> signInRouterProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new StateIdologyNameFormPresenter_Factory(screenProvider, idologyFormValidConnectorProvider, idologyVerificationConnectorProvider, progressConnectorProvider, idologyRouterProvider, signInRouterProvider, mixpanelTrackingProvider, mainSchedulerProvider);
    }

    public static StateIdologyNameFormPresenter newStateIdologyNameFormPresenter(StateIdologyFormScreen screen, IdologyFormValidConnector idologyFormValidConnector, IdologyVerificationConnector idologyVerificationConnector, ProgressConnector progressConnector, IdologyRouter idologyRouter, SignInRouter signInRouter, MixpanelTracking mixpanelTracking, Scheduler mainScheduler) {
        return new StateIdologyNameFormPresenter(screen, idologyFormValidConnector, idologyVerificationConnector, progressConnector, idologyRouter, signInRouter, mixpanelTracking, mainScheduler);
    }
}
