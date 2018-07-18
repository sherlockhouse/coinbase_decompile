package com.coinbase.android.buysell;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class SellController$$Lambda$3 implements OnClickListener {
    private final SellController arg$1;

    private SellController$$Lambda$3(SellController sellController) {
        this.arg$1 = sellController;
    }

    public static OnClickListener lambdaFactory$(SellController sellController) {
        return new SellController$$Lambda$3(sellController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onSellMaxClicked();
    }
}
