package com.coinbase.android.wbl;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class WithdrawalBasedLimitsDialogController$$Lambda$1 implements OnClickListener {
    private final WithdrawalBasedLimitsDialogController arg$1;

    private WithdrawalBasedLimitsDialogController$$Lambda$1(WithdrawalBasedLimitsDialogController withdrawalBasedLimitsDialogController) {
        this.arg$1 = withdrawalBasedLimitsDialogController;
    }

    public static OnClickListener lambdaFactory$(WithdrawalBasedLimitsDialogController withdrawalBasedLimitsDialogController) {
        return new WithdrawalBasedLimitsDialogController$$Lambda$1(withdrawalBasedLimitsDialogController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onClickLearnMore();
    }
}
