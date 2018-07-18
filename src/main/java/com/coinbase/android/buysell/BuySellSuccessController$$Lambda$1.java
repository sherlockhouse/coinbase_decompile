package com.coinbase.android.buysell;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class BuySellSuccessController$$Lambda$1 implements OnClickListener {
    private final BuySellSuccessController arg$1;

    private BuySellSuccessController$$Lambda$1(BuySellSuccessController buySellSuccessController) {
        this.arg$1 = buySellSuccessController;
    }

    public static OnClickListener lambdaFactory$(BuySellSuccessController buySellSuccessController) {
        return new BuySellSuccessController$$Lambda$1(buySellSuccessController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onAccountClicked();
    }
}
