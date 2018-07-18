package com.coinbase.android.settings.idology;

import com.coinbase.android.idology.IdologyFailureRouter;
import com.coinbase.android.idology.IdologyFormValidConnector;
import com.coinbase.android.idology.IdologyRetryConnector;
import com.coinbase.android.idology.IdologyVerificationConnector;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class IdologySettingsPresenter_Factory implements Factory<IdologySettingsPresenter> {
    private final Provider<IdologyFailureRouter> idologyFailureRouterProvider;
    private final Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider;
    private final Provider<IdologyRetryConnector> idologyRetryConnectorProvider;
    private final Provider<IdologyRouter> idologyRouterProvider;
    private final Provider<IdologySuccessRouter> idologySuccessRouterProvider;
    private final Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<IdologySettingsScreen> screenProvider;

    public IdologySettingsPresenter_Factory(Provider<IdologySettingsScreen> screenProvider, Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<IdologyRouter> idologyRouterProvider, Provider<IdologySuccessRouter> idologySuccessRouterProvider, Provider<IdologyFailureRouter> idologyFailureRouterProvider, Provider<IdologyRetryConnector> idologyRetryConnectorProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.screenProvider = screenProvider;
        this.idologyFormValidConnectorProvider = idologyFormValidConnectorProvider;
        this.idologyVerificationConnectorProvider = idologyVerificationConnectorProvider;
        this.idologyRouterProvider = idologyRouterProvider;
        this.idologySuccessRouterProvider = idologySuccessRouterProvider;
        this.idologyFailureRouterProvider = idologyFailureRouterProvider;
        this.idologyRetryConnectorProvider = idologyRetryConnectorProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public IdologySettingsPresenter get() {
        return provideInstance(this.screenProvider, this.idologyFormValidConnectorProvider, this.idologyVerificationConnectorProvider, this.idologyRouterProvider, this.idologySuccessRouterProvider, this.idologyFailureRouterProvider, this.idologyRetryConnectorProvider, this.mainSchedulerProvider);
    }

    public static IdologySettingsPresenter provideInstance(Provider<IdologySettingsScreen> screenProvider, Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<IdologyRouter> idologyRouterProvider, Provider<IdologySuccessRouter> idologySuccessRouterProvider, Provider<IdologyFailureRouter> idologyFailureRouterProvider, Provider<IdologyRetryConnector> idologyRetryConnectorProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new IdologySettingsPresenter((IdologySettingsScreen) screenProvider.get(), (IdologyFormValidConnector) idologyFormValidConnectorProvider.get(), (IdologyVerificationConnector) idologyVerificationConnectorProvider.get(), (IdologyRouter) idologyRouterProvider.get(), (IdologySuccessRouter) idologySuccessRouterProvider.get(), (IdologyFailureRouter) idologyFailureRouterProvider.get(), (IdologyRetryConnector) idologyRetryConnectorProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static IdologySettingsPresenter_Factory create(Provider<IdologySettingsScreen> screenProvider, Provider<IdologyFormValidConnector> idologyFormValidConnectorProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<IdologyRouter> idologyRouterProvider, Provider<IdologySuccessRouter> idologySuccessRouterProvider, Provider<IdologyFailureRouter> idologyFailureRouterProvider, Provider<IdologyRetryConnector> idologyRetryConnectorProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new IdologySettingsPresenter_Factory(screenProvider, idologyFormValidConnectorProvider, idologyVerificationConnectorProvider, idologyRouterProvider, idologySuccessRouterProvider, idologyFailureRouterProvider, idologyRetryConnectorProvider, mainSchedulerProvider);
    }

    public static IdologySettingsPresenter newIdologySettingsPresenter(IdologySettingsScreen screen, IdologyFormValidConnector idologyFormValidConnector, IdologyVerificationConnector idologyVerificationConnector, IdologyRouter idologyRouter, IdologySuccessRouter idologySuccessRouter, IdologyFailureRouter idologyFailureRouter, IdologyRetryConnector idologyRetryConnector, Scheduler mainScheduler) {
        return new IdologySettingsPresenter(screen, idologyFormValidConnector, idologyVerificationConnector, idologyRouter, idologySuccessRouter, idologyFailureRouter, idologyRetryConnector, mainScheduler);
    }
}
