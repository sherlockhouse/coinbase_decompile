package com.coinbase.android.buysell;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class SellController$$Lambda$1 implements OnClickListener {
    private final SellController arg$1;

    private SellController$$Lambda$1(SellController sellController) {
        this.arg$1 = sellController;
    }

    public static OnClickListener lambdaFactory$(SellController sellController) {
        return new SellController$$Lambda$1(sellController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onSwapAmountClicked();
    }
}
