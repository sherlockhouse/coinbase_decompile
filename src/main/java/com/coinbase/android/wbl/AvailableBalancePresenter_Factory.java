package com.coinbase.android.wbl;

import android.app.Application;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class AvailableBalancePresenter_Factory implements Factory<AvailableBalancePresenter> {
    private final Provider<Application> applicationProvider;
    private final Provider<AvailableBalanceCalculator> availableBalanceCalculatorProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<MoneyFormatterUtil> moneyFormatterUtilProvider;
    private final Provider<AvailableBalanceScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;

    public AvailableBalancePresenter_Factory(Provider<AvailableBalanceScreen> screenProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Application> applicationProvider, Provider<AvailableBalanceCalculator> availableBalanceCalculatorProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.screenProvider = screenProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.moneyFormatterUtilProvider = moneyFormatterUtilProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.applicationProvider = applicationProvider;
        this.availableBalanceCalculatorProvider = availableBalanceCalculatorProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public AvailableBalancePresenter get() {
        return provideInstance(this.screenProvider, this.snackBarWrapperProvider, this.moneyFormatterUtilProvider, this.mixpanelTrackingProvider, this.applicationProvider, this.availableBalanceCalculatorProvider, this.mainSchedulerProvider);
    }

    public static AvailableBalancePresenter provideInstance(Provider<AvailableBalanceScreen> screenProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Application> applicationProvider, Provider<AvailableBalanceCalculator> availableBalanceCalculatorProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new AvailableBalancePresenter((AvailableBalanceScreen) screenProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (MoneyFormatterUtil) moneyFormatterUtilProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (Application) applicationProvider.get(), (AvailableBalanceCalculator) availableBalanceCalculatorProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static AvailableBalancePresenter_Factory create(Provider<AvailableBalanceScreen> screenProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Application> applicationProvider, Provider<AvailableBalanceCalculator> availableBalanceCalculatorProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new AvailableBalancePresenter_Factory(screenProvider, snackBarWrapperProvider, moneyFormatterUtilProvider, mixpanelTrackingProvider, applicationProvider, availableBalanceCalculatorProvider, mainSchedulerProvider);
    }

    public static AvailableBalancePresenter newAvailableBalancePresenter(AvailableBalanceScreen screen, SnackBarWrapper snackBarWrapper, MoneyFormatterUtil moneyFormatterUtil, MixpanelTracking mixpanelTracking, Application application, AvailableBalanceCalculator availableBalanceCalculator, Scheduler mainScheduler) {
        return new AvailableBalancePresenter(screen, snackBarWrapper, moneyFormatterUtil, mixpanelTracking, application, availableBalanceCalculator, mainScheduler);
    }
}
