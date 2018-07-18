package com.coinbase.android.deposits.fiat;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class WithdrawFiatConfirmationController$$Lambda$1 implements OnClickListener {
    private final WithdrawFiatConfirmationController arg$1;

    private WithdrawFiatConfirmationController$$Lambda$1(WithdrawFiatConfirmationController withdrawFiatConfirmationController) {
        this.arg$1 = withdrawFiatConfirmationController;
    }

    public static OnClickListener lambdaFactory$(WithdrawFiatConfirmationController withdrawFiatConfirmationController) {
        return new WithdrawFiatConfirmationController$$Lambda$1(withdrawFiatConfirmationController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onConfirmClicked();
    }
}
