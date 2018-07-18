package com.coinbase.android.paymentmethods.card;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CardFormPresenterModule_ProvidesCurrentControllerFactory implements Factory<ActionBarController> {
    private final CardFormPresenterModule module;

    public CardFormPresenterModule_ProvidesCurrentControllerFactory(CardFormPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(CardFormPresenterModule module) {
        return proxyProvidesCurrentController(module);
    }

    public static CardFormPresenterModule_ProvidesCurrentControllerFactory create(CardFormPresenterModule module) {
        return new CardFormPresenterModule_ProvidesCurrentControllerFactory(module);
    }

    public static ActionBarController proxyProvidesCurrentController(CardFormPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesCurrentController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
