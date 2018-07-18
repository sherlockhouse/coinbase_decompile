package com.coinbase.android.settings.idology;

import com.coinbase.android.idology.IdologyAnswerListValidConnector;
import com.coinbase.android.idology.IdologyFailureRouter;
import com.coinbase.android.idology.IdologyRetryConnector;
import com.coinbase.android.idology.IdologyVerificationConnector;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class IdologySettingsQuestionsPresenter_Factory implements Factory<IdologySettingsQuestionsPresenter> {
    private final Provider<IdologyAnswerListValidConnector> idologyAnswerListValidConnectorProvider;
    private final Provider<IdologyFailureRouter> idologyFailureRouterProvider;
    private final Provider<IdologyRetryConnector> idologyRetryConnectorProvider;
    private final Provider<IdologyRouter> idologyRouterProvider;
    private final Provider<IdologySuccessRouter> idologySuccessRouterProvider;
    private final Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<IdologySettingsQuestionsScreen> screenProvider;

    public IdologySettingsQuestionsPresenter_Factory(Provider<IdologySettingsQuestionsScreen> screenProvider, Provider<IdologyAnswerListValidConnector> idologyAnswerListValidConnectorProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<IdologyRetryConnector> idologyRetryConnectorProvider, Provider<IdologySuccessRouter> idologySuccessRouterProvider, Provider<IdologyFailureRouter> idologyFailureRouterProvider, Provider<IdologyRouter> idologyRouterProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.screenProvider = screenProvider;
        this.idologyAnswerListValidConnectorProvider = idologyAnswerListValidConnectorProvider;
        this.idologyVerificationConnectorProvider = idologyVerificationConnectorProvider;
        this.idologyRetryConnectorProvider = idologyRetryConnectorProvider;
        this.idologySuccessRouterProvider = idologySuccessRouterProvider;
        this.idologyFailureRouterProvider = idologyFailureRouterProvider;
        this.idologyRouterProvider = idologyRouterProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public IdologySettingsQuestionsPresenter get() {
        return provideInstance(this.screenProvider, this.idologyAnswerListValidConnectorProvider, this.idologyVerificationConnectorProvider, this.idologyRetryConnectorProvider, this.idologySuccessRouterProvider, this.idologyFailureRouterProvider, this.idologyRouterProvider, this.mainSchedulerProvider);
    }

    public static IdologySettingsQuestionsPresenter provideInstance(Provider<IdologySettingsQuestionsScreen> screenProvider, Provider<IdologyAnswerListValidConnector> idologyAnswerListValidConnectorProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<IdologyRetryConnector> idologyRetryConnectorProvider, Provider<IdologySuccessRouter> idologySuccessRouterProvider, Provider<IdologyFailureRouter> idologyFailureRouterProvider, Provider<IdologyRouter> idologyRouterProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new IdologySettingsQuestionsPresenter((IdologySettingsQuestionsScreen) screenProvider.get(), (IdologyAnswerListValidConnector) idologyAnswerListValidConnectorProvider.get(), (IdologyVerificationConnector) idologyVerificationConnectorProvider.get(), (IdologyRetryConnector) idologyRetryConnectorProvider.get(), (IdologySuccessRouter) idologySuccessRouterProvider.get(), (IdologyFailureRouter) idologyFailureRouterProvider.get(), (IdologyRouter) idologyRouterProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static IdologySettingsQuestionsPresenter_Factory create(Provider<IdologySettingsQuestionsScreen> screenProvider, Provider<IdologyAnswerListValidConnector> idologyAnswerListValidConnectorProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<IdologyRetryConnector> idologyRetryConnectorProvider, Provider<IdologySuccessRouter> idologySuccessRouterProvider, Provider<IdologyFailureRouter> idologyFailureRouterProvider, Provider<IdologyRouter> idologyRouterProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new IdologySettingsQuestionsPresenter_Factory(screenProvider, idologyAnswerListValidConnectorProvider, idologyVerificationConnectorProvider, idologyRetryConnectorProvider, idologySuccessRouterProvider, idologyFailureRouterProvider, idologyRouterProvider, mainSchedulerProvider);
    }

    public static IdologySettingsQuestionsPresenter newIdologySettingsQuestionsPresenter(IdologySettingsQuestionsScreen screen, IdologyAnswerListValidConnector idologyAnswerListValidConnector, IdologyVerificationConnector idologyVerificationConnector, IdologyRetryConnector idologyRetryConnector, IdologySuccessRouter idologySuccessRouter, IdologyFailureRouter idologyFailureRouter, IdologyRouter idologyRouter, Scheduler mainScheduler) {
        return new IdologySettingsQuestionsPresenter(screen, idologyAnswerListValidConnector, idologyVerificationConnector, idologyRetryConnector, idologySuccessRouter, idologyFailureRouter, idologyRouter, mainScheduler);
    }
}
