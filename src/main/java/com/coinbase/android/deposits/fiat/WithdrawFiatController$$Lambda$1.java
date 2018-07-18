package com.coinbase.android.deposits.fiat;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class WithdrawFiatController$$Lambda$1 implements OnClickListener {
    private final WithdrawFiatController arg$1;

    private WithdrawFiatController$$Lambda$1(WithdrawFiatController withdrawFiatController) {
        this.arg$1 = withdrawFiatController;
    }

    public static OnClickListener lambdaFactory$(WithdrawFiatController withdrawFiatController) {
        return new WithdrawFiatController$$Lambda$1(withdrawFiatController);
    }

    public void onClick(View view) {
        WithdrawFiatController.lambda$onCreateView$0(this.arg$1, view);
    }
}
