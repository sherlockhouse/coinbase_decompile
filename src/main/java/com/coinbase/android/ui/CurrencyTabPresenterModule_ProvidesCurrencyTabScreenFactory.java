package com.coinbase.android.ui;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CurrencyTabPresenterModule_ProvidesCurrencyTabScreenFactory implements Factory<CurrencyTabScreen> {
    private final CurrencyTabPresenterModule module;

    public CurrencyTabPresenterModule_ProvidesCurrencyTabScreenFactory(CurrencyTabPresenterModule module) {
        this.module = module;
    }

    public CurrencyTabScreen get() {
        return provideInstance(this.module);
    }

    public static CurrencyTabScreen provideInstance(CurrencyTabPresenterModule module) {
        return proxyProvidesCurrencyTabScreen(module);
    }

    public static CurrencyTabPresenterModule_ProvidesCurrencyTabScreenFactory create(CurrencyTabPresenterModule module) {
        return new CurrencyTabPresenterModule_ProvidesCurrencyTabScreenFactory(module);
    }

    public static CurrencyTabScreen proxyProvidesCurrencyTabScreen(CurrencyTabPresenterModule instance) {
        return (CurrencyTabScreen) Preconditions.checkNotNull(instance.providesCurrencyTabScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
