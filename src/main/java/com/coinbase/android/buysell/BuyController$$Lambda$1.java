package com.coinbase.android.buysell;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class BuyController$$Lambda$1 implements OnClickListener {
    private final BuyController arg$1;

    private BuyController$$Lambda$1(BuyController buyController) {
        this.arg$1 = buyController;
    }

    public static OnClickListener lambdaFactory$(BuyController buyController) {
        return new BuyController$$Lambda$1(buyController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onSwapAmountClicked();
    }
}
