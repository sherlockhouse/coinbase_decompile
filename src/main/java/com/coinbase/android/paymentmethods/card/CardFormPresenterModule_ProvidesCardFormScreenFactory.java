package com.coinbase.android.paymentmethods.card;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CardFormPresenterModule_ProvidesCardFormScreenFactory implements Factory<CardFormScreen> {
    private final CardFormPresenterModule module;

    public CardFormPresenterModule_ProvidesCardFormScreenFactory(CardFormPresenterModule module) {
        this.module = module;
    }

    public CardFormScreen get() {
        return provideInstance(this.module);
    }

    public static CardFormScreen provideInstance(CardFormPresenterModule module) {
        return proxyProvidesCardFormScreen(module);
    }

    public static CardFormPresenterModule_ProvidesCardFormScreenFactory create(CardFormPresenterModule module) {
        return new CardFormPresenterModule_ProvidesCardFormScreenFactory(module);
    }

    public static CardFormScreen proxyProvidesCardFormScreen(CardFormPresenterModule instance) {
        return (CardFormScreen) Preconditions.checkNotNull(instance.providesCardFormScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
