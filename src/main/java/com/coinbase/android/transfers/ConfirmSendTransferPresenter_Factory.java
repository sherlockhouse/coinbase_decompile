package com.coinbase.android.transfers;

import android.app.Application;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class ConfirmSendTransferPresenter_Factory implements Factory<ConfirmSendTransferPresenter> {
    private final Provider<Application> appProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<MoneyFormatterUtil> moneyFormatterUtilProvider;
    private final Provider<ConfirmSendTransferScreen> screenProvider;
    private final Provider<TransferMadeConnector> transferMadeConnectorProvider;

    public ConfirmSendTransferPresenter_Factory(Provider<ConfirmSendTransferScreen> screenProvider, Provider<Application> appProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<LoginManager> loginManagerProvider, Provider<TransferMadeConnector> transferMadeConnectorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.screenProvider = screenProvider;
        this.appProvider = appProvider;
        this.moneyFormatterUtilProvider = moneyFormatterUtilProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.transferMadeConnectorProvider = transferMadeConnectorProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public ConfirmSendTransferPresenter get() {
        return provideInstance(this.screenProvider, this.appProvider, this.moneyFormatterUtilProvider, this.loginManagerProvider, this.transferMadeConnectorProvider, this.mixpanelTrackingProvider, this.mainSchedulerProvider);
    }

    public static ConfirmSendTransferPresenter provideInstance(Provider<ConfirmSendTransferScreen> screenProvider, Provider<Application> appProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<LoginManager> loginManagerProvider, Provider<TransferMadeConnector> transferMadeConnectorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new ConfirmSendTransferPresenter((ConfirmSendTransferScreen) screenProvider.get(), (Application) appProvider.get(), (MoneyFormatterUtil) moneyFormatterUtilProvider.get(), (LoginManager) loginManagerProvider.get(), (TransferMadeConnector) transferMadeConnectorProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static ConfirmSendTransferPresenter_Factory create(Provider<ConfirmSendTransferScreen> screenProvider, Provider<Application> appProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<LoginManager> loginManagerProvider, Provider<TransferMadeConnector> transferMadeConnectorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new ConfirmSendTransferPresenter_Factory(screenProvider, appProvider, moneyFormatterUtilProvider, loginManagerProvider, transferMadeConnectorProvider, mixpanelTrackingProvider, mainSchedulerProvider);
    }

    public static ConfirmSendTransferPresenter newConfirmSendTransferPresenter(ConfirmSendTransferScreen screen, Application app, MoneyFormatterUtil moneyFormatterUtil, LoginManager loginManager, TransferMadeConnector transferMadeConnector, MixpanelTracking mixpanelTracking, Scheduler mainScheduler) {
        return new ConfirmSendTransferPresenter(screen, app, moneyFormatterUtil, loginManager, transferMadeConnector, mixpanelTracking, mainScheduler);
    }
}
