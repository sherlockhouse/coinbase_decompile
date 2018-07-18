package com.coinbase.android.paymentmethods.card;

import rx.functions.Action1;

final /* synthetic */ class CardFormPresenter$$Lambda$9 implements Action1 {
    private final CardFormPresenter arg$1;

    private CardFormPresenter$$Lambda$9(CardFormPresenter cardFormPresenter) {
        this.arg$1 = cardFormPresenter;
    }

    public static Action1 lambdaFactory$(CardFormPresenter cardFormPresenter) {
        return new CardFormPresenter$$Lambda$9(cardFormPresenter);
    }

    public void call(Object obj) {
        CardFormPresenter.lambda$paymentCardSetup$8(this.arg$1, (Throwable) obj);
    }
}
