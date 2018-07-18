package com.coinbase.android.deposits.fiat;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class WithdrawDepositSuccessController$$Lambda$1 implements OnClickListener {
    private final WithdrawDepositSuccessController arg$1;

    private WithdrawDepositSuccessController$$Lambda$1(WithdrawDepositSuccessController withdrawDepositSuccessController) {
        this.arg$1 = withdrawDepositSuccessController;
    }

    public static OnClickListener lambdaFactory$(WithdrawDepositSuccessController withdrawDepositSuccessController) {
        return new WithdrawDepositSuccessController$$Lambda$1(withdrawDepositSuccessController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onAccountClicked();
    }
}
