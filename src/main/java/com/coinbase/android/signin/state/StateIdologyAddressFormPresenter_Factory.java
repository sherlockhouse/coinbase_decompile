package com.coinbase.android.signin.state;

import com.coinbase.android.idology.IdologyAutoCompleteShownConnector;
import com.coinbase.android.idology.IdologyFormValidConnector;
import com.coinbase.android.idology.IdologyVerificationConnector;
import com.coinbase.android.idology.ProgressConnector;
import com.coinbase.android.settings.idology.IdologyRouter;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class StateIdologyAddressFormPresenter_Factory implements Factory<StateIdologyAddressFormPresenter> {
    private final Provider<IdologyAutoCompleteShownConnector> idologyAutoCompleteShownConnectorProvider;
    private final Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider;
    private final Provider<IdologyRouter> idologyRouterProvider;
    private final Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<ProgressConnector> progressConnectorProvider;
    private final Provider<StateIdologyAddressFormScreen> screenProvider;

    public StateIdologyAddressFormPresenter_Factory(Provider<StateIdologyAddressFormScreen> screenProvider, Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<IdologyAutoCompleteShownConnector> idologyAutoCompleteShownConnectorProvider, Provider<ProgressConnector> progressConnectorProvider, Provider<IdologyRouter> idologyRouterProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.screenProvider = screenProvider;
        this.idologyFormValidConnectorProvider = idologyFormValidConnectorProvider;
        this.idologyVerificationConnectorProvider = idologyVerificationConnectorProvider;
        this.idologyAutoCompleteShownConnectorProvider = idologyAutoCompleteShownConnectorProvider;
        this.progressConnectorProvider = progressConnectorProvider;
        this.idologyRouterProvider = idologyRouterProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public StateIdologyAddressFormPresenter get() {
        return provideInstance(this.screenProvider, this.idologyFormValidConnectorProvider, this.idologyVerificationConnectorProvider, this.idologyAutoCompleteShownConnectorProvider, this.progressConnectorProvider, this.idologyRouterProvider, this.mainSchedulerProvider);
    }

    public static StateIdologyAddressFormPresenter provideInstance(Provider<StateIdologyAddressFormScreen> screenProvider, Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<IdologyAutoCompleteShownConnector> idologyAutoCompleteShownConnectorProvider, Provider<ProgressConnector> progressConnectorProvider, Provider<IdologyRouter> idologyRouterProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new StateIdologyAddressFormPresenter((StateIdologyAddressFormScreen) screenProvider.get(), (IdologyFormValidConnector) idologyFormValidConnectorProvider.get(), (IdologyVerificationConnector) idologyVerificationConnectorProvider.get(), (IdologyAutoCompleteShownConnector) idologyAutoCompleteShownConnectorProvider.get(), (ProgressConnector) progressConnectorProvider.get(), (IdologyRouter) idologyRouterProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static StateIdologyAddressFormPresenter_Factory create(Provider<StateIdologyAddressFormScreen> screenProvider, Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<IdologyAutoCompleteShownConnector> idologyAutoCompleteShownConnectorProvider, Provider<ProgressConnector> progressConnectorProvider, Provider<IdologyRouter> idologyRouterProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new StateIdologyAddressFormPresenter_Factory(screenProvider, idologyFormValidConnectorProvider, idologyVerificationConnectorProvider, idologyAutoCompleteShownConnectorProvider, progressConnectorProvider, idologyRouterProvider, mainSchedulerProvider);
    }

    public static StateIdologyAddressFormPresenter newStateIdologyAddressFormPresenter(StateIdologyAddressFormScreen screen, IdologyFormValidConnector idologyFormValidConnector, IdologyVerificationConnector idologyVerificationConnector, IdologyAutoCompleteShownConnector idologyAutoCompleteShownConnector, ProgressConnector progressConnector, IdologyRouter idologyRouter, Scheduler mainScheduler) {
        return new StateIdologyAddressFormPresenter(screen, idologyFormValidConnector, idologyVerificationConnector, idologyAutoCompleteShownConnector, progressConnector, idologyRouter, mainScheduler);
    }
}
