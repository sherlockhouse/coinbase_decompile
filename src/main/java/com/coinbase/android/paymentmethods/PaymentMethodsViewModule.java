package com.coinbase.android.paymentmethods;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.paymentmethods.PaymentMethodsPresenter.PaymentMethodDeleted;

public class PaymentMethodsViewModule {
    @ControllerScope
    @PaymentMethodDeleted
    int providesPaymentMethodDeletedRes() {
        return R.string.payment_method_deleted;
    }
}
