package com.coinbase.android.paymentmethods.card;

import dagger.internal.Factory;

public final class CardFormViewModule_ProvidesCheckFieldsErrorResFactory implements Factory<Integer> {
    private final CardFormViewModule module;

    public CardFormViewModule_ProvidesCheckFieldsErrorResFactory(CardFormViewModule module) {
        this.module = module;
    }

    public Integer get() {
        return provideInstance(this.module);
    }

    public static Integer provideInstance(CardFormViewModule module) {
        return Integer.valueOf(proxyProvidesCheckFieldsErrorRes(module));
    }

    public static CardFormViewModule_ProvidesCheckFieldsErrorResFactory create(CardFormViewModule module) {
        return new CardFormViewModule_ProvidesCheckFieldsErrorResFactory(module);
    }

    public static int proxyProvidesCheckFieldsErrorRes(CardFormViewModule instance) {
        return instance.providesCheckFieldsErrorRes();
    }
}
