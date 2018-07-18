package com.coinbase.android.buysell;

import com.coinbase.android.wbl.AvailableBalance;
import rx.functions.Action1;

final /* synthetic */ class SellPresenter$$Lambda$23 implements Action1 {
    private final SellPresenter arg$1;

    private SellPresenter$$Lambda$23(SellPresenter sellPresenter) {
        this.arg$1 = sellPresenter;
    }

    public static Action1 lambdaFactory$(SellPresenter sellPresenter) {
        return new SellPresenter$$Lambda$23(sellPresenter);
    }

    public void call(Object obj) {
        SellPresenter.lambda$setPrimaryAccount$22(this.arg$1, (AvailableBalance) obj);
    }
}
