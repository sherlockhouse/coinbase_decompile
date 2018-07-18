package com.coinbase.android.accounts;

import android.app.Application;
import com.coinbase.android.task.FetchAccountTask;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class AccountTransactionsPresenterModule_ProvideFetchAccountTaskFactory implements Factory<FetchAccountTask> {
    private final Provider<Application> contextProvider;
    private final AccountTransactionsPresenterModule module;

    public AccountTransactionsPresenterModule_ProvideFetchAccountTaskFactory(AccountTransactionsPresenterModule module, Provider<Application> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    public FetchAccountTask get() {
        return provideInstance(this.module, this.contextProvider);
    }

    public static FetchAccountTask provideInstance(AccountTransactionsPresenterModule module, Provider<Application> contextProvider) {
        return proxyProvideFetchAccountTask(module, (Application) contextProvider.get());
    }

    public static AccountTransactionsPresenterModule_ProvideFetchAccountTaskFactory create(AccountTransactionsPresenterModule module, Provider<Application> contextProvider) {
        return new AccountTransactionsPresenterModule_ProvideFetchAccountTaskFactory(module, contextProvider);
    }

    public static FetchAccountTask proxyProvideFetchAccountTask(AccountTransactionsPresenterModule instance, Application context) {
        return (FetchAccountTask) Preconditions.checkNotNull(instance.provideFetchAccountTask(context), "Cannot return null from a non-@Nullable @Provides method");
    }
}
