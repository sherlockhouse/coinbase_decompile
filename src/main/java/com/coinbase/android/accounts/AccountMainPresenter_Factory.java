package com.coinbase.android.accounts;

import com.coinbase.android.ui.CurrenciesUpdatedConnector;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class AccountMainPresenter_Factory implements Factory<AccountMainPresenter> {
    private final Provider<AccountItemClickedConnector> accountItemClickedConnectorProvider;
    private final Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<AccountMainScreen> screenProvider;

    public AccountMainPresenter_Factory(Provider<AccountMainScreen> screenProvider, Provider<AccountItemClickedConnector> accountItemClickedConnectorProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.screenProvider = screenProvider;
        this.accountItemClickedConnectorProvider = accountItemClickedConnectorProvider;
        this.currenciesUpdatedConnectorProvider = currenciesUpdatedConnectorProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public AccountMainPresenter get() {
        return provideInstance(this.screenProvider, this.accountItemClickedConnectorProvider, this.currenciesUpdatedConnectorProvider, this.mixpanelTrackingProvider, this.mainSchedulerProvider);
    }

    public static AccountMainPresenter provideInstance(Provider<AccountMainScreen> screenProvider, Provider<AccountItemClickedConnector> accountItemClickedConnectorProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new AccountMainPresenter((AccountMainScreen) screenProvider.get(), (AccountItemClickedConnector) accountItemClickedConnectorProvider.get(), (CurrenciesUpdatedConnector) currenciesUpdatedConnectorProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static AccountMainPresenter_Factory create(Provider<AccountMainScreen> screenProvider, Provider<AccountItemClickedConnector> accountItemClickedConnectorProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new AccountMainPresenter_Factory(screenProvider, accountItemClickedConnectorProvider, currenciesUpdatedConnectorProvider, mixpanelTrackingProvider, mainSchedulerProvider);
    }

    public static AccountMainPresenter newAccountMainPresenter(AccountMainScreen screen, AccountItemClickedConnector accountItemClickedConnector, CurrenciesUpdatedConnector currenciesUpdatedConnector, MixpanelTracking mixpanelTracking, Scheduler mainScheduler) {
        return new AccountMainPresenter(screen, accountItemClickedConnector, currenciesUpdatedConnector, mixpanelTracking, mainScheduler);
    }
}
