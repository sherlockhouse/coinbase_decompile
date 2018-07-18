package com.coinbase.android.deposits.fiat;

import android.app.Application;
import com.coinbase.android.task.SyncAccountsTask;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class WithdrawFiatPresenterModule_ProvidesSyncAccountsTaskFactory implements Factory<SyncAccountsTask> {
    private final Provider<Application> appProvider;
    private final WithdrawFiatPresenterModule module;

    public WithdrawFiatPresenterModule_ProvidesSyncAccountsTaskFactory(WithdrawFiatPresenterModule module, Provider<Application> appProvider) {
        this.module = module;
        this.appProvider = appProvider;
    }

    public SyncAccountsTask get() {
        return provideInstance(this.module, this.appProvider);
    }

    public static SyncAccountsTask provideInstance(WithdrawFiatPresenterModule module, Provider<Application> appProvider) {
        return proxyProvidesSyncAccountsTask(module, (Application) appProvider.get());
    }

    public static WithdrawFiatPresenterModule_ProvidesSyncAccountsTaskFactory create(WithdrawFiatPresenterModule module, Provider<Application> appProvider) {
        return new WithdrawFiatPresenterModule_ProvidesSyncAccountsTaskFactory(module, appProvider);
    }

    public static SyncAccountsTask proxyProvidesSyncAccountsTask(WithdrawFiatPresenterModule instance, Application app) {
        return (SyncAccountsTask) Preconditions.checkNotNull(instance.providesSyncAccountsTask(app), "Cannot return null from a non-@Nullable @Provides method");
    }
}
