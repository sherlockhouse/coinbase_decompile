package com.coinbase.android.paymentmethods.card;

import com.coinbase.android.ui.MystiqueListButtonConnector.MystiqueListButtonEvent;
import rx.functions.Func1;

final /* synthetic */ class CardFormPresenter$$Lambda$2 implements Func1 {
    private static final CardFormPresenter$$Lambda$2 instance = new CardFormPresenter$$Lambda$2();

    private CardFormPresenter$$Lambda$2() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return CardFormPresenter.lambda$onResume$1((MystiqueListButtonEvent) obj);
    }
}
