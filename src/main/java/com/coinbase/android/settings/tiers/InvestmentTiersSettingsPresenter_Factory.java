package com.coinbase.android.settings.tiers;

import android.app.Application;
import com.coinbase.android.ui.AutoResizeTextViewAdjustor;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class InvestmentTiersSettingsPresenter_Factory implements Factory<InvestmentTiersSettingsPresenter> {
    private final Provider<Application> applicationProvider;
    private final Provider<AutoResizeTextViewAdjustor> autoResizeTextViewAdjustorProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<MoneyFormatterUtil> moneyFormatterUtilProvider;
    private final Provider<InvestmentTiersSettingsRouter> routerProvider;
    private final Provider<InvestmentTiersSettingsScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;

    public InvestmentTiersSettingsPresenter_Factory(Provider<InvestmentTiersSettingsScreen> screenProvider, Provider<InvestmentTiersSettingsRouter> routerProvider, Provider<LoginManager> loginManagerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Application> applicationProvider, Provider<AutoResizeTextViewAdjustor> autoResizeTextViewAdjustorProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.screenProvider = screenProvider;
        this.routerProvider = routerProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.moneyFormatterUtilProvider = moneyFormatterUtilProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.applicationProvider = applicationProvider;
        this.autoResizeTextViewAdjustorProvider = autoResizeTextViewAdjustorProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public InvestmentTiersSettingsPresenter get() {
        return provideInstance(this.screenProvider, this.routerProvider, this.loginManagerProvider, this.snackBarWrapperProvider, this.moneyFormatterUtilProvider, this.mixpanelTrackingProvider, this.applicationProvider, this.autoResizeTextViewAdjustorProvider, this.mainSchedulerProvider);
    }

    public static InvestmentTiersSettingsPresenter provideInstance(Provider<InvestmentTiersSettingsScreen> screenProvider, Provider<InvestmentTiersSettingsRouter> routerProvider, Provider<LoginManager> loginManagerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Application> applicationProvider, Provider<AutoResizeTextViewAdjustor> autoResizeTextViewAdjustorProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new InvestmentTiersSettingsPresenter((InvestmentTiersSettingsScreen) screenProvider.get(), (InvestmentTiersSettingsRouter) routerProvider.get(), (LoginManager) loginManagerProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (MoneyFormatterUtil) moneyFormatterUtilProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (Application) applicationProvider.get(), (AutoResizeTextViewAdjustor) autoResizeTextViewAdjustorProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static InvestmentTiersSettingsPresenter_Factory create(Provider<InvestmentTiersSettingsScreen> screenProvider, Provider<InvestmentTiersSettingsRouter> routerProvider, Provider<LoginManager> loginManagerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Application> applicationProvider, Provider<AutoResizeTextViewAdjustor> autoResizeTextViewAdjustorProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new InvestmentTiersSettingsPresenter_Factory(screenProvider, routerProvider, loginManagerProvider, snackBarWrapperProvider, moneyFormatterUtilProvider, mixpanelTrackingProvider, applicationProvider, autoResizeTextViewAdjustorProvider, mainSchedulerProvider);
    }

    public static InvestmentTiersSettingsPresenter newInvestmentTiersSettingsPresenter(InvestmentTiersSettingsScreen screen, InvestmentTiersSettingsRouter router, LoginManager loginManager, SnackBarWrapper snackBarWrapper, MoneyFormatterUtil moneyFormatterUtil, MixpanelTracking mixpanelTracking, Application application, AutoResizeTextViewAdjustor autoResizeTextViewAdjustor, Scheduler mainScheduler) {
        return new InvestmentTiersSettingsPresenter(screen, router, loginManager, snackBarWrapper, moneyFormatterUtil, mixpanelTracking, application, autoResizeTextViewAdjustor, mainScheduler);
    }
}
