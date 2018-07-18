package com.coinbase.android.paymentmethods;

import android.view.View;
import android.view.View.OnClickListener;
import com.coinbase.api.internal.models.institutions.Data;

final /* synthetic */ class BanksAdapterDelegate$BankViewHolder$$Lambda$1 implements OnClickListener {
    private final BankViewHolder arg$1;
    private final Data arg$2;

    private BanksAdapterDelegate$BankViewHolder$$Lambda$1(BankViewHolder bankViewHolder, Data data) {
        this.arg$1 = bankViewHolder;
        this.arg$2 = data;
    }

    public static OnClickListener lambdaFactory$(BankViewHolder bankViewHolder, Data data) {
        return new BanksAdapterDelegate$BankViewHolder$$Lambda$1(bankViewHolder, data);
    }

    public void onClick(View view) {
        this.arg$1.this$0.mPresenter.onBankClicked(this.arg$2);
    }
}
