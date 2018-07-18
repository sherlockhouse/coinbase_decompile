package com.coinbase.android.wbl;

import android.app.Application;
import com.coinbase.android.task.SyncAccountsTask;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class AvailableBalancePresenterModule_ProvidesSyncAccountsTaskFactory implements Factory<SyncAccountsTask> {
    private final Provider<Application> appProvider;
    private final AvailableBalancePresenterModule module;

    public AvailableBalancePresenterModule_ProvidesSyncAccountsTaskFactory(AvailableBalancePresenterModule module, Provider<Application> appProvider) {
        this.module = module;
        this.appProvider = appProvider;
    }

    public SyncAccountsTask get() {
        return provideInstance(this.module, this.appProvider);
    }

    public static SyncAccountsTask provideInstance(AvailableBalancePresenterModule module, Provider<Application> appProvider) {
        return proxyProvidesSyncAccountsTask(module, (Application) appProvider.get());
    }

    public static AvailableBalancePresenterModule_ProvidesSyncAccountsTaskFactory create(AvailableBalancePresenterModule module, Provider<Application> appProvider) {
        return new AvailableBalancePresenterModule_ProvidesSyncAccountsTaskFactory(module, appProvider);
    }

    public static SyncAccountsTask proxyProvidesSyncAccountsTask(AvailableBalancePresenterModule instance, Application app) {
        return (SyncAccountsTask) Preconditions.checkNotNull(instance.providesSyncAccountsTask(app), "Cannot return null from a non-@Nullable @Provides method");
    }
}
