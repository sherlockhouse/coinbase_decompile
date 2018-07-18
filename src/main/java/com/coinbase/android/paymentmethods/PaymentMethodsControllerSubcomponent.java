package com.coinbase.android.paymentmethods;

import com.coinbase.android.ControllerScope;

@ControllerScope
public interface PaymentMethodsControllerSubcomponent {
    void inject(PaymentMethodsController paymentMethodsController);
}
