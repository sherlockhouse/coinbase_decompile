package com.coinbase.android.paymentmethods;

final /* synthetic */ class PaymentMethodsPresenter$$Lambda$1 implements Runnable {
    private final PaymentMethodsPresenter arg$1;

    private PaymentMethodsPresenter$$Lambda$1(PaymentMethodsPresenter paymentMethodsPresenter) {
        this.arg$1 = paymentMethodsPresenter;
    }

    public static Runnable lambdaFactory$(PaymentMethodsPresenter paymentMethodsPresenter) {
        return new PaymentMethodsPresenter$$Lambda$1(paymentMethodsPresenter);
    }

    public void run() {
        PaymentMethodsPresenter.lambda$onActivityResult$0(this.arg$1);
    }
}
