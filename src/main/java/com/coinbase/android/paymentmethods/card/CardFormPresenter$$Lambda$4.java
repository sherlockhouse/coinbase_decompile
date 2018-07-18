package com.coinbase.android.paymentmethods.card;

import com.coinbase.android.ui.MystiqueListButtonConnector.MystiqueListButtonEvent;
import rx.functions.Func1;

final /* synthetic */ class CardFormPresenter$$Lambda$4 implements Func1 {
    private static final CardFormPresenter$$Lambda$4 instance = new CardFormPresenter$$Lambda$4();

    private CardFormPresenter$$Lambda$4() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return CardFormPresenter.lambda$onResume$3((MystiqueListButtonEvent) obj);
    }
}
