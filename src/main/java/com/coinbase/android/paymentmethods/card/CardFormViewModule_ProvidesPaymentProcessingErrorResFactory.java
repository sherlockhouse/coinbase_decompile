package com.coinbase.android.paymentmethods.card;

import dagger.internal.Factory;

public final class CardFormViewModule_ProvidesPaymentProcessingErrorResFactory implements Factory<Integer> {
    private final CardFormViewModule module;

    public CardFormViewModule_ProvidesPaymentProcessingErrorResFactory(CardFormViewModule module) {
        this.module = module;
    }

    public Integer get() {
        return provideInstance(this.module);
    }

    public static Integer provideInstance(CardFormViewModule module) {
        return Integer.valueOf(proxyProvidesPaymentProcessingErrorRes(module));
    }

    public static CardFormViewModule_ProvidesPaymentProcessingErrorResFactory create(CardFormViewModule module) {
        return new CardFormViewModule_ProvidesPaymentProcessingErrorResFactory(module);
    }

    public static int proxyProvidesPaymentProcessingErrorRes(CardFormViewModule instance) {
        return instance.providesPaymentProcessingErrorRes();
    }
}
