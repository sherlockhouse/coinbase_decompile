package com.coinbase.android.buysell;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AbstractBuySellConfirmationController$$Lambda$1 implements OnClickListener {
    private final AbstractBuySellConfirmationController arg$1;

    private AbstractBuySellConfirmationController$$Lambda$1(AbstractBuySellConfirmationController abstractBuySellConfirmationController) {
        this.arg$1 = abstractBuySellConfirmationController;
    }

    public static OnClickListener lambdaFactory$(AbstractBuySellConfirmationController abstractBuySellConfirmationController) {
        return new AbstractBuySellConfirmationController$$Lambda$1(abstractBuySellConfirmationController);
    }

    public void onClick(View view) {
        AbstractBuySellConfirmationController.lambda$onCreateView$0(this.arg$1, view);
    }
}
