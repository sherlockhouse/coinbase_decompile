package com.coinbase.android.paymentmethods.linkedaccounts;

import android.view.View;
import android.view.View.OnClickListener;
import com.coinbase.v2.models.paymentMethods.Data;

final /* synthetic */ class PaymentMethodListAdapter$$Lambda$2 implements OnClickListener {
    private final PaymentMethodListAdapter arg$1;
    private final Data arg$2;

    private PaymentMethodListAdapter$$Lambda$2(PaymentMethodListAdapter paymentMethodListAdapter, Data data) {
        this.arg$1 = paymentMethodListAdapter;
        this.arg$2 = data;
    }

    public static OnClickListener lambdaFactory$(PaymentMethodListAdapter paymentMethodListAdapter, Data data) {
        return new PaymentMethodListAdapter$$Lambda$2(paymentMethodListAdapter, data);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onPaymentMethodClicked(this.arg$2);
    }
}
