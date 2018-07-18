package com.coinbase.android.paymentmethods;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class PaymentMethodsPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final PaymentMethodsPresenterModule module;

    public PaymentMethodsPresenterModule_ProvidesActionBarControllerFactory(PaymentMethodsPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(PaymentMethodsPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static PaymentMethodsPresenterModule_ProvidesActionBarControllerFactory create(PaymentMethodsPresenterModule module) {
        return new PaymentMethodsPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(PaymentMethodsPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
