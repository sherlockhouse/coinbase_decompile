package com.coinbase.android.paymentmethods.card;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import org.apache.commons.validator.routines.CreditCardValidator;

public final class CardFormPresenterModule_ProvidesCardValidatorFactory implements Factory<CreditCardValidator> {
    private final CardFormPresenterModule module;

    public CardFormPresenterModule_ProvidesCardValidatorFactory(CardFormPresenterModule module) {
        this.module = module;
    }

    public CreditCardValidator get() {
        return provideInstance(this.module);
    }

    public static CreditCardValidator provideInstance(CardFormPresenterModule module) {
        return proxyProvidesCardValidator(module);
    }

    public static CardFormPresenterModule_ProvidesCardValidatorFactory create(CardFormPresenterModule module) {
        return new CardFormPresenterModule_ProvidesCardValidatorFactory(module);
    }

    public static CreditCardValidator proxyProvidesCardValidator(CardFormPresenterModule instance) {
        return (CreditCardValidator) Preconditions.checkNotNull(instance.providesCardValidator(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
