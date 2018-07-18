package com.coinbase.android.deposits.fiat;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class DepositFiatConfirmationController$$Lambda$1 implements OnClickListener {
    private final DepositFiatConfirmationController arg$1;

    private DepositFiatConfirmationController$$Lambda$1(DepositFiatConfirmationController depositFiatConfirmationController) {
        this.arg$1 = depositFiatConfirmationController;
    }

    public static OnClickListener lambdaFactory$(DepositFiatConfirmationController depositFiatConfirmationController) {
        return new DepositFiatConfirmationController$$Lambda$1(depositFiatConfirmationController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onConfirmClicked();
    }
}
