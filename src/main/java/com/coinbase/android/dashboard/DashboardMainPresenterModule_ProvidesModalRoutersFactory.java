package com.coinbase.android.dashboard;

import com.coinbase.android.gdpr.GdprModalRouter;
import com.coinbase.android.modalAlerts.ModalRouter;
import com.coinbase.android.signin.LaunchMessageModalRouter;
import com.coinbase.android.wbl.WithdrawalBasedLimitsExistingUserModalRouter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;
import javax.inject.Provider;

public final class DashboardMainPresenterModule_ProvidesModalRoutersFactory implements Factory<List<ModalRouter>> {
    private final Provider<GdprModalRouter> gdprModalRouterProvider;
    private final Provider<LaunchMessageModalRouter> launchMessageModalRouterProvider;
    private final DashboardMainPresenterModule module;
    private final Provider<WithdrawalBasedLimitsExistingUserModalRouter> withdrawalBasedLimitsExistingUserModalRouterProvider;

    public DashboardMainPresenterModule_ProvidesModalRoutersFactory(DashboardMainPresenterModule module, Provider<LaunchMessageModalRouter> launchMessageModalRouterProvider, Provider<GdprModalRouter> gdprModalRouterProvider, Provider<WithdrawalBasedLimitsExistingUserModalRouter> withdrawalBasedLimitsExistingUserModalRouterProvider) {
        this.module = module;
        this.launchMessageModalRouterProvider = launchMessageModalRouterProvider;
        this.gdprModalRouterProvider = gdprModalRouterProvider;
        this.withdrawalBasedLimitsExistingUserModalRouterProvider = withdrawalBasedLimitsExistingUserModalRouterProvider;
    }

    public List<ModalRouter> get() {
        return provideInstance(this.module, this.launchMessageModalRouterProvider, this.gdprModalRouterProvider, this.withdrawalBasedLimitsExistingUserModalRouterProvider);
    }

    public static List<ModalRouter> provideInstance(DashboardMainPresenterModule module, Provider<LaunchMessageModalRouter> launchMessageModalRouterProvider, Provider<GdprModalRouter> gdprModalRouterProvider, Provider<WithdrawalBasedLimitsExistingUserModalRouter> withdrawalBasedLimitsExistingUserModalRouterProvider) {
        return proxyProvidesModalRouters(module, (LaunchMessageModalRouter) launchMessageModalRouterProvider.get(), (GdprModalRouter) gdprModalRouterProvider.get(), (WithdrawalBasedLimitsExistingUserModalRouter) withdrawalBasedLimitsExistingUserModalRouterProvider.get());
    }

    public static DashboardMainPresenterModule_ProvidesModalRoutersFactory create(DashboardMainPresenterModule module, Provider<LaunchMessageModalRouter> launchMessageModalRouterProvider, Provider<GdprModalRouter> gdprModalRouterProvider, Provider<WithdrawalBasedLimitsExistingUserModalRouter> withdrawalBasedLimitsExistingUserModalRouterProvider) {
        return new DashboardMainPresenterModule_ProvidesModalRoutersFactory(module, launchMessageModalRouterProvider, gdprModalRouterProvider, withdrawalBasedLimitsExistingUserModalRouterProvider);
    }

    public static List<ModalRouter> proxyProvidesModalRouters(DashboardMainPresenterModule instance, LaunchMessageModalRouter launchMessageModalRouter, GdprModalRouter gdprModalRouter, WithdrawalBasedLimitsExistingUserModalRouter withdrawalBasedLimitsExistingUserModalRouter) {
        return (List) Preconditions.checkNotNull(instance.providesModalRouters(launchMessageModalRouter, gdprModalRouter, withdrawalBasedLimitsExistingUserModalRouter), "Cannot return null from a non-@Nullable @Provides method");
    }
}
