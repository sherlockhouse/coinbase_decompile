package com.coinbase.android.buysell;

import android.app.Application;
import com.coinbase.android.task.SyncAccountsTask;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SellPresenterModule_ProvidesSyncAccountsTaskFactory implements Factory<SyncAccountsTask> {
    private final Provider<Application> appProvider;
    private final SellPresenterModule module;

    public SellPresenterModule_ProvidesSyncAccountsTaskFactory(SellPresenterModule module, Provider<Application> appProvider) {
        this.module = module;
        this.appProvider = appProvider;
    }

    public SyncAccountsTask get() {
        return provideInstance(this.module, this.appProvider);
    }

    public static SyncAccountsTask provideInstance(SellPresenterModule module, Provider<Application> appProvider) {
        return proxyProvidesSyncAccountsTask(module, (Application) appProvider.get());
    }

    public static SellPresenterModule_ProvidesSyncAccountsTaskFactory create(SellPresenterModule module, Provider<Application> appProvider) {
        return new SellPresenterModule_ProvidesSyncAccountsTaskFactory(module, appProvider);
    }

    public static SyncAccountsTask proxyProvidesSyncAccountsTask(SellPresenterModule instance, Application app) {
        return (SyncAccountsTask) Preconditions.checkNotNull(instance.providesSyncAccountsTask(app), "Cannot return null from a non-@Nullable @Provides method");
    }
}
