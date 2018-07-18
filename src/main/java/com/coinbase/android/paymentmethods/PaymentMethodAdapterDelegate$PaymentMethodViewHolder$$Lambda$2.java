package com.coinbase.android.paymentmethods;

import android.view.View;
import android.view.View.OnClickListener;
import com.coinbase.v2.models.paymentMethods.Data;

final /* synthetic */ class PaymentMethodAdapterDelegate$PaymentMethodViewHolder$$Lambda$2 implements OnClickListener {
    private final PaymentMethodViewHolder arg$1;
    private final Data arg$2;

    private PaymentMethodAdapterDelegate$PaymentMethodViewHolder$$Lambda$2(PaymentMethodViewHolder paymentMethodViewHolder, Data data) {
        this.arg$1 = paymentMethodViewHolder;
        this.arg$2 = data;
    }

    public static OnClickListener lambdaFactory$(PaymentMethodViewHolder paymentMethodViewHolder, Data data) {
        return new PaymentMethodAdapterDelegate$PaymentMethodViewHolder$$Lambda$2(paymentMethodViewHolder, data);
    }

    public void onClick(View view) {
        this.arg$1.this$0.mPresenter.onVerifyPaymentMethodClicked(this.arg$2);
    }
}
