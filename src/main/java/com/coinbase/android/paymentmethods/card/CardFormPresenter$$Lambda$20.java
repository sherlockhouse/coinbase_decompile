package com.coinbase.android.paymentmethods.card;

import android.util.Pair;
import com.coinbase.api.internal.models.paymentMethods.verify.Data;
import rx.functions.Action1;

final /* synthetic */ class CardFormPresenter$$Lambda$20 implements Action1 {
    private final CardFormPresenter arg$1;
    private final Data arg$2;

    private CardFormPresenter$$Lambda$20(CardFormPresenter cardFormPresenter, Data data) {
        this.arg$1 = cardFormPresenter;
        this.arg$2 = data;
    }

    public static Action1 lambdaFactory$(CardFormPresenter cardFormPresenter, Data data) {
        return new CardFormPresenter$$Lambda$20(cardFormPresenter, data);
    }

    public void call(Object obj) {
        CardFormPresenter.lambda$pollWorldPayAddCard$23(this.arg$1, this.arg$2, (Pair) obj);
    }
}
