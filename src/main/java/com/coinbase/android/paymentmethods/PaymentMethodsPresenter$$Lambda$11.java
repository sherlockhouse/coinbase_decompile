package com.coinbase.android.paymentmethods;

import com.coinbase.android.paymentmethods.DeletePaymentMethodTask.DeletePaymentMethodListener;

final /* synthetic */ class PaymentMethodsPresenter$$Lambda$11 implements DeletePaymentMethodListener {
    private final PaymentMethodsPresenter arg$1;

    private PaymentMethodsPresenter$$Lambda$11(PaymentMethodsPresenter paymentMethodsPresenter) {
        this.arg$1 = paymentMethodsPresenter;
    }

    public static DeletePaymentMethodListener lambdaFactory$(PaymentMethodsPresenter paymentMethodsPresenter) {
        return new PaymentMethodsPresenter$$Lambda$11(paymentMethodsPresenter);
    }

    public void onFinally() {
        this.arg$1.mSnackBarWrapper.show(this.arg$1.mPaymentMethodDeletedResId);
    }
}
