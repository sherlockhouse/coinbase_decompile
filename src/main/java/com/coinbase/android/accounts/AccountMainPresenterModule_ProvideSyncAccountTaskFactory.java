package com.coinbase.android.accounts;

import android.app.Application;
import com.coinbase.android.task.SyncAccountsTask;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class AccountMainPresenterModule_ProvideSyncAccountTaskFactory implements Factory<SyncAccountsTask> {
    private final Provider<Application> contextProvider;
    private final AccountMainPresenterModule module;

    public AccountMainPresenterModule_ProvideSyncAccountTaskFactory(AccountMainPresenterModule module, Provider<Application> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    public SyncAccountsTask get() {
        return provideInstance(this.module, this.contextProvider);
    }

    public static SyncAccountsTask provideInstance(AccountMainPresenterModule module, Provider<Application> contextProvider) {
        return proxyProvideSyncAccountTask(module, (Application) contextProvider.get());
    }

    public static AccountMainPresenterModule_ProvideSyncAccountTaskFactory create(AccountMainPresenterModule module, Provider<Application> contextProvider) {
        return new AccountMainPresenterModule_ProvideSyncAccountTaskFactory(module, contextProvider);
    }

    public static SyncAccountsTask proxyProvideSyncAccountTask(AccountMainPresenterModule instance, Application context) {
        return (SyncAccountsTask) Preconditions.checkNotNull(instance.provideSyncAccountTask(context), "Cannot return null from a non-@Nullable @Provides method");
    }
}
