package com.coinbase.android.paymentmethods.card;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class CardFormPresenter$$Lambda$14 implements Action1 {
    private final CardFormPresenter arg$1;

    private CardFormPresenter$$Lambda$14(CardFormPresenter cardFormPresenter) {
        this.arg$1 = cardFormPresenter;
    }

    public static Action1 lambdaFactory$(CardFormPresenter cardFormPresenter) {
        return new CardFormPresenter$$Lambda$14(cardFormPresenter);
    }

    public void call(Object obj) {
        CardFormPresenter.lambda$getPaymentMethod$13(this.arg$1, (Pair) obj);
    }
}
