package com.coinbase.android.buysell;

import rx.functions.Action1;

final /* synthetic */ class BuyConfirmationPresenter$$Lambda$15 implements Action1 {
    private final BuyConfirmationPresenter arg$1;
    private final String arg$2;
    private final String arg$3;

    private BuyConfirmationPresenter$$Lambda$15(BuyConfirmationPresenter buyConfirmationPresenter, String str, String str2) {
        this.arg$1 = buyConfirmationPresenter;
        this.arg$2 = str;
        this.arg$3 = str2;
    }

    public static Action1 lambdaFactory$(BuyConfirmationPresenter buyConfirmationPresenter, String str, String str2) {
        return new BuyConfirmationPresenter$$Lambda$15(buyConfirmationPresenter, str, str2);
    }

    public void call(Object obj) {
        this.arg$1.pollForWorldpayStatus(this.arg$2, this.arg$3);
    }
}
