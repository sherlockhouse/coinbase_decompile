package com.coinbase.android.modalAlerts;

import dagger.internal.Factory;
import java.util.List;
import javax.inject.Provider;
import rx.Scheduler;

public final class ModalRouterAggregator_Factory implements Factory<ModalRouterAggregator> {
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<List<ModalRouter>> modalRoutersProvider;

    public ModalRouterAggregator_Factory(Provider<List<ModalRouter>> modalRoutersProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.modalRoutersProvider = modalRoutersProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public ModalRouterAggregator get() {
        return provideInstance(this.modalRoutersProvider, this.mainSchedulerProvider);
    }

    public static ModalRouterAggregator provideInstance(Provider<List<ModalRouter>> modalRoutersProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new ModalRouterAggregator((List) modalRoutersProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static ModalRouterAggregator_Factory create(Provider<List<ModalRouter>> modalRoutersProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new ModalRouterAggregator_Factory(modalRoutersProvider, mainSchedulerProvider);
    }

    public static ModalRouterAggregator newModalRouterAggregator(List<ModalRouter> modalRouters, Scheduler mainScheduler) {
        return new ModalRouterAggregator(modalRouters, mainScheduler);
    }
}
