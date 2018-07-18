package com.coinbase.android.deposits.fiat;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class DepositFiatController$$Lambda$1 implements OnClickListener {
    private final DepositFiatController arg$1;

    private DepositFiatController$$Lambda$1(DepositFiatController depositFiatController) {
        this.arg$1 = depositFiatController;
    }

    public static OnClickListener lambdaFactory$(DepositFiatController depositFiatController) {
        return new DepositFiatController$$Lambda$1(depositFiatController);
    }

    public void onClick(View view) {
        DepositFiatController.lambda$onCreateView$0(this.arg$1, view);
    }
}
