package com.coinbase.android.paymentmethods.card;

import android.util.Pair;
import retrofit2.Response;
import rx.functions.Action1;

final /* synthetic */ class CardFormPresenter$$Lambda$24 implements Action1 {
    private final CardFormPresenter arg$1;
    private final Response arg$2;

    private CardFormPresenter$$Lambda$24(CardFormPresenter cardFormPresenter, Response response) {
        this.arg$1 = cardFormPresenter;
        this.arg$2 = response;
    }

    public static Action1 lambdaFactory$(CardFormPresenter cardFormPresenter, Response response) {
        return new CardFormPresenter$$Lambda$24(cardFormPresenter, response);
    }

    public void call(Object obj) {
        CardFormPresenter.lambda$null$21(this.arg$1, this.arg$2, (Pair) obj);
    }
}
