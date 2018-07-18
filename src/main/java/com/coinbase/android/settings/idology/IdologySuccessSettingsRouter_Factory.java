package com.coinbase.android.settings.idology;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class IdologySuccessSettingsRouter_Factory implements Factory<IdologySuccessSettingsRouter> {
    private final Provider<ActionBarController> controllerProvider;
    private final Provider<IdologyArgsBuilder> idologyArgsBuilderProvider;
    private final Provider<Scheduler> schedulerProvider;

    public IdologySuccessSettingsRouter_Factory(Provider<ActionBarController> controllerProvider, Provider<Scheduler> schedulerProvider, Provider<IdologyArgsBuilder> idologyArgsBuilderProvider) {
        this.controllerProvider = controllerProvider;
        this.schedulerProvider = schedulerProvider;
        this.idologyArgsBuilderProvider = idologyArgsBuilderProvider;
    }

    public IdologySuccessSettingsRouter get() {
        return provideInstance(this.controllerProvider, this.schedulerProvider, this.idologyArgsBuilderProvider);
    }

    public static IdologySuccessSettingsRouter provideInstance(Provider<ActionBarController> controllerProvider, Provider<Scheduler> schedulerProvider, Provider<IdologyArgsBuilder> idologyArgsBuilderProvider) {
        return new IdologySuccessSettingsRouter((ActionBarController) controllerProvider.get(), (Scheduler) schedulerProvider.get(), (IdologyArgsBuilder) idologyArgsBuilderProvider.get());
    }

    public static IdologySuccessSettingsRouter_Factory create(Provider<ActionBarController> controllerProvider, Provider<Scheduler> schedulerProvider, Provider<IdologyArgsBuilder> idologyArgsBuilderProvider) {
        return new IdologySuccessSettingsRouter_Factory(controllerProvider, schedulerProvider, idologyArgsBuilderProvider);
    }

    public static IdologySuccessSettingsRouter newIdologySuccessSettingsRouter(ActionBarController controller, Scheduler scheduler, IdologyArgsBuilder idologyArgsBuilder) {
        return new IdologySuccessSettingsRouter(controller, scheduler, idologyArgsBuilder);
    }
}
