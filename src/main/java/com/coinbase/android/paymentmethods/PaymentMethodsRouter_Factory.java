package com.coinbase.android.paymentmethods;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class PaymentMethodsRouter_Factory implements Factory<PaymentMethodsRouter> {
    private final Provider<ActionBarController> controllerProvider;
    private final Provider<PaymentMethodsScreen> screenProvider;

    public PaymentMethodsRouter_Factory(Provider<ActionBarController> controllerProvider, Provider<PaymentMethodsScreen> screenProvider) {
        this.controllerProvider = controllerProvider;
        this.screenProvider = screenProvider;
    }

    public PaymentMethodsRouter get() {
        return provideInstance(this.controllerProvider, this.screenProvider);
    }

    public static PaymentMethodsRouter provideInstance(Provider<ActionBarController> controllerProvider, Provider<PaymentMethodsScreen> screenProvider) {
        return new PaymentMethodsRouter((ActionBarController) controllerProvider.get(), (PaymentMethodsScreen) screenProvider.get());
    }

    public static PaymentMethodsRouter_Factory create(Provider<ActionBarController> controllerProvider, Provider<PaymentMethodsScreen> screenProvider) {
        return new PaymentMethodsRouter_Factory(controllerProvider, screenProvider);
    }

    public static PaymentMethodsRouter newPaymentMethodsRouter(ActionBarController controller, PaymentMethodsScreen screen) {
        return new PaymentMethodsRouter(controller, screen);
    }
}
