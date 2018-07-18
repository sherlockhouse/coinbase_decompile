package com.coinbase.android.paymentmethods;

import android.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;
import com.coinbase.api.internal.models.availablePaymentMethods.AvailablePaymentMethod;

final /* synthetic */ class AvailablePaymentMethodAdapterDelegate$AvailablePaymentMethodViewHolder$$Lambda$1 implements OnClickListener {
    private final AvailablePaymentMethodViewHolder arg$1;
    private final Pair arg$2;

    private AvailablePaymentMethodAdapterDelegate$AvailablePaymentMethodViewHolder$$Lambda$1(AvailablePaymentMethodViewHolder availablePaymentMethodViewHolder, Pair pair) {
        this.arg$1 = availablePaymentMethodViewHolder;
        this.arg$2 = pair;
    }

    public static OnClickListener lambdaFactory$(AvailablePaymentMethodViewHolder availablePaymentMethodViewHolder, Pair pair) {
        return new AvailablePaymentMethodAdapterDelegate$AvailablePaymentMethodViewHolder$$Lambda$1(availablePaymentMethodViewHolder, pair);
    }

    public void onClick(View view) {
        this.arg$1.this$0.mPresenter.onClickAddPaymentMethod((AvailablePaymentMethod) this.arg$2.second);
    }
}
