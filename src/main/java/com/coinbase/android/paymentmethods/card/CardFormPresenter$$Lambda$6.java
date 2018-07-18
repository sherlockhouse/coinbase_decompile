package com.coinbase.android.paymentmethods.card;

import com.coinbase.api.internal.models.billingAddress.Data;
import rx.functions.Action1;

final /* synthetic */ class CardFormPresenter$$Lambda$6 implements Action1 {
    private final CardFormPresenter arg$1;

    private CardFormPresenter$$Lambda$6(CardFormPresenter cardFormPresenter) {
        this.arg$1 = cardFormPresenter;
    }

    public static Action1 lambdaFactory$(CardFormPresenter cardFormPresenter) {
        return new CardFormPresenter$$Lambda$6(cardFormPresenter);
    }

    public void call(Object obj) {
        CardFormPresenter.lambda$onResume$5(this.arg$1, (Data) obj);
    }
}
