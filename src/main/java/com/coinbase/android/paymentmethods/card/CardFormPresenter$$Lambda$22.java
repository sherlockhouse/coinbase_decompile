package com.coinbase.android.paymentmethods.card;

import com.coinbase.api.internal.models.paymentMethods.verify.Data;
import rx.functions.Action1;

final /* synthetic */ class CardFormPresenter$$Lambda$22 implements Action1 {
    private final CardFormPresenter arg$1;
    private final Data arg$2;

    private CardFormPresenter$$Lambda$22(CardFormPresenter cardFormPresenter, Data data) {
        this.arg$1 = cardFormPresenter;
        this.arg$2 = data;
    }

    public static Action1 lambdaFactory$(CardFormPresenter cardFormPresenter, Data data) {
        return new CardFormPresenter$$Lambda$22(cardFormPresenter, data);
    }

    public void call(Object obj) {
        this.arg$1.pollWorldPayAddCard(this.arg$2);
    }
}
