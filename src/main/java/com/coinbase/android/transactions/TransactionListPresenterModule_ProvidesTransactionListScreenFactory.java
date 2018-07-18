package com.coinbase.android.transactions;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class TransactionListPresenterModule_ProvidesTransactionListScreenFactory implements Factory<TransactionListScreen> {
    private final TransactionListPresenterModule module;

    public TransactionListPresenterModule_ProvidesTransactionListScreenFactory(TransactionListPresenterModule module) {
        this.module = module;
    }

    public TransactionListScreen get() {
        return provideInstance(this.module);
    }

    public static TransactionListScreen provideInstance(TransactionListPresenterModule module) {
        return proxyProvidesTransactionListScreen(module);
    }

    public static TransactionListPresenterModule_ProvidesTransactionListScreenFactory create(TransactionListPresenterModule module) {
        return new TransactionListPresenterModule_ProvidesTransactionListScreenFactory(module);
    }

    public static TransactionListScreen proxyProvidesTransactionListScreen(TransactionListPresenterModule instance) {
        return (TransactionListScreen) Preconditions.checkNotNull(instance.providesTransactionListScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
