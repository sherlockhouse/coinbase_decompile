package com.coinbase.android.quickstart;

import com.coinbase.android.paymentmethods.GetPaymentMethodsTaskRx;
import com.coinbase.android.phone.PhoneNumbersUpdatedConnector;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import rx.Scheduler;

public final class QuickstartModule_ProvidesQuickStartManagerFactory implements Factory<QuickstartManager> {
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final QuickstartModule module;
    private final Provider<PhoneNumbersUpdatedConnector> phoneNumbersUpdatedConnectorProvider;
    private final Provider<SplitTesting> splitTestingProvider;
    private final Provider<GetPaymentMethodsTaskRx> taskProvider;

    public QuickstartModule_ProvidesQuickStartManagerFactory(QuickstartModule module, Provider<LoginManager> loginManagerProvider, Provider<PhoneNumbersUpdatedConnector> phoneNumbersUpdatedConnectorProvider, Provider<GetPaymentMethodsTaskRx> taskProvider, Provider<Scheduler> mainSchedulerProvider, Provider<SplitTesting> splitTestingProvider) {
        this.module = module;
        this.loginManagerProvider = loginManagerProvider;
        this.phoneNumbersUpdatedConnectorProvider = phoneNumbersUpdatedConnectorProvider;
        this.taskProvider = taskProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
        this.splitTestingProvider = splitTestingProvider;
    }

    public QuickstartManager get() {
        return provideInstance(this.module, this.loginManagerProvider, this.phoneNumbersUpdatedConnectorProvider, this.taskProvider, this.mainSchedulerProvider, this.splitTestingProvider);
    }

    public static QuickstartManager provideInstance(QuickstartModule module, Provider<LoginManager> loginManagerProvider, Provider<PhoneNumbersUpdatedConnector> phoneNumbersUpdatedConnectorProvider, Provider<GetPaymentMethodsTaskRx> taskProvider, Provider<Scheduler> mainSchedulerProvider, Provider<SplitTesting> splitTestingProvider) {
        return proxyProvidesQuickStartManager(module, (LoginManager) loginManagerProvider.get(), (PhoneNumbersUpdatedConnector) phoneNumbersUpdatedConnectorProvider.get(), (GetPaymentMethodsTaskRx) taskProvider.get(), (Scheduler) mainSchedulerProvider.get(), (SplitTesting) splitTestingProvider.get());
    }

    public static QuickstartModule_ProvidesQuickStartManagerFactory create(QuickstartModule module, Provider<LoginManager> loginManagerProvider, Provider<PhoneNumbersUpdatedConnector> phoneNumbersUpdatedConnectorProvider, Provider<GetPaymentMethodsTaskRx> taskProvider, Provider<Scheduler> mainSchedulerProvider, Provider<SplitTesting> splitTestingProvider) {
        return new QuickstartModule_ProvidesQuickStartManagerFactory(module, loginManagerProvider, phoneNumbersUpdatedConnectorProvider, taskProvider, mainSchedulerProvider, splitTestingProvider);
    }

    public static QuickstartManager proxyProvidesQuickStartManager(QuickstartModule instance, LoginManager loginManager, PhoneNumbersUpdatedConnector phoneNumbersUpdatedConnector, GetPaymentMethodsTaskRx task, Scheduler mainScheduler, SplitTesting splitTesting) {
        return (QuickstartManager) Preconditions.checkNotNull(instance.providesQuickStartManager(loginManager, phoneNumbersUpdatedConnector, task, mainScheduler, splitTesting), "Cannot return null from a non-@Nullable @Provides method");
    }
}
