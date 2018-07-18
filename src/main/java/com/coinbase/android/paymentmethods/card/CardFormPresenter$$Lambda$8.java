package com.coinbase.android.paymentmethods.card;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class CardFormPresenter$$Lambda$8 implements Action1 {
    private final CardFormPresenter arg$1;

    private CardFormPresenter$$Lambda$8(CardFormPresenter cardFormPresenter) {
        this.arg$1 = cardFormPresenter;
    }

    public static Action1 lambdaFactory$(CardFormPresenter cardFormPresenter) {
        return new CardFormPresenter$$Lambda$8(cardFormPresenter);
    }

    public void call(Object obj) {
        CardFormPresenter.lambda$paymentCardSetup$7(this.arg$1, (Pair) obj);
    }
}
