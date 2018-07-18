package com.coinbase.android.transfers;

import android.app.Application;
import com.coinbase.android.task.SyncAccountsTask;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SendPresenterModule_ProvidesSyncAccountsTaskFactory implements Factory<SyncAccountsTask> {
    private final Provider<Application> appProvider;
    private final SendPresenterModule module;

    public SendPresenterModule_ProvidesSyncAccountsTaskFactory(SendPresenterModule module, Provider<Application> appProvider) {
        this.module = module;
        this.appProvider = appProvider;
    }

    public SyncAccountsTask get() {
        return provideInstance(this.module, this.appProvider);
    }

    public static SyncAccountsTask provideInstance(SendPresenterModule module, Provider<Application> appProvider) {
        return proxyProvidesSyncAccountsTask(module, (Application) appProvider.get());
    }

    public static SendPresenterModule_ProvidesSyncAccountsTaskFactory create(SendPresenterModule module, Provider<Application> appProvider) {
        return new SendPresenterModule_ProvidesSyncAccountsTaskFactory(module, appProvider);
    }

    public static SyncAccountsTask proxyProvidesSyncAccountsTask(SendPresenterModule instance, Application app) {
        return (SyncAccountsTask) Preconditions.checkNotNull(instance.providesSyncAccountsTask(app), "Cannot return null from a non-@Nullable @Provides method");
    }
}
