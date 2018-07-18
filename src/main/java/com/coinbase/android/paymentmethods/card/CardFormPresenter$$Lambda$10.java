package com.coinbase.android.paymentmethods.card;

import android.util.Pair;
import com.coinbase.api.internal.models.paymentMethods.Data;
import rx.functions.Action1;

final /* synthetic */ class CardFormPresenter$$Lambda$10 implements Action1 {
    private final CardFormPresenter arg$1;
    private final Data arg$2;

    private CardFormPresenter$$Lambda$10(CardFormPresenter cardFormPresenter, Data data) {
        this.arg$1 = cardFormPresenter;
        this.arg$2 = data;
    }

    public static Action1 lambdaFactory$(CardFormPresenter cardFormPresenter, Data data) {
        return new CardFormPresenter$$Lambda$10(cardFormPresenter, data);
    }

    public void call(Object obj) {
        CardFormPresenter.lambda$finishProcessPaymentCard$9(this.arg$1, this.arg$2, (Pair) obj);
    }
}
