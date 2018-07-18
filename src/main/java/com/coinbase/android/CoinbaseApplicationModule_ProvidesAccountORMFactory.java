package com.coinbase.android;

import com.coinbase.android.db.AccountORM;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseApplicationModule_ProvidesAccountORMFactory implements Factory<AccountORM> {
    private final CoinbaseApplicationModule module;

    public CoinbaseApplicationModule_ProvidesAccountORMFactory(CoinbaseApplicationModule module) {
        this.module = module;
    }

    public AccountORM get() {
        return provideInstance(this.module);
    }

    public static AccountORM provideInstance(CoinbaseApplicationModule module) {
        return proxyProvidesAccountORM(module);
    }

    public static CoinbaseApplicationModule_ProvidesAccountORMFactory create(CoinbaseApplicationModule module) {
        return new CoinbaseApplicationModule_ProvidesAccountORMFactory(module);
    }

    public static AccountORM proxyProvidesAccountORM(CoinbaseApplicationModule instance) {
        return (AccountORM) Preconditions.checkNotNull(instance.providesAccountORM(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
